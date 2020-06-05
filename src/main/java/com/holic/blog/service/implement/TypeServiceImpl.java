package com.holic.blog.service.implement;

import com.holic.blog.entity.Type;
import com.holic.blog.mapper.TypeMapper;
import com.holic.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
    public Page<Type> listType(Pageable pageable) {
        return null;
    }

    @Override
    public int updateType(Type type) {
        return 0;
    }

    @Override
    public int deleteType(Long id) {
        int flag = typeMapper.deleteTypeById(id);
        return flag;
    }
}
