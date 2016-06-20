package com.hengda.tailyou.retrofitfiledownload.ui;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;

import com.hengda.tailyou.retrofitfiledownload.R;
import com.hengda.tailyou.retrofitfiledownload.fileload.FileApi;
import com.hengda.tailyou.retrofitfiledownload.fileload.FileCallback;
import com.orhanobut.logger.Logger;

import java.io.File;

import okhttp3.ResponseBody;
import retrofit2.Call;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Logger.init("FILE_LOAD");

//        下载地址
//        http://hengdawb-app.oss-cn-hangzhou.aliyuncs.com/app-debug.apk

        String baseUrl = "http://hengdawb-app.oss-cn-hangzhou.aliyuncs.com/";
        String fileName = "app-debug.apk";
        String fileStoreDir = Environment.getExternalStorageDirectory()
                .getAbsolutePath() + File.separator + "M_DEFAULT_DIR";
        String fileStoreName = fileName;

        FileApi.getInstance(baseUrl).loadFileByName(fileName, new FileCallback(fileStoreDir,
                fileStoreName) {

            @Override
            public void onSuccess(File file) {
                super.onSuccess(file);
            }

            @Override
            public void progress(long progress, long total) {
                Logger.d(progress + "---" + total);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                call.cancel();
            }

        });
    }

}
