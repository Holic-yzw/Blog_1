package com.holic.blog.service.implement;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.holic.blog.entity.CommonUser;
import com.holic.blog.entity.Blog;
import com.holic.blog.entity.Link;
import com.holic.blog.entity.example.SearchBlogForAdmin;
import com.holic.blog.entity.example.ShowBlogForAdmin;
import com.holic.blog.entity.example.ShowBlogForViewer;
import com.holic.blog.entity.example.ShowLatestBlogForViewer;
import com.holic.blog.mapper.BlogMapper;
import com.holic.blog.service.BlogService;
import com.holic.blog.util.MarkDownUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * @author：HOLiC_y
 * @date：2020/6/14
 */
@Service
public class BlogServiceImpl implements BlogService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private BlogMapper blogMapper;

    @Override
    public PageInfo<ShowBlogForAdmin> listBlog(Integer pageNum, Integer pageSize, SearchBlogForAdmin blog) {

        // 参数转换
        String recommend = "true".equalsIgnoreCase(blog.getRecommend()) ? "on" : "off";
        blog.setRecommend(recommend);

        PageHelper.startPage(pageNum, pageSize);
        List<ShowBlogForAdmin> list = blogMapper.findAllBlogBySearch(blog);

        logger.info("\n 博客条件查询结果 {} \n ", list.toString());

        PageInfo page = new PageInfo(list);
        return page;
    }

    /* * 
     * @Description: 初始化时查询全部
     * @author: Administrator
     * @date: 2020/7/1
     * @Param: [pageNum, pageSize]
     * @return: com.github.pagehelper.PageInfo<com.holic.blog.entity.Blog>
     **/
    @Override
    public PageInfo<ShowBlogForAdmin> listBlog(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<ShowBlogForAdmin> list = blogMapper.findAllBlog();
        PageInfo page = new PageInfo(list);
        return page;
    }

    @Override
    public Blog getBlog(Long id) {
        Blog blog = blogMapper.getBlogById(id);
        if (blog == null) {
            throw new RuntimeException("查找博客失败");
        }
        return blog;
    }

    /* * 
     * @Description:将content的内容进行处理，既将markdown文本转换为HTML内容
     * @Author:HOLiC
     * @Date:2020/7/5 0005 
     * @Param:[id]
     * @return:com.holic.blog.entity.Blog
     * */
    @Override
    public Blog getBlogForView(Long id) {
        Blog blog = blogMapper.getBlogById(id);
        String content = blog.getContent();
        blog.setContent(MarkDownUtils.markdownToHtmlAdvice(content));
        return blog;
    }

    @Transactional
    @Override
    public int saveBlog(Blog blog) {
        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        blog.setCreateDate(date);
        blog.setUpdateDate(date);

        logger.info("\n 博客入库信息 {} \n ", blog);

        int i = blogMapper.saveBlog(blog);

        if (i == 0) {
            throw new RuntimeException("博客保存失败");
        }

        Long blogId = blog.getId();
        String tagIds = blog.getBlogTagId();
        saveLink(blogId, tagIds);
        return i;
    }

    @Transactional
    @Override
    public int updateBlog(Blog blog) {
        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        blog.setUpdateDate(date);

        logger.info("\n 博客入库信息 {} \n ", blog);

        int i = blogMapper.updateBlogById(blog);

        if (i == 0) {
            throw new RuntimeException("博客保存失败");
        }

        Long blogId = blog.getId();
        String tagIds = blog.getBlogTagId();

        // 查找未修改之前的tagId,并于修改后的比较,不一致则删掉之前的，保存新的
        String origin = getTagIdByBlogId(blogId);
        if (!origin.equalsIgnoreCase(tagIds)) {
            blogMapper.deleteLinkByBlogId(blogId);
            saveLink(blogId, tagIds);
        }

        return i;
    }

    private void saveLink(Long blogId, String tagIds) {
        List<String> stringList = Arrays.asList(tagIds.split(","));
        // 批量插入到中间表里
        ArrayList<Link> linkList = new ArrayList<>();
        for (int j = 0; j < stringList.size(); j++) {
            Link link = new Link();
            link.setBlogId(blogId);
            link.setTagId(Long.parseLong(stringList.get(j)));
            linkList.add(link);
        }
        int count = blogMapper.saveLink(linkList);
        if (count != linkList.size()) {
            throw new RuntimeException("保存中间表失败");
        }
    }

    @Transactional
    @Override
    public int deleteBlog(Long id) {
        int i = blogMapper.deleteBlogById(id);
        int j = blogMapper.deleteLinkByBlogId(id);
        if (i ==0 || j ==0) {
            throw new RuntimeException("删除博客失败");
        }
        return 1;
    }

    @Override
    public int countExistBlog(String title) {
        int i = blogMapper.countExistBlog(title);
        return i;
    }

    @Override
    public String getTagIdByBlogId(Long id) {
        List<Long> list = blogMapper.getTagIdByBlogId(id);
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < list.size(); i++) {
            buffer.append(list.get(i));
            buffer.append(",");
        }
        String str = buffer.toString();
        String s = str.substring(0, str.lastIndexOf(","));
        return s;
    }

    @Override
    public CommonUser getAuthorByBlogId(Long id) {
        CommonUser author = blogMapper.getAuthorByBlogId(id);
        return author;
    }

    @Override
    public int updateViewTimes(Long id) {
        int i = blogMapper.updateViewTimes(id);
        if (i == 0) {
            throw new RuntimeException("更新浏览次数失败");
        }
        return 1;
    }

    @Override
    public List<ShowLatestBlogForViewer> listLatestBlog() {
        List<ShowLatestBlogForViewer> blogs = blogMapper.findThreeLatestBlogs();
        return blogs;
    }

    @Override
    public Map<String, List<ShowBlogForViewer>> archiveBlog() {
        List<String> years = blogMapper.getYearOfAllBlog();
        // HashMap无序，不能用
        Map<String, List<ShowBlogForViewer>> map = new TreeMap<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.compareTo(o1);
            }
        });
        for (String year : years) {
            List<ShowBlogForViewer> blogs = blogMapper.getAllBlogOfOneYear(year);
            map.put(year, blogs);
        }
        return map;
    }
}
