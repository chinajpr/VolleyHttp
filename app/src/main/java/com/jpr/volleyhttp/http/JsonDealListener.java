package com.jpr.volleyhttp.http;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.jpr.volleyhttp.http.interfaces.IDataListener;
import com.jpr.volleyhttp.http.interfaces.IHttpListener;

import org.apache.http.HttpEntity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;

/**
 * 类描述:
 * 创建日期:2018/2/10 on 11:34
 * 作者:JiaoPeiRong
 */

public class JsonDealListener<M> implements IHttpListener {
    private Class<M> responese;

    /**
     * 回调给调用层的接口
     */
    private IDataListener<M> dataListener;
    /**
     * 用于线程切换
     */
    private Handler handler = new Handler(Looper.getMainLooper());

    public JsonDealListener(Class<M> responese, IDataListener<M> dataListener) {
        this.responese = responese;
        this.dataListener = dataListener;
    }

    @Override
    public void onSSuccess(HttpEntity httpEntity) {
        InputStream inputStream = null;
        try {
            inputStream = httpEntity.getContent();
            //得到网络返回的数据
            String content = getContent(inputStream);
            //将String 转基类
            final M m = JSON.parseObject(content, responese);
            //由子线程切到主线程
            handler.post(new Runnable() {
                @Override
                public void run() {
                    dataListener.onSuccess(m);
                }
            });

        } catch (IOException e) {
            dataListener.onFail();
        }
    }

    /**
     * 从流中获取数据
     *
     * @param inputStream
     * @return
     */
    private String getContent(InputStream inputStream) {
        String content = null;
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            StringBuilder sb = new StringBuilder();

            String line = null;

            try {

                while ((line = reader.readLine()) != null) {

                    sb.append(line + "\n");

                }

            } catch (IOException e) {
                dataListener.onFail();
                System.out.println("Error=" + e.toString());

            } finally {

                try {

                    inputStream.close();

                } catch (IOException e) {

                    System.out.println("Error=" + e.toString());

                }

            }
            return sb.toString();

        } catch (Exception e) {
            e.printStackTrace();
            dataListener.onFail();
        }
        return content;
    }


    @Override
    public void onFail() {
        dataListener.onFail();
    }
}
