package com.shawncos.springbootcrud.community.mapper;


import com.shawncos.springbootcrud.community.dto.QuestionDTO;
import com.shawncos.springbootcrud.community.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface QuestionMapper {


    @Insert("Insert into question(title,description,gmt_create,gmt_modified,creator,tag) value (#{title},#{description},#{gmtCreate},#{gmtModified},#{creator},#{tag})")
    void create(Question question);


    @Select("select * from question limit #{offset},#{size}")
    List<Question> list(@Param("offset") Integer offset, @Param("size") Integer size);

    @Select("select * from question where creator=#{userid} limit #{offset},#{size}")
    List<Question> myList(@Param("userid") Integer userid, @Param("offset") Integer offset, @Param("size") Integer size);

    @Select("select count(*) from question")
    Integer count();

    @Select("select count(*) from question where creator=#{userid}")
    Integer myCount(@Param("userid") Integer userid);

    @Select("select * from question where id=#{id}")
    Question getByID(@Param("id") Integer id);
}
