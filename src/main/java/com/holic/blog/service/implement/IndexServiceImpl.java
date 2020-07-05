package com.holic.blog.service.implement;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.holic.blog.entity.example.ShowBlogForViewer;
import com.holic.blog.entity.example.ShowLatestBlogForViewer;
import com.holic.blog.entity.example.ShowTagForViewer;
import com.holic.blog.entity.example.ShowTypeForViewer;
import com.holic.blog.mapper.ExampleMapper;
import com.holic.blog.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author：HOLiC
 * @date：2020/7/5  12:55
 */
@Service
public class IndexServiceImpl implements IndexService {

    @Autowired
    private ExampleMapper exampleMapper;

    @Override
    public PageInfo<ShowBlogForViewer> listAllBlog(Integer pageNum, Integer pageSize) {

        PageHelper.startPage(pageNum, pageSize);
        List<ShowBlogForViewer> blog = exampleMapper.findAllBlog();
        PageInfo page = new PageInfo(blog);
        return page;

    }

    @Override
    public PageInfo<ShowBlogForViewer> listQueryBlog(Integer pageNum, Integer pageSize, String query) {

        PageHelper.startPage(pageNum, pageSize);
        List<ShowBlogForViewer> blog = exampleMapper.findBlogByQuery(query);
        PageInfo page = new PageInfo(blog);
        return page;
    }

    @Override
    public List<ShowTypeForViewer> listTypes() {
        List<ShowTypeForViewer> type = exampleMapper.fianTypeOfMostBlog();
        return type;
    }

    @Override
    public List<ShowTagForViewer> listTags() {
        List<ShowTagForViewer> tag = exampleMapper.fianTagOfMostBlog();
        return tag;
    }

    @Override
    public List<ShowLatestBlogForViewer> listLatestBlog() {
        List<ShowLatestBlogForViewer> blog = exampleMapper.findLatestBlog();
        return blog;
    }
}
