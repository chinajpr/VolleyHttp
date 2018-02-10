# VolleyHttp
##### 实现功能
- 高扩展.可根据需求请求文本,音频,图片等类型
- 支持高并发请求
- 响应发生在主线程
- 请求参数封装
- json自动解析

##### 使用到的知识点:
- 泛型
- 阻塞队列
- 线程池
- 线程池拒绝策略
- 模板方法模式
- 单例模式
- 策略模式
- 生产者消费者

##### 遗留问题
当同一时刻请求数量过多时,比如50条,会"遗漏"一些请求,既不会返回成功的回调,也不会返回失败的回调.在同一时刻请求比较少,比如20条,则没有这个问题.我使用Retrofit + Okhttp试了一下,在50条数据同时请求时,50条都会有回调.
