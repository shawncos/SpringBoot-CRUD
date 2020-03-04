package com.shawncos.springbootcrud.community.mapper;


import com.shawncos.springbootcrud.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {


    @Insert("INSERT INTO user (name,accountId,token,gmtCreate,gmtModified,avatar_url) value (#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified},#{avatarUrl})")
    void insertUser(User user);

    @Select("select * from user where token=#{token}")
    User getUserByToken(@Param("token") String token);

    @Select("select * from user where accountid=#{accountid}")
    User getUserByAccountId(@Param("accountid") long accountid);

    @Select("select * from user where id=#{id}")
    User getUserById(@Param("id") int creator);
}
