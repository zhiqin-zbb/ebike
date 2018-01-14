package com.aaebike.mapper;

import com.aaebike.common.utils.MyMapper;
import com.aaebike.model.User;

public interface UserMapper extends MyMapper<User> {
    User getUserById(Integer id);
}
