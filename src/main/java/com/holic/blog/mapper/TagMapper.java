package com.holic.blog.mapper;

import com.holic.blog.entity.Tag;
import com.holic.blog.entity.example.ShowBlogForViewer;
import com.holic.blog.entity.example.ShowTagForViewer;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author：HOLiC_y
 * @date：2020/6/14 
 */
@Repository
public interface TagMapper {
    int saveTag(Tag tag);

    Tag getTagById(Long id);

    List<Tag> getTagByName(String  name);

    List<Tag> getTagByBlogId(Long  id);

    List<Tag> findAllTag();

    int updateTagById(Tag tag);

    int deleteTagById(Long id);

    List<ShowTagForViewer> findAllTagForViewer();

    List<ShowBlogForViewer> findBlogsByTagId(Long id);

    List<Tag> findTagsByBlogId(Long id);

}
