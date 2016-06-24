package com.hengda.tailyou.retrofitfiledownload;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.hengda.tailyou.retrofitfiledownload.fileload.FileApi;
import com.hengda.tailyou.retrofitfiledownload.fileload.FileCallback;

import java.io.File;

import okhttp3.ResponseBody;
import retrofit2.Call;

public class MainActivity extends AppCompatActivity {

    TextView txtProgress;
    HDialogBuilder hDialogBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //        下载地址
        //        http://hengdawb-app.oss-cn-hangzhou.aliyuncs.com/app-debug.apk
        String baseUrl = "http://hengdawb-app.oss-cn-hangzhou.aliyuncs.com/";
        String fileName = "app-debug.apk";
        String fileStoreDir = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "M_DEFAULT_DIR";
        String fileStoreName = fileName;
        showLoadingDialog();
        FileApi.getInstance(baseUrl).loadFileByName(fileName, new FileCallback(fileStoreDir, fileStoreName) {
                    @Override
                    public void onSuccess(File file) {
                        super.onSuccess(file);
                        hDialogBuilder.dismiss();
                    }

                    @Override
                    public void progress(long progress, long total) {
                        updateProgress(progress, total);
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        hDialogBuilder.dismiss();
                        call.cancel();
                    }

                });
    }

    /**
     * 更新下载进度
     *
     * @param progress
     * @param total
     */
    private void updateProgress(long progress, long total) {
        txtProgress.setText(String.format("正在下载：(%s/%s)",
                DataManager.getFormatSize(progress),
                DataManager.getFormatSize(total)));
    }

    /**
     * 显示下载对话框
     */
    private void showLoadingDialog() {
        txtProgress = (TextView) View.inflate(MainActivity.this, R.layout
                .layout_hd_dialog_custom_tv, null);
        hDialogBuilder = new HDialogBuilder(MainActivity.this);
        hDialogBuilder.setCustomView(txtProgress)
                .title("下载")
                .nBtnText("取消")
                .nBtnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        hDialogBuilder.dismiss();
                    }
                })
                .show();
    }

}
