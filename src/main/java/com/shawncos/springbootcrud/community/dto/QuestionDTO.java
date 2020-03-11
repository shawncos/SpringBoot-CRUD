package com.shawncos.springbootcrud.community.dto;


import com.shawncos.springbootcrud.community.model.User;
import lombok.Data;

@Data
public class QuestionDTO {


    private int id;

    private String title;
    private String description;


    private long gmtCreate;

    private long gmtModified;

    private int creator;

    private int commentCount;

    private int likeCount;

    private String tag;


    private User user;

    @Override
    public String toString() {
        return "QuestionDTO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", gmtCreate=" + gmtCreate +
                ", gmtModified=" + gmtModified +
                ", creator=" + creator +
                ", commentCount=" + commentCount +
                ", likeCount=" + likeCount +
                ", tag='" + tag + '\'' +
                ", user=" + user +
                '}';
    }
}
