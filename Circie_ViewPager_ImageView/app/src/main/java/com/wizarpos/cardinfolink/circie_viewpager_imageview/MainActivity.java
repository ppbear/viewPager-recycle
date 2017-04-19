package com.wizarpos.cardinfolink.circie_viewpager_imageview;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<ImageView> ims = new ArrayList<>();
    private int[] adImages = {R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher};
    private BannerChangedListener bannerChangedListener;
    private LinearLayout linearLayout;
    private ViewPager viewPager;
    private BannerAdapter adapter;
    private int pageIndex = 0;
    private boolean isFinish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        initAction();
        startTimer();
    }

    public void startTimer() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (!isFinish) {
                    SystemClock.sleep(4000);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                        }
                    });
                }
            }
        }).start();
    }

    public void initView() {
        linearLayout = (LinearLayout) findViewById(R.id.linearlayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
    }

    public void initAction() {
        bannerChangedListener = new BannerChangedListener();
        viewPager.setOnPageChangeListener(bannerChangedListener);
        int index = (Integer.MAX_VALUE / 2) - (Integer.MAX_VALUE / 2 % adImages.length);
        viewPager.setCurrentItem(index);
        Log.i("test",pageIndex+"---");
        linearLayout.getChildAt(pageIndex).setEnabled(true);
    }

    public void initData() {
        ImageView imageView = new ImageView(this);
        LinearLayout.LayoutParams params;
        View view;
        for (int i = 0; i < adImages.length; i++) {
            imageView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            imageView.setBackgroundResource(adImages[i]);
            ims.add(imageView);

            view = new View(this);
            params = new LinearLayout.LayoutParams(10, 10);
            params.leftMargin = 10;
            view.setBackgroundResource(R.drawable.dot);
            view.setLayoutParams(params);
            view.setEnabled(false);
            linearLayout.addView(view);
        }
        adapter = new BannerAdapter(ims);
        viewPager.setAdapter(adapter);
    }

    class BannerChangedListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            int newPosition = position % adImages.length;
            linearLayout.getChildAt(newPosition).setEnabled(true);
            linearLayout.getChildAt(pageIndex).setEnabled(false);
            pageIndex = newPosition;
        }

        @Override
        public void onPageScrollStateChanged(int state) {
            
        }
    }

    @Override
    protected void onDestroy() {
        isFinish = true;
        super.onDestroy();
    }
}
