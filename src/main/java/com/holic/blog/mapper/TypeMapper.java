package com.holic.blog.mapper;

import com.holic.blog.entity.Type;
import com.holic.blog.entity.example.ShowBlogForViewer;
import com.holic.blog.entity.example.ShowLatestBlogForViewer;
import com.holic.blog.entity.example.ShowTypeForViewer;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Title: TypeMapper
 * @Description: 分类业务持久层
 * @author: Administrator
 * @date: 2020/6/5
 */
@Repository
public interface TypeMapper {

    int saveType(Type type);

    Type getTypeById(Long id);

    List<Type> getTypeByName(String  name);

    List<Type> findAllType();

    int updateTypeById(Type type);

    int deleteTypeById(Long id);

    List<ShowTypeForViewer> findAllTypeForViewer();

    List<ShowBlogForViewer> findBlogsByTypeId(Long id);
}
