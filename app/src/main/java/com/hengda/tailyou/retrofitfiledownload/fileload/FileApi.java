package com.hengda.tailyou.retrofitfiledownload.fileload;

import java.io.IOException;
import java.util.Hashtable;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;

/**
 * 作者：Tailyou （祝文飞）
 * 时间：2016/4/1 16:47
 * 邮箱：tailyou@163.com
 * 描述：Retrofit文件下载API
 */
public class FileApi {
    private static final int DEFAULT_TIMEOUT = 5;
    private Retrofit retrofit;
    private FileService fileService;
    private volatile static FileApi instance;
    private static Call<ResponseBody> call;

    private static Hashtable<String, FileApi> mFileApiTable;

    static {
        mFileApiTable = new Hashtable<>();
    }

    /**
     * 单例模式-私有构造函数
     *
     * @param baseUrl
     */
    private FileApi(String baseUrl) {
        retrofit = new Retrofit.Builder()
                .client(initOkHttpClient())
                .baseUrl(baseUrl)
                .build();
        fileService = retrofit.create(FileService.class);
    }

    /**
     * 获取实例
     *
     * @param baseUrl
     * @return
     */
    public static FileApi getInstance(String baseUrl) {
        instance = mFileApiTable.get(baseUrl);
        if (instance == null) {
            synchronized (FileApi.class) {
                if (instance == null) {
                    instance = new FileApi(baseUrl);
                    mFileApiTable.put(baseUrl, instance);
                }
            }
        }
        return instance;
    }

    /**
     * 下载文件
     *
     * @param fileName
     * @param callback
     */
    public void loadFileByName(String fileName, FileCallback callback) {
        call = fileService.loadFile(fileName);
        call.enqueue(callback);
    }

    /**
     * 取消下载
     */
    public static void cancelLoading() {
        if (call != null && call.isCanceled() == false) {
            call.cancel();
        }
    }

    /**
     * 初始化OkHttpClient
     *
     * @return
     */
    private OkHttpClient initOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        builder.networkInterceptors().add(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Response originalResponse = chain.proceed(chain.request());
                return originalResponse
                        .newBuilder()
                        .body(new FileResponseBody(originalResponse))
                        .build();
            }
        });
        return builder.build();
    }

}
