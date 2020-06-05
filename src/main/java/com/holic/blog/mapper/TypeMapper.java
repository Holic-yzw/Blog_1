package com.holic.blog.mapper;

import com.holic.blog.entity.Type;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

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

    Page<Type> listType(Pageable pageable);

    int updateTypeById(Type type);

    int deleteTypeById(Long id);
}
