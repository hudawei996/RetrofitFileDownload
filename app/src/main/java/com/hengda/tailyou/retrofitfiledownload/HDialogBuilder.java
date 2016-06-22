package com.hengda.tailyou.retrofitfiledownload;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


public class HDialogBuilder extends Dialog {

    private LinearLayout rootPanel;
    private LinearLayout topPanel;
    private LinearLayout customPanel;
    private View mDivider;
    private TextView mTitle;
    private TextView mMsg;
    private ImageView mIcon;
    private Button mBtnP;
    private Button mBtnN;

    public HDialogBuilder(Context context) {
        super(context, R.style.hd_dialog_dim);
        init(context);
    }

    public HDialogBuilder(Context context, int theme) {
        super(context, theme);
        init(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.width = ViewGroup.LayoutParams.WRAP_CONTENT;
        params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        getWindow().setAttributes(params);
    }


    private void init(Context context) {
        View dialogContainer = View.inflate(context, R.layout.layout_hd_builder_dialog, null);

        rootPanel = (LinearLayout) dialogContainer.findViewById(R.id.rootPanel);
        topPanel = (LinearLayout) dialogContainer.findViewById(R.id.topPanel);
        customPanel = (LinearLayout) dialogContainer.findViewById(R.id.customPanel);
        mIcon = (ImageView) dialogContainer.findViewById(R.id.icon);
        mDivider = dialogContainer.findViewById(R.id.titleDivider);
        mTitle = (TextView) dialogContainer.findViewById(R.id.alertTitle);
        mMsg = (TextView) dialogContainer.findViewById(R.id.alertMsg);
        mBtnP = (Button) dialogContainer.findViewById(R.id.dialog_btn_p);
        mBtnN = (Button) dialogContainer.findViewById(R.id.dialog_btn_n);

        setContentView(dialogContainer);

    }

    /**
     * 设置标题
     *
     * @param title
     * @return
     */
    public HDialogBuilder title(CharSequence title) {
        topPanel.setVisibility(View.VISIBLE);
        mTitle.setText(title);
        return this;
    }

    /**
     * 设置标题
     *
     * @param title
     * @return
     */
    public HDialogBuilder title(int title) {
        topPanel.setVisibility(View.VISIBLE);
        mTitle.setText(title);
        return this;
    }

    /**
     * 设置标题文字颜色
     *
     * @param color
     * @return
     */
    public HDialogBuilder titleColor(int color) {
        mDivider.setVisibility(View.VISIBLE);
        mTitle.setTextColor(color);
        return this;
    }

    /**
     * 设置Divider颜色
     *
     * @param color
     * @return
     */
    public HDialogBuilder dividerColor(int color) {
        mDivider.setVisibility(View.VISIBLE);
        mDivider.setBackgroundColor(color);
        return this;
    }

    /**
     * 设置消息
     *
     * @param msg
     * @return
     */
    public HDialogBuilder message(CharSequence msg) {
        mMsg.setVisibility(View.VISIBLE);
        mMsg.setText(msg);
        return this;
    }

    /**
     * 设置消息
     *
     * @param msg
     * @return
     */
    public HDialogBuilder message(int msg) {
        mMsg.setVisibility(View.VISIBLE);
        mMsg.setText(msg);
        return this;
    }

    /**
     * 设置消息文字颜色
     *
     * @param color
     * @return
     */
    public HDialogBuilder msgColor(int color) {
        mMsg.setTextColor(color);
        return this;
    }

    /**
     * 设置Dialog背景颜色
     *
     * @param color
     * @return
     */
    public HDialogBuilder dialogColor(int color) {
        rootPanel.setBackgroundColor(color);
        return this;
    }

    /**
     * 设置Icon
     *
     * @param drawableResId
     * @return
     */
    public HDialogBuilder withIcon(int drawableResId) {
        mIcon.setImageResource(drawableResId);
        return this;
    }

    /**
     * 设置按钮背景（自定义XML）
     *
     * @param resid
     * @return
     */
    public HDialogBuilder btnBg(int resid) {
        mBtnP.setBackgroundResource(resid);
        mBtnN.setBackgroundResource(resid);
        return this;
    }

    /**
     * 确定按钮文字
     *
     * @param text
     * @return
     */
    public HDialogBuilder pBtnText(CharSequence text) {
        mBtnP.setVisibility(View.VISIBLE);
        mBtnP.setText(text);
        return this;
    }

    /**
     * 确定按钮文字
     *
     * @param text
     * @return
     */
    public HDialogBuilder pBtnText(int text) {
        mBtnP.setVisibility(View.VISIBLE);
        mBtnP.setText(text);
        return this;
    }

    /**
     * 取消按钮文字
     *
     * @param text
     * @return
     */
    public HDialogBuilder nBtnText(CharSequence text) {
        mBtnN.setVisibility(View.VISIBLE);
        mBtnN.setText(text);
        return this;
    }

    /**
     * 取消按钮文字
     *
     * @param text
     * @return
     */
    public HDialogBuilder nBtnText(int text) {
        mBtnN.setVisibility(View.VISIBLE);
        mBtnN.setText(text);
        return this;
    }

    /**
     * 确定按钮监听
     *
     * @param click
     * @return
     */
    public HDialogBuilder pBtnClickListener(View.OnClickListener click) {
        mBtnP.setOnClickListener(click);
        return this;
    }

    /**
     * 取消按钮监听
     *
     * @param click
     * @return
     */
    public HDialogBuilder nBtnClickListener(View.OnClickListener click) {
        mBtnN.setOnClickListener(click);
        return this;
    }

    /**
     * 自定义Dialog主体部分
     *
     * @param context
     * @param layoutId
     * @return
     */
    public HDialogBuilder setCustomView(Context context, int layoutId) {
        View customView = View.inflate(context, layoutId, null);
        if (customPanel.getChildCount() > 0) {
            customPanel.removeAllViews();
        }
        customPanel.addView(customView);
        customPanel.setVisibility(View.VISIBLE);
        return this;
    }

    /**
     * 自定义Dialog主体部分
     *
     * @param view
     * @return
     */
    public HDialogBuilder setCustomView(View view) {
        if (customPanel.getChildCount() > 0) {
            customPanel.removeAllViews();
        }
        customPanel.addView(view);
        customPanel.setVisibility(View.VISIBLE);
        return this;
    }

    /**
     * 设置是否可以取消
     *
     * @param cancelable
     * @return
     */
    public HDialogBuilder cancelable(boolean cancelable) {
        setCancelable(cancelable);
        return this;
    }

    /**
     * 设置是否可以点击周围取消
     *
     * @param outsideCancelable
     * @return
     */
    public HDialogBuilder outsideCancelable(boolean outsideCancelable) {
        setCanceledOnTouchOutside(outsideCancelable);
        return this;
    }

    /**
     * 设置字体
     *
     * @param typeface
     * @return
     */
    public HDialogBuilder setTypeface(Typeface typeface) {
        mTitle.setTypeface(typeface);
        mMsg.setTypeface(typeface);
        mBtnP.setTypeface(typeface);
        mBtnN.setTypeface(typeface);
        return this;
    }

}
