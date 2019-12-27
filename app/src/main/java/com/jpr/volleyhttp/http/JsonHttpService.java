package com.jpr.volleyhttp.http;

import android.util.Log;

import com.jpr.volleyhttp.http.interfaces.IHttpListener;
import com.jpr.volleyhttp.http.interfaces.IHttpService;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;

/**
 * 类描述:
 * 创建日期:2018/2/10 on 11:47
 * 作者:JiaoPeiRong
 */

public class JsonHttpService implements IHttpService {
    private IHttpListener httpListener;
    private HttpClient httpClient = new DefaultHttpClient();
    private HttpPost httpPost;
    private String url;

    private byte[] requestDate;
    /**
     * httpClient获取网络的回调
     */
    private HttpResponceHandler httpResponceHandler = new HttpResponceHandler();

    @Override
    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public void excute() {
        httpPost = new HttpPost(url);
        ByteArrayEntity byteArrayEntity = new ByteArrayEntity(requestDate);
        httpPost.setEntity(byteArrayEntity);
        try {
            httpClient.execute(httpPost, httpResponceHandler);

        } catch (IOException e) {
            httpListener.onFail();
        }
    }

    @Override
    public void setHttpListener(IHttpListener httpListener) {
        this.httpListener = httpListener;
    }

    @Override
    public void setRequestData(byte[] requestData) {
        this.requestDate = requestData;
    }

    private class HttpResponceHandler extends BasicResponseHandler {
        @Override
        public String handleResponse(HttpResponse response) throws ClientProtocolException {
            //处理网络返回的结果
            int code = response.getStatusLine().getStatusCode();
            if (code == 200) {
                httpListener.onSSuccess(response.getEntity());
//                Log.d("jiao" , "json返回----->" + response.getEntity());
            } else {
                httpListener.onFail();
            }
            return null;
        }
    }
}
