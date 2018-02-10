package com.jpr.volleyhttp.http;

import com.jpr.volleyhttp.http.interfaces.IHttpListener;
import com.jpr.volleyhttp.http.interfaces.IHttpService;

/**
 * 类描述:
 * 创建日期:2018/2/10 on 14:35
 * 作者:JiaoPeiRong
 */

public class RequestHodler<T> {
    /**
     * 执行网络下载
     */
    private IHttpService httpService;
    /**
     * 获取网络回调
     */
    private IHttpListener httpListener;
    /**
     * 请求返回实体
     */
    private T requestInfo;
    private String url;

    public IHttpService getHttpService() {
        return httpService;
    }

    public void setHttpService(IHttpService httpService) {
        this.httpService = httpService;
    }

    public IHttpListener getHttpListener() {
        return httpListener;
    }

    public void setHttpListener(IHttpListener httpListener) {
        this.httpListener = httpListener;
    }

    public T getRequestInfo() {
        return requestInfo;
    }

    public void setRequestInfo(T requestInfo) {
        this.requestInfo = requestInfo;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
