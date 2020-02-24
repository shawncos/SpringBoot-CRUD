package com.shawncos.springbootcrud.community.mapper;


import com.shawncos.springbootcrud.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {


    @Insert("INSERT INTO user (name,accountId,token,gmtCreate,gmtModified) value (#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified})")
    void insertUser(User user);

    @Select("select * from user where token=#{token}")
    User getUserByToken(@Param("token") String token);
}
