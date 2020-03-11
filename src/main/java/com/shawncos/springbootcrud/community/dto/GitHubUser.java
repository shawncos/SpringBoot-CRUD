package com.shawncos.springbootcrud.community.dto;


import lombok.Data;

@Data
public class GitHubUser {

    private String name;
    private String id;

    private String bio;


    private String avatarUrl;

}
