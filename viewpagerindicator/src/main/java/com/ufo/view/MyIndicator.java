package com.ufo.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者： XuDiWei
 * <p/>
 * 日期：2015/7/27  11:41.
 * <p/>
 * 文件描述:
 */
public class MyIndicator extends HorizontalScrollView implements ViewPager.OnPageChangeListener {

    private Context mContext;

    /**
     * 用于存放标题的集合
     */
    private List<String> titleList;

    /**
     * 标题
     */
    private RadioGroup rg;

    /**
     * 指示器
     */
    private ImageView vIndicator;

    /**
     * 指示器滚动开始的X坐标
     */
    private float fromX;

    private ViewPager mViewPager;
    private View bottomLine;

    public MyIndicator(Context context) {
        super(context);
        this.mContext = context;

    }

    public MyIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
    }

    public MyIndicator(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
    }

    /**
     * 初始化view
     *
     */
    private void initView() {

        LinearLayout ll = new LinearLayout(mContext);
        ll.setOrientation(LinearLayout.VERTICAL);
        HorizontalScrollView.LayoutParams llParams = new LayoutParams(HorizontalScrollView.LayoutParams.MATCH_PARENT, HorizontalScrollView.LayoutParams.WRAP_CONTENT);
        ll.setLayoutParams(llParams);

        //向LinearLayout里添加RadioGroup
        initChannel();
        ll.addView(rg);

        //向Linearlayout 里添加指示器(ImageView)
        initIndicator();
        ll.addView(vIndicator);

        //添加底部线
        initBottomLine();
        ll.addView(bottomLine);

        //最后再把Linearlayout添加到当前控件（HorizontalScrollView）
        this.addView(ll);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        System.out.println("onmeasure");
        //RadioGroup
        View view = ((ViewGroup) getChildAt(0)).getChildAt(0);
        //RadioButton的宽
        int childWidth = ((RadioGroup) view).getChildAt(0).getWidth();


        //ImageView
        View indicator = ((ViewGroup) getChildAt(0)).getChildAt(1);
        indicator.getLayoutParams().width = childWidth;

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //

    }

    /**
     * 初始化RadoiGroup
     */
    private void initChannel() {
        rg = new RadioGroup(mContext);
        rg.setOrientation(RadioGroup.HORIZONTAL);
        rg.setFadingEdgeLength(0);
        LinearLayout.LayoutParams rgParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        rg.setLayoutParams(rgParams);

        //向RadioGroup里添加RadioButton
        RadioButton rb = null;
        for (int i = 0; i < titleList.size(); i++) {
            rb = new RadioButton(mContext);
            RadioGroup.LayoutParams rbParams = new RadioGroup.LayoutParams(RadioGroup.LayoutParams.WRAP_CONTENT, RadioGroup.LayoutParams.WRAP_CONTENT);
            rb.setLayoutParams(rbParams);
            rb.setButtonDrawable(new BitmapDrawable());
            rb.setGravity(Gravity.CENTER);
            rb.setText(titleList.get(i));
            rb.setTextSize(20);
            rb.setTextColor(Color.GRAY);
            rb.setPadding(20, 10, 20, 10);
            rg.addView(rb);
        }
    }

    /**
     * 初始化指示器
     */
    private void initIndicator() {
        vIndicator = new ImageView(mContext);
        LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(10, 10);
        vIndicator.setLayoutParams(params1);
        vIndicator.setBackgroundColor(Color.RED);
    }

    /**
     * 初始化底部线
     */
    private void initBottomLine() {
        bottomLine = new View(mContext);
        LinearLayout.LayoutParams ll = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 4);
        bottomLine.setBackgroundColor(Color.RED);
        bottomLine.setLayoutParams(ll);
    }

    /**
     * 把要关联的viewpager设置进来
     *
     * @param viewPager
     */
    public void setViewPager(ViewPager viewPager) {
        this.mViewPager = viewPager;
        if (null != mViewPager) {
            mViewPager.setOnPageChangeListener(this);
            PagerAdapter adapter = mViewPager.getAdapter();
            if (null == adapter) {
                throw new RuntimeException("当前viewPager没有设置适配器，或是你在设置适配置之前调用了当前方法:setViewPager");
            }

            int count = adapter.getCount();
            titleList = new ArrayList<String>();
            for (int i = 0; i < count; i++) {
                titleList.add(adapter.getPageTitle(i).toString());
            }
            initView();
        }
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        //myIndicator移动
        RadioButton rb = (RadioButton) rg.getChildAt(position);
        int indicatorScrollWidth = (int) ((positionOffset + position) * rb.getWidth());
        int halfWidth = (mViewPager.getWidth() - rb.getWidth()) / 2;
        int scroll = indicatorScrollWidth - halfWidth;
        this.scrollTo(scroll, 0);

//        int[] location = new int[2];
//        rb.getLocationInWindow(location);
        //指示器移动
        float toX = (positionOffset + position) * rb.getWidth();
//        System.out.println(position);
        TranslateAnimation ta = new TranslateAnimation(fromX, toX, 0, 0);
        ta.setDuration(50);
        ta.setFillAfter(true);
        vIndicator.startAnimation(ta);
        fromX = toX;

    }

    @Override
    public void onPageSelected(int position) {
        RadioButton rb = null;

        //设置当前页面选中时的颜色
        for (int i = 0; i < rg.getChildCount(); i++) {
            rb = (RadioButton) rg.getChildAt(i);
            if (i == position) {
                rb.setTextColor(Color.RED);
            } else {
                rb.setTextColor(Color.GRAY);
            }
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
