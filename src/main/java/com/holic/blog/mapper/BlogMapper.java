package com.holic.blog.mapper;

import com.holic.blog.entity.Blog;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description：TODO
 * @author：HOLiC_y
 * @date：2020/6/14 0014
 */
@Repository
public interface BlogMapper {

    List<Blog> findAllBlog(Blog blog);

    Blog getBlogById(Long id);

    int saveBlog(Blog blog);

    int updateBlogById(Blog blog);

    int deleteBlogById(Long id);
}
