package com.ufo.viewpagerindicator;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.ufo.view.MyHorizontalScrollView;


public class MainActivity extends Activity implements ViewPager.OnPageChangeListener, RadioGroup.OnCheckedChangeListener {

    private ViewPager viewPager;
    private Context mContext;
    private static String[] str = {"页面1", "页面2", "页面3", "页面4", "页面5", "页面6", "页面7", "页面8", "页面9", "页面10", "页面11", "页面12"};
    private MyHorizontalScrollView mhs;
    private RadioGroup rg;
    private RadioButton rb;
    private ImageView iv_scroll;

    private float fromX;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.mContext = this;
        initView();
    }

    private void initView() {

        mhs = (MyHorizontalScrollView) findViewById(R.id.mhs);
        iv_scroll = (ImageView) findViewById(R.id.iv_scroll);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setAdapter(new MyAdapter());
        viewPager.setOnPageChangeListener(this);

        //获取按钮的宽度
        rg = mhs.getRadioGroup();
        rb = (RadioButton) rg.getChildAt(0);
        rg.setOnCheckedChangeListener(this);


    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        int total = (int) ((position + positionOffset) * rb.getWidth());
        int green = (viewPager.getWidth() - rb.getWidth()) / 2;
        int scroll = total - green;
        System.out.println("scroll:" + scroll);
        //当scroll大小0时执行这个方法才会有效，也就是说指示器滚动过的长度大于屏幕的一半减于一个rb的宽度。
        mhs.scrollTo(scroll, 0);

        imgScroll(position, positionOffset);

    }

    /**
     * 图片滚动
     * @param position
     * @param positionOffset
     */
    private void imgScroll(int position, float positionOffset) {

        RadioButton rb1 = (RadioButton) rg.getChildAt(position);
        int[] location = new int[2];
        rb1.getLocationInWindow(location);
        float toX = location[0] + positionOffset * rb1.getWidth();

        //图片滚动
        TranslateAnimation ta = new TranslateAnimation(fromX, toX, 0, 0);
//        System.out.println("fromX:" + fromX);
//        System.out.println("tox:" + toX);
        ta.setDuration(50);
        ta.setFillAfter(true);
        fromX = toX;
        iv_scroll.startAnimation(ta);
        //或是用这种方式
//        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
//        layoutParams.leftMargin = (int) toX;
//        System.out.println(toX);
//        iv_scroll.setLayoutParams(layoutParams);
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    //RadioGroup子类点击事件
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

        for (int i = 0; i < group.getChildCount(); i++) {
            View v = group.getChildAt(i);
            if (v.getId() == checkedId) {
                viewPager.setCurrentItem(i);
            }
        }
    }


    class MyAdapter extends PagerAdapter {


        @Override
        public int getCount() {
            return str.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            TextView tv = new TextView(mContext);
            tv.setText(str[position]);
            tv.setTextColor(Color.RED);
            container.addView(tv);
            return tv;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
//            super.destroyItem(container, position, object);
            container.removeView((View) object);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return str[position];
        }
    }


}
