package com.ufo.viewpagerindicator;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ufo.view.MyIndicator;


public class SecondActivity extends ActionBarActivity {

    private MyIndicator myIndicator;
    private ViewPager viewPager;
    private static String[] str = {"首页", "新闻", "资讯", "娱乐", "图文", "教育", "汽车", "天气", "军事", "热门", "搞笑", "其它"};

    private Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        this.mContext = this;
        initView();
    }

    private void initView() {
        myIndicator = (MyIndicator) findViewById(R.id.myIndicator);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        MyAdapter myAdapter = new MyAdapter();
        viewPager.setAdapter(myAdapter);
        myIndicator.setViewPager(viewPager);
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
