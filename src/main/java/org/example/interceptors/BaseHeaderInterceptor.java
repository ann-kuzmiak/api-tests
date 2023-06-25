package org.example.interceptors;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class BaseHeaderInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request().newBuilder()
                .addHeader("Authorization", "token is here")
                .addHeader("Content-Type", "application/json").build();
        return chain.proceed(request);
    }
}
