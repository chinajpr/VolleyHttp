package com.jpr.volleyhttp.http;

import android.util.Log;

import java.util.Objects;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 类描述:
 * 创建日期:2018/2/10 on 15:02
 * 作者:JiaoPeiRong
 */

public class ThreadPoolManager {
    private static ThreadPoolManager instance = new ThreadPoolManager();
    /**
     * 阻塞式队列
     */
//    private LinkedBlockingQueue<Future<?>> taskQueue = new LinkedBlockingQueue<>();
    private BlockingQueue<Future<?>> taskQueue = new ArrayBlockingQueue<>(4);
    private ThreadPoolExecutor threadPoolExecutor;

    /**
     * 单例
     *
     * @return
     */
    public static ThreadPoolManager getInstance() {
        return instance;
    }

    int i = 0;
    /**
     * 线程池的拒绝策略
     */
    private RejectedExecutionHandler handler = new RejectedExecutionHandler() {
        @Override
        public void rejectedExecution(Runnable runnable, ThreadPoolExecutor threadPoolExecutor) {
            try {
//                Log.d("jiaopeirong" , taskQueue.size() + "----------------");
                taskQueue.put(new FutureTask<Object>(runnable, null));
//                i++;
//                Log.d("jiaopeirong" ,  "---------" + i);
//                Log.d("jiaopeirong" , taskQueue.size() + "++++++++++++++++++++");

//                new Thread(runnable,"新线程:"+new Random().nextInt(10)).start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    private ThreadPoolManager() {
        /**
         * 核心线程数
         * 最大线程数
         * 保持线程时间
         * 保持线程时间单位
         * 阻塞式队列,保持线程池的开销
         * 拒绝策略
         *
         */
        threadPoolExecutor = new ThreadPoolExecutor(4, 10, 10, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(4), handler);
        threadPoolExecutor.execute(runable);
    }

    public <T> void execte(FutureTask<T> futureTask) throws InterruptedException {
        taskQueue.put(futureTask);
    }

    private Runnable runable = new Runnable() {
        @Override
        public void run() {
            while (true) {
                FutureTask futureTask = null;
                try {
                    /**
                     * 阻塞式函数,有,则取;无则阻塞
                     */
                    Log.i("jiao", "等待队列--->" + taskQueue.size());
                    futureTask = (FutureTask) taskQueue.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (futureTask != null) {
                    threadPoolExecutor.execute(futureTask);
                }
                Log.i("jiao", "线程池大小--->" + threadPoolExecutor.getPoolSize());
            }
        }
    };

}
