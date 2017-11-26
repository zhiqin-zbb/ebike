package com.aaebike.service;

import com.aaebike.mapper.UserMapper;
import com.aaebike.model.User;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        if (user.getId() != null) {
            userMapper.updateByPrimaryKey(user);
        } else {
            userMapper.insert(user);
        }
    }

    public User findByUsername(String username) {
        User userExample = new User();
        userExample.setUsername(username);
        return userMapper.selectOne(userExample);
    }

    public int update(User user) {
        return userMapper.updateByPrimaryKeySelective(user);
    }

    public List<User> getAll(User User) {
        if (User.getPage() != null && User.getRows() != null) {
            PageHelper.startPage(User.getPage(), User.getRows());
        }
        return userMapper.selectAll();
    }

    public User getById(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    public void deleteById(Integer id) {
        userMapper.deleteByPrimaryKey(id);
    }
}
