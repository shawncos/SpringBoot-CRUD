package com.shawncos.springbootcrud.community.controller;


import com.shawncos.springbootcrud.community.dto.PaginationDTO;
import com.shawncos.springbootcrud.community.dto.QuestionDTO;
import com.shawncos.springbootcrud.community.mapper.QuestionMapper;
import com.shawncos.springbootcrud.community.mapper.UserMapper;
import com.shawncos.springbootcrud.community.model.Question;
import com.shawncos.springbootcrud.community.model.User;
import com.shawncos.springbootcrud.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public String index(@RequestParam(value = "page",defaultValue = "1") Integer page,@RequestParam(value = "size",defaultValue = "2")Integer size, HttpServletRequest request, Model model){
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

        PaginationDTO pagination=questionService.list(page,size);
        model.addAttribute("pagination",pagination);

        return  "index";
    }

    @GetMapping("/index")
    public String index(){
        return "index";
    }

}
