package com.holic.blog.service.implement;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.holic.blog.entity.Blog;
import com.holic.blog.entity.example.ExampleForSearchBlog;
import com.holic.blog.entity.example.ExampleForShowBlog;
import com.holic.blog.mapper.BlogMapper;
import com.holic.blog.service.BlogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public PageInfo<ExampleForShowBlog> listBlog(Integer pageNum, Integer pageSize, ExampleForSearchBlog blog) {
        PageHelper.startPage(pageNum, pageSize);
        List<ExampleForShowBlog> list = blogMapper.findAllBlogBySearch(blog);

        logger.info("\n 日志条件查询结果  \n {}", list.toString());

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
    public PageInfo<ExampleForShowBlog> listBlog(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<ExampleForShowBlog> list = blogMapper.findAllBlog();
        PageInfo page = new PageInfo(list);
        return page;
    }

    @Override
    public Blog getBlog(Long id) {
        return null;
    }

    @Override
    public int saveBlog(Blog blog) {
        return 0;
    }

    @Override
    public int updateBlog(Blog blog) {
        return 0;
    }

    @Override
    public int deleteBlog(Long id) {
        return 0;
    }


}
