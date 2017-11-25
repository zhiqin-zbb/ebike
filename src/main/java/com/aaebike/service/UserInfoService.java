package com.aaebike.service;

import com.aaebike.mapper.UserInfoMapper;
import com.aaebike.model.UserInfo;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserInfoService {
    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void save(UserInfo user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        if (user.getId() != null) {
            userInfoMapper.updateByPrimaryKey(user);
        } else {
            userInfoMapper.insert(user);
        }
    }

    public UserInfo findByUsername(String username) {
        UserInfo userInfoExample = new UserInfo();
        userInfoExample.setUsername(username);
        return userInfoMapper.selectOne(userInfoExample);
    }

    public List<UserInfo> getAll(UserInfo UserInfo) {
        if (UserInfo.getPage() != null && UserInfo.getRows() != null) {
            PageHelper.startPage(UserInfo.getPage(), UserInfo.getRows());
        }
        return userInfoMapper.selectAll();
    }

    public UserInfo getById(Integer id) {
        return userInfoMapper.selectByPrimaryKey(id);
    }

    public void deleteById(Integer id) {
        userInfoMapper.deleteByPrimaryKey(id);
    }
}
