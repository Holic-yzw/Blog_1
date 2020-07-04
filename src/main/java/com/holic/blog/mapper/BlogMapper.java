package com.holic.blog.mapper;

import com.holic.blog.entity.Blog;
import com.holic.blog.entity.Link;
import com.holic.blog.entity.example.SearchBlogForAdmin;
import com.holic.blog.entity.example.ShowBlogForAdmin;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author：HOLiC_y
 * @date：2020/6/14
 */
@Repository
public interface BlogMapper {

    List<ShowBlogForAdmin> findAllBlogBySearch(SearchBlogForAdmin blog);

    List<ShowBlogForAdmin> findAllBlog();

    Blog getBlogById(Long id);

    int saveBlog(Blog blog);

    int updateBlogById(Blog blog);

    int deleteBlogById(Long id);

    int countExistBlog(String title);

    int saveLink(List<Link> linkList);

    List<Long> getTagIdByBlogId(Long id);

    int deleteLinkByBlogId(Long id);

}
