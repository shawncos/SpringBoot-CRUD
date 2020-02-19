package com.shawncos.sringbootcrud.community;


import com.alibaba.fastjson.JSON;
import com.shawncos.sringbootcrud.community.dto.GitHubUser;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
public class MyTest {


    public String run(String url) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            System.out.println(response.body().string());
            GitHubUser gitHubUser = JSON.parseObject(response.body().toString(), GitHubUser.class);
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Test
    public void testokhttp() {
        run("https://api.github.com/user?access_token=6ef5dd57f1ebb7275a63753fadb93b0751bc8814");
    }

}
