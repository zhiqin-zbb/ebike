package com.aaebike.mapper;

import org.springframework.stereotype.Repository;

import com.aaebike.common.utils.MyMapper;
import com.aaebike.entity.table.User;

@Repository
public interface UserMapper extends MyMapper<User> {
    User getUserById(Integer id);
}
