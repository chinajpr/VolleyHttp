package com.jpr.volleyhttp.http.interfaces;

/**
 * 类描述:回调给UI页面的接口
 * 创建日期:2018/2/10 on 11:22
 * 作者:JiaoPeiRong
 */

public interface IDataListener<M> {
    /**
     * 回调结果给调用层
     * @param m
     */
    void onSuccess(M m);

    void onFail();

}
