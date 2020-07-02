package com.holic.blog.mapper;

import com.holic.blog.entity.Blog;
import com.holic.blog.entity.example.ExampleForSearchBlog;
import com.holic.blog.entity.example.ExampleForShowBlog;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description：TODO
 * @author：HOLiC_y
 * @date：2020/6/14 0014
 */
@Repository
public interface BlogMapper {

    List<ExampleForShowBlog> findAllBlogBySearch(ExampleForSearchBlog blog);

    List<ExampleForShowBlog> findAllBlog();

    Blog getBlogById(Long id);

    int saveBlog(Blog blog);

    int updateBlogById(Blog blog);

    int deleteBlogById(Long id);

    int countExistBlog(String title);
}
