package com.shawncos.springbootcrud.community.service;


import com.shawncos.springbootcrud.community.mapper.UserMapper;
import com.shawncos.springbootcrud.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    public void createOrUpdate(User user) {
        User userByAccountId = userMapper.getUserByAccountId(user.getAccountId());

        if(Objects.isNull(userByAccountId)){
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(System.currentTimeMillis());
            userMapper.insertUser(user);
        }else{
            userByAccountId.setGmtModified(System.currentTimeMillis());
            userByAccountId.setAvatarUrl(user.getAvatarUrl());
            userByAccountId.setName(user.getName());
            userByAccountId.setToken(user.getToken());
            userMapper.update(userByAccountId);

        }
    }
}
