package com.jpr.volleyhttp.http;

import com.jpr.volleyhttp.http.interfaces.IDataListener;
import com.jpr.volleyhttp.http.interfaces.IHttpListener;
import com.jpr.volleyhttp.http.interfaces.IHttpService;

import java.util.concurrent.FutureTask;

/**
 * 类描述:
 * 创建日期:2018/2/10 on 11:17
 * 作者:JiaoPeiRong
 */

public class Volley {
    /**
     * 暴露给调用层
     * @param <T> 请求参数类型
     * @param <M> 响应参数类型
     */
    public static <T,M> void sendRequest(T requestInfo, String url , Class<M> response, IDataListener dataListener){
        RequestHodler<T> requestHodler = new RequestHodler<>();
        requestHodler.setUrl(url);
        IHttpService httpService = new JsonHttpService();
        IHttpListener httpListener = new JsonDealListener<>(response , dataListener);
        requestHodler.setHttpListener(httpListener);
        requestHodler.setHttpService(httpService);

        HttpTask<T> httpTask = new HttpTask<>(requestHodler);

        try {
            ThreadPoolManager.getInstance().execte(new FutureTask<Object>(httpTask , null));
        } catch (InterruptedException e) {
            dataListener.onFail();
        }

    }
}
