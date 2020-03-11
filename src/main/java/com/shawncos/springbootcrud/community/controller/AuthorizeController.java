package com.shawncos.springbootcrud.community.controller;


import com.shawncos.springbootcrud.community.dto.AccessTokenDTO;
import com.shawncos.springbootcrud.community.dto.GitHubUser;
import com.shawncos.springbootcrud.community.mapper.UserMapper;
import com.shawncos.springbootcrud.community.model.User;
import com.shawncos.springbootcrud.community.provider.GitHubProvider;
import com.shawncos.springbootcrud.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
public class AuthorizeController {


    @Autowired
    GitHubProvider gitHubProvider;

    @Autowired
    UserService userService;

    @Autowired
    UserMapper userMapper;

    @Value("${github.client.id}")
    private String client_id;

    @Value("${github.client.secret}")
    private String client_secret;

    @Value("${github.redirect.uri}")
    private String redirect_uri;

    @GetMapping("/callback")
    public String callback(@RequestParam("code") String code, @RequestParam("state") String state, HttpServletRequest request, HttpServletResponse response) {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(client_id);
        accessTokenDTO.setClient_secret(client_secret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(redirect_uri);
        accessTokenDTO.setState(state);
        String accessToken = gitHubProvider.getAccessToken(accessTokenDTO);
        GitHubUser gitHubUser = gitHubProvider.getUser(accessToken);
        if (gitHubUser != null) {
            User user = userMapper.getUserByAccountId(gitHubUser.getId());
            if (user != null) {
                Cookie cookie = new Cookie("token", user.getToken());
                response.addCookie(cookie);
            } else {
                user = new User();
                user.setAccountId(String.valueOf(gitHubUser.getId()));
                user.setName(gitHubUser.getName());
                user.setToken(UUID.randomUUID().toString());
                user.setGmtCreate(System.currentTimeMillis());
                user.setGmtModified(user.getGmtCreate());
                user.setAvatarUrl(gitHubUser.getAvatarUrl());
                userService.createOrUpdate(user);
                Cookie cookie = new Cookie("token", user.getToken());
                response.addCookie(cookie);
            }

            return "redirect:/";
        }
        return "index";
    }


    @GetMapping("logout")
    public String logOut(HttpServletRequest request,HttpServletResponse response){
        request.getSession().removeAttribute("user");
        Cookie cookie=new Cookie("token",null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return "redirect:/";
    }
}
