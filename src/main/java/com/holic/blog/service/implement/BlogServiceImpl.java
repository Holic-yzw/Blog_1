package com.holic.blog.service.implement;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.holic.blog.entity.Blog;
import com.holic.blog.mapper.BlogMapper;
import com.holic.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author：HOLiC_y
 * @date：2020/6/14
 */
@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogMapper blogMapper;

    @Override
    public PageInfo<Blog> listBlog(Integer pageNum, Integer pageSize, Blog blog) {
        PageHelper.startPage(pageNum, pageSize);
        List<Blog> list = blogMapper.findAllBlog(blog);
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
