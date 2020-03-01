package com.shawncos.springbootcrud.community.provider;


import com.alibaba.fastjson.JSON;
import com.shawncos.springbootcrud.community.dto.AccessTokenDTO;
import com.shawncos.springbootcrud.community.dto.GitHubUser;

import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class GitHubProvider {

    public String getAccessToken(AccessTokenDTO accessTokenDto) {

        final MediaType mediaType
                = MediaType.get("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(JSON.toJSONString(accessTokenDto), mediaType);
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String s= response.body().string();
            System.out.println(s);
            return s.split("&")[0].split("=")[1];
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public GitHubUser getUser(String accessToken){
        String url="https://api.github.com/user?access_token="+accessToken;
        System.out.println(url);
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return JSON.parseObject(response.body().string(),GitHubUser.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
