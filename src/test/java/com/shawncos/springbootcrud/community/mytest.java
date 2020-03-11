package com.shawncos.springbootcrud.community;


import okhttp3.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
public class mytest {

    @Test
    public void test() {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        RequestBody body = RequestBody.create(mediaType, "client_id=1de5676d5614f7505964&client_secret=634d58e6d4a80425bd5274de6fd56195170de79c&code=27dc26ee89c939e7efcc");
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .method("POST", body)
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .build();
        try {
            Response response = client.newCall(request).execute();
            System.out.println(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
