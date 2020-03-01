package com.shawncos.springbootcrud.community.service;


import com.shawncos.springbootcrud.community.dto.PaginationDTO;
import com.shawncos.springbootcrud.community.dto.QuestionDTO;
import com.shawncos.springbootcrud.community.mapper.QuestionMapper;
import com.shawncos.springbootcrud.community.mapper.UserMapper;
import com.shawncos.springbootcrud.community.model.Question;
import com.shawncos.springbootcrud.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionMapper questionMapper;

    public PaginationDTO list(Integer page, Integer size) {

        Integer offset = size * (page - 1);

        List<Question> questionList = questionMapper.list(offset, size);
        List<QuestionDTO> questionDTOList = new ArrayList<>();

        PaginationDTO paginationDTO = new PaginationDTO();

        for (Question q : questionList) {
            User u = userMapper.getUserById(q.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(q, questionDTO);
            questionDTO.setUser(u);
            questionDTOList.add(questionDTO);
        }
        Integer totalCount = questionMapper.count();
        paginationDTO.setQuestion(questionDTOList);
        paginationDTO.setPagination(totalCount, page, size);
        return paginationDTO;
    }
}
