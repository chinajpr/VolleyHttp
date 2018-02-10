package com.jpr.volleyhttp.http.interfaces;

/**
 * 类描述:请求网络数据接口
 * 创建日期:2018/2/10 on 11:21
 * 作者:JiaoPeiRong
 */

public interface IHttpService {

    /**
     * 设置url
     * @param url
     */
    void setUrl(String url);

    /**
     * 执行网络
     */
    void excute();

    /**
     * 设置处理数据接口
     * @param httpListener
     */
    void setHttpListener(IHttpListener httpListener);

    /**
     * 设置请求参数
     */
    void setRequestData(byte[] requestData);
}
