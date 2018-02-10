package com.jpr.volleyhttp.http.interfaces;

import org.apache.http.HttpEntity;

/**
 * 类描述:IHttpService拿到json了,交由IHttpListener处理
 * 创建日期:2018/2/10 on 11:19
 * 作者:JiaoPeiRong
 */

public interface IHttpListener {

    void onSSuccess(HttpEntity httpEntity);

    void onFail();
}
