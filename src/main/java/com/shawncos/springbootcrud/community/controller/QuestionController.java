package com.shawncos.springbootcrud.community.controller;


import com.shawncos.springbootcrud.community.dto.QuestionDTO;
import com.shawncos.springbootcrud.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/question/{id}")
    public String question(@PathVariable("id") Integer id, Model model) {
        QuestionDTO questionDTO = questionService.getByID(id);
        model.addAttribute("question", questionDTO);
        return "question";
    }
}
