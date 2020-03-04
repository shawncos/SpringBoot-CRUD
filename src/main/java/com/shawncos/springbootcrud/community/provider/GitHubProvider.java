package com.shawncos.springbootcrud.community.provider;


import com.alibaba.fastjson.JSON;
import com.shawncos.springbootcrud.community.dto.AccessTokenDTO;
import com.shawncos.springbootcrud.community.dto.GitHubUser;

import okhttp3.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Component
public class GitHubProvider {

    public String getAccessToken(AccessTokenDTO accessTokenDto) {
        String content = "client_id=" + accessTokenDto.getClient_id() + "&client_secret=" + accessTokenDto.getClient_secret() + "&code=" + accessTokenDto.getCode();
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        RequestBody body = RequestBody.create(mediaType, content);
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .method("POST", body)
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .build();
        try {
            Response response = client.newCall(request).execute();
            return response.body().string().split("&")[0].split("=")[1];
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public GitHubUser getUser(String accessToken) {
        String url = "https://api.github.com/user?access_token=" + accessToken;
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return JSON.parseObject(response.body().string(), GitHubUser.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
