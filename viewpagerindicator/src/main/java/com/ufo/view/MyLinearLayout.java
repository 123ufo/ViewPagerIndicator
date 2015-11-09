package com.ufo.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

/**
 * 作者： XuDiWei
 * <p/>
 * 日期：2015/7/27  14:31.
 * <p/>
 * 文件描述:
 */
public class MyLinearLayout extends LinearLayout {

    private Context mContext;
    private String[] title = {"页面1", "页面2", "页面3", "页面4", "页面5"};
    private RadioGroup rg;
    private ImageView vIndicator;

    public MyLinearLayout(Context context) {
        super(context);
        this.mContext = context;
        initView();
    }

    public MyLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        initView();
    }

    private void initView() {
        this.setOrientation(LinearLayout.VERTICAL);

        initChannel();
        this.addView(rg);

        initIndicator();
        this.addView(vIndicator);

    }


    /**
     * 初始化RadoiGroup
     */
    private void initChannel() {
        rg = new RadioGroup(mContext);
        rg.setOrientation(RadioGroup.HORIZONTAL);
        rg.setFadingEdgeLength(0);
        LinearLayout.LayoutParams rgParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        rg.setLayoutParams(rgParams);

        //向RadioGroup里添加RadioButton
        RadioButton rb = null;
        for (int i = 0; i < title.length; i++) {
            rb = new RadioButton(mContext);
            RadioGroup.LayoutParams rbParams = new RadioGroup.LayoutParams(RadioGroup.LayoutParams.WRAP_CONTENT, RadioGroup.LayoutParams.WRAP_CONTENT);
            rb.setLayoutParams(rbParams);
            rb.setButtonDrawable(new BitmapDrawable());
            rb.setGravity(Gravity.CENTER);
            rb.setText(title[i]);
            rb.setTextSize(20);
            rb.setTextColor(Color.GRAY);
            rb.setPadding(10, 10, 10, 10);
            rg.addView(rb);
        }
    }

    /**
     * 初始化指示器
     */
    private void initIndicator() {
        vIndicator = new ImageView(mContext);
        LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(100, 10);
        vIndicator.setLayoutParams(params1);
        vIndicator.setBackgroundColor(Color.RED);
    }


}
