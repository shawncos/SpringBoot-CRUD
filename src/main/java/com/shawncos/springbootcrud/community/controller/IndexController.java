package com.shawncos.springbootcrud.community.controller;


import com.shawncos.springbootcrud.community.mapper.UserMapper;
import com.shawncos.springbootcrud.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/")
    public String index(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if(cookies!=null){
            for (Cookie cookie : cookies) {
                if("token".equals(cookie.getName())){
                    User user=userMapper.getUserByToken(cookie.getValue());
                    if(user!=null){
                        request.getSession().setAttribute("user",user);
                    }
                    break;
                }
            }
        }

        return  "index";
    }

    @GetMapping("/index")
    public String index(){
        return "index";
    }

}
