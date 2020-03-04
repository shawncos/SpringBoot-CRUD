package com.shawncos.springbootcrud.community.controller;


import com.shawncos.springbootcrud.community.dto.PaginationDTO;
import com.shawncos.springbootcrud.community.mapper.UserMapper;
import com.shawncos.springbootcrud.community.model.User;
import com.shawncos.springbootcrud.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ProfileCustomController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionService questionService;

    @GetMapping("/profile/{action}")
    public String profile(@PathVariable(name = "action", value = "") String action, @RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "size", defaultValue = "2") Integer size, Model model, HttpServletRequest request) {
        if ("question".equals(action)) {
            model.addAttribute("section", "questions");
            model.addAttribute("sectionname", "我的提问");
        }
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return "redirect:/index";
        }

        PaginationDTO pagination = questionService.list(user.getId(), page, size);
        model.addAttribute("pagination", pagination);
        return "profile";
    }
}
