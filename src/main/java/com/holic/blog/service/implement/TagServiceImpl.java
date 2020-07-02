package com.holic.blog.service.implement;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.holic.blog.entity.Tag;
import com.holic.blog.mapper.TagMapper;
import com.holic.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author：HOLiC_y
 * @date：2020/6/14 0014
 */
@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagMapper tagMapper;

    @Transactional
    @Override
    public int saveType(Tag tag) {
        int i = tagMapper.saveTag(tag);
        return i;
    }

    @Override
    public Tag getTag(Long id) {
        Tag tag = tagMapper.getTagById(id);
        return tag;
    }

    @Override
    public List<Tag> getTagByName(String name) {
        List<Tag> tagList = tagMapper.getTagByName(name);
        return tagList;
    }

    @Override
    public PageInfo<Tag> listTag(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Tag> list = tagMapper.findAllTag();
        PageInfo page = new PageInfo(list);
        return page;
    }

    @Transactional
    @Override
    public int updateTag(Tag tag) {
        int i = tagMapper.updateTagById(tag);
        return i;
    }

    @Transactional
    @Override
    public int deleteTag(Long id) {
        int i = tagMapper.deleteTagById(id);
        return i;
    }

    @Override
    public List<Tag> findAllTag() {
        List<Tag> allTag = tagMapper.findAllTag();
        return allTag;
    }
}
