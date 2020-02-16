package com.majiang.community.provider;

import com.alibaba.fastjson.JSON;
import com.majiang.community.dto.AccessTokenDTO;
import com.majiang.community.dto.GithubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class GithubProvider {


    OkHttpClient client = new OkHttpClient();
    public String getAccessToken(AccessTokenDTO accessTokenDTO){
        final MediaType mediaType
                = MediaType.get("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDTO));
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        String str = null;
        try (Response response = client.newCall(request).execute()) {
            str = response.body().string();
            String s = str.split("&")[0];
            String token = s.split("=")[1];
            return token;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

    public GithubUser getUser(String accessToken){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url("https://api.github.com/user?access_token="+accessToken).build();
        String str = null;
        try {
            Response response = client.newCall(request).execute();
            str = response.body().string();
            GithubUser githubUser = JSON.parseObject(str, GithubUser.class);
            return githubUser;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
