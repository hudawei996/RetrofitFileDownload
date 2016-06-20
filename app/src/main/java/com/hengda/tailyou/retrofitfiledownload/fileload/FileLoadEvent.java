package com.hengda.tailyou.retrofitfiledownload.fileload;

/**
 * 作者：Tailyou （祝文飞）
 * 时间：2016/5/27 11:21
 * 邮箱：tailyou@163.com
 * 描述：
 */
public class FileLoadEvent {
    /**
     * 文件大小
     */
    long total;
    /**
     * 已下载大小
     */
    long progress;

    public long getProgress() {
        return progress;
    }

    public long getTotal() {
        return total;
    }

    public FileLoadEvent(long total, long progress) {
        this.total = total;
        this.progress = progress;
    }
}
