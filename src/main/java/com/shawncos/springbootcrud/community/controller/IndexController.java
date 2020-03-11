package com.shawncos.springbootcrud.community.controller;


import com.shawncos.springbootcrud.community.dto.PaginationDTO;
import com.shawncos.springbootcrud.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {


    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public String index(@RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "size", defaultValue = "2") Integer size, Model model) {


        PaginationDTO pagination = questionService.list(page, size);
        System.out.println(pagination);
        model.addAttribute("pagination", pagination);

        return "index";
    }

    @GetMapping("/index")
    public String index() {
        return "redirect:/";
    }

}
