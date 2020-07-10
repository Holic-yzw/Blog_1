package com.holic.blog.service.implement;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.holic.blog.entity.Type;
import com.holic.blog.entity.example.ShowBlogForViewer;
import com.holic.blog.entity.example.ShowTypeForViewer;
import com.holic.blog.mapper.TypeMapper;
import com.holic.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Title: TypeServiceImpl
 * @Description: type业务实现类
 * @author: Administrator
 * @date: 2020/6/5
 */
@Service
public class TypeServiceImpl  implements TypeService {

    @Autowired
    private TypeMapper typeMapper;

    @Transactional
    @Override
    public int saveType(Type type) {
        int flag = typeMapper.saveType(type);
        return flag;
    }

    @Override
    public Type getType(Long id) {
        Type type = typeMapper.getTypeById(id);
        return type;
    }

    @Override
    public List<Type> getTypeByName(String name) {
        List<Type> typeList = typeMapper.getTypeByName(name);
        return typeList;
    }

    @Transactional
    @Override
    public int updateType(Type type) {
        int flag = typeMapper.updateTypeById(type);
        return flag;
    }

    @Transactional
    @Override
    public int deleteType(Long id) {
        int flag = typeMapper.deleteTypeById(id);
        return flag;
    }

    @Override
    public List<Type> findAllType() {
        List<Type> allType = typeMapper.findAllType();
        return allType;
    }

    @Override
    public List<ShowTypeForViewer> listAllTypeForViewer() {
        List<ShowTypeForViewer> allTypeForViewer = typeMapper.findAllTypeForViewer();
        return allTypeForViewer;
    }

    @Override
    public PageInfo<ShowBlogForViewer> liatBlogsByTypeId(Integer pageNum, Integer pageSize, Long id) {
        PageHelper.startPage(pageNum, pageSize);
        List<ShowBlogForViewer> blogs = typeMapper.findBlogsByTypeId(id);
        PageInfo page = new PageInfo(blogs);
        return page;
    }

    @Override
    public PageInfo<Type> listType(Integer pageNum, Integer pageSize) {
        // 获取第pageNum页，每一页pageSize条内容，默认查询总数count
        // 紧跟在startPage方法后的第一个MyBatis 查询方法会被进行分页
        PageHelper.startPage(pageNum, pageSize);
        List<Type> list = typeMapper.findAllType();
        // 用PageInfo对结果进行包装
        PageInfo page = new PageInfo(list);
        return page;
    }
}
