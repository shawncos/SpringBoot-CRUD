package com.shawncos.springbootcrud.community.service;


import com.shawncos.springbootcrud.community.dto.PaginationDTO;
import com.shawncos.springbootcrud.community.dto.QuestionDTO;
import com.shawncos.springbootcrud.community.mapper.QuestionMapper;
import com.shawncos.springbootcrud.community.mapper.UserMapper;
import com.shawncos.springbootcrud.community.model.Question;
import com.shawncos.springbootcrud.community.model.QuestionExample;
import com.shawncos.springbootcrud.community.model.User;
import com.shawncos.springbootcrud.community.model.UserExample;
import org.apache.ibatis.session.RowBounds;
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

        List<Question> questionList = questionMapper.selectByExampleWithBLOBsWithRowbounds(new QuestionExample(),new RowBounds(offset,size));
        List<QuestionDTO> questionDTOList = new ArrayList<>();

        PaginationDTO paginationDTO = new PaginationDTO();

        for (Question q : questionList) {
            User u = userMapper.selectByPrimaryKey(q.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(q, questionDTO);
            questionDTO.setUser(u);
            questionDTOList.add(questionDTO);
        }
        Integer totalCount = (int)questionMapper.countByExample(new QuestionExample());
        paginationDTO.setQuestion(questionDTOList);
        paginationDTO.setPagination(totalCount, page, size);
        return paginationDTO;
    }

    public PaginationDTO list(int id, Integer page, Integer size) {
        Integer offset = size * (page - 1);
        QuestionExample example=new QuestionExample();
        example.createCriteria().andCreatorEqualTo(id);
        List<Question> questionList =questionMapper.selectByExampleWithBLOBsWithRowbounds(example,new RowBounds(offset,size));
        List<QuestionDTO> questionDTOList = new ArrayList<>();

        PaginationDTO paginationDTO = new PaginationDTO();
        for (Question q : questionList) {
            User u = userMapper.selectByPrimaryKey(q.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(q, questionDTO);
            questionDTO.setUser(u);
            questionDTOList.add(questionDTO);
        }

        QuestionExample questionExample=new QuestionExample();
        questionExample.createCriteria().andCreatorEqualTo(id);
        Integer totalCount = (int)questionMapper.countByExample(questionExample);
        paginationDTO.setQuestion(questionDTOList);
        paginationDTO.setPagination(totalCount, page, size);
        return paginationDTO;
    }

    public QuestionDTO getByID(Integer id) {
        Question question = questionMapper.selectByPrimaryKey(id);
        User user = userMapper.selectByPrimaryKey(question.getCreator());
        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question, questionDTO);
        questionDTO.setUser(user);
        return questionDTO;
    }

    public void createOrUpdate(Question question) {
        if(question.getId()==null){
            questionMapper.insert(question);
        }else{
            question.setGmtModified(System.currentTimeMillis());
            questionMapper.updateByPrimaryKey(question);
        }
    }
}
