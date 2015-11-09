package com.ufo.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

/**
 * Created by Administrator on 2015/7/26.
 */
public class MyHorizontalScrollView extends HorizontalScrollView{

    private static String[] chanels = {"页面1", "页面2", "页面3", "页面4", "页面5", "页面61", "页面7", "页面8", "页面9", "页面10","页面11","页面12"};
    private Context mContext;
    private int screenWidth;
    private RadioGroup rg;

    public MyHorizontalScrollView(Context context) {
        super(context);
    }

    public MyHorizontalScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        //获取屏的宽
        WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        screenWidth = manager.getDefaultDisplay().getWidth();
        //初始化指示器
        initChanels();
    }

    private void initChanels() {
        //初始化radiogroup
        rg = new RadioGroup(mContext);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        rg.setLayoutParams(params);
        rg.setOrientation(RadioGroup.HORIZONTAL);
        rg.setFadingEdgeLength(0);

        //初始化radiobutton
        RadioButton rb = null;

        int width = screenWidth / 6;

        for (int i = 0; i < chanels.length; i++) {
            rb = new RadioButton(mContext);
            LayoutParams rbParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            rb.setLayoutParams(rbParams);
            rb.setButtonDrawable(new BitmapDrawable());
            rb.setPadding(10, 10, 10, 10);
            rb.setGravity(Gravity.CENTER);
            rb.setTextColor(Color.GRAY);
            rb.setText(chanels[i]);
            rg.addView(rb);

        }

        this.addView(rg);
    }

    public RadioGroup getRadioGroup(){
        return rg;
    }
}
