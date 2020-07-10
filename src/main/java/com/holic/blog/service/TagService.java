package com.holic.blog.service;

import com.github.pagehelper.PageInfo;
import com.holic.blog.entity.Tag;
import com.holic.blog.entity.example.ShowBlogForViewer;
import com.holic.blog.entity.example.ShowTagForViewer;

import java.util.List;

/**
 * @author：HOLiC_y
 * @date：2020/6/14 0014
 */
public interface TagService {

    int saveType(Tag tag);

    Tag getTag(Long id);

    List<Tag> getTagByName(String name);

    List<Tag> getTagByBlogId(Long id);

    PageInfo<Tag> listTag(Integer pageNum, Integer pageSize);

    int updateTag(Tag tag);

    int deleteTag(Long id);

    List<Tag> findAllTag();

    List<ShowTagForViewer> listAllTagForViewer();

    PageInfo<ShowBlogForViewer> listBlogsByTypeId(Integer pageNum, Integer pageSize, Long id);
}
