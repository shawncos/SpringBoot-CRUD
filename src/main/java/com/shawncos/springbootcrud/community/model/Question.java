package com.shawncos.springbootcrud.community.model;

import lombok.Data;


@Data
public class Question {

    private int id;

    private String title;
    private String description;


    private long gmtCreate;

    private long gmtModified;

    private int creator;

    private int commentCount;

    private int likeCount;

    private String tag;


}
