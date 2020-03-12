package com.shawncos.springbootcrud.community.service;


import com.shawncos.springbootcrud.community.mapper.UserMapper;
import com.shawncos.springbootcrud.community.model.User;
import com.shawncos.springbootcrud.community.model.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    public void createOrUpdate(User user) {
        UserExample example=new UserExample();
        example.createCriteria().andAccountidEqualTo(user.getAccountid());
        List<User> users = userMapper.selectByExample(example);

        if(CollectionUtils.isEmpty(users)){

            user.setGmtcreate(System.currentTimeMillis());
            user.setGmtmodified(System.currentTimeMillis());
            userMapper.insert(user);
        }else{
            User userSelect=users.get(0);
            userSelect.setGmtmodified(System.currentTimeMillis());
            userSelect.setAvatarUrl(user.getAvatarUrl());
            userSelect.setName(user.getName());
            userSelect.setToken(user.getToken());
            userMapper.updateByPrimaryKey(userSelect);
        }
    }
}
