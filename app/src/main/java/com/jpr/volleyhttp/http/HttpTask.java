package com.jpr.volleyhttp.http;

import com.alibaba.fastjson.JSON;
import com.jpr.volleyhttp.http.interfaces.IHttpListener;
import com.jpr.volleyhttp.http.interfaces.IHttpService;

import java.io.UnsupportedEncodingException;

/**
 * 类描述:
 * 创建日期:2018/2/10 on 14:18
 * 作者:JiaoPeiRong
 */

public class HttpTask<T> implements Runnable {
    private IHttpService httpService;

    public HttpTask(RequestHodler<T> requestHodler) {
        this.httpService = requestHodler.getHttpService();
        httpService.setHttpListener(requestHodler.getHttpListener());
        httpService.setUrl(requestHodler.getUrl());

        T request = requestHodler.getRequestInfo();
        String requestInfo = JSON.toJSONString(request);

        try {
            httpService.setRequestData(requestInfo.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        httpService.excute();
    }
}
