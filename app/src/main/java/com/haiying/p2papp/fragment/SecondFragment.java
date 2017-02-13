package com.haiying.p2papp.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.haiying.p2papp.MyApplication;
import com.haiying.p2papp.activity.R;
import com.haiying.p2papp.adapter.MessageGroupFragmentAdapter;
import com.zcx.helper.bound.BoundViewHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SecondFragment extends Fragment implements View.OnClickListener, ViewPager.OnPageChangeListener {
    private ViewPager vPager;
    private List<Fragment> list = new ArrayList<Fragment>();
    private MessageGroupFragmentAdapter adapter;

    private ImageView ivShapeCircle;
    private TextView tvFollow, tvRecommend;

    private int offset = 0;//偏移量216  我这边只是举例说明,不同手机值不一样
    private int currentIndex = 1;


    private LinearLayout ll_tab_left;
    private LinearLayout ll_tab_right;

    private TextView tv_invest_list;
    private TextView tv_regular_list;

    public SecondFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = BoundViewHelper.boundView(this, MyApplication.scaleScreenHelper.loadView((ViewGroup) inflater.inflate(R.layout.fragment_tow, null)));

        /**
         * 初始化三个Fragment  并且填充到ViewPager
         */
        vPager = (ViewPager) view.findViewById(R.id.viewpager_home);


        DynamicFragment dynamicFragment = new DynamicFragment();
        MessageFragment messageFragment = new MessageFragment();
//        DynamicFragment2 personFragment = new DynamicFragment2();
        list.add(dynamicFragment);
        list.add(messageFragment);

        adapter = new MessageGroupFragmentAdapter(getChildFragmentManager(), list);
        vPager.setAdapter(adapter);

        vPager.setOffscreenPageLimit(0);
        vPager.setCurrentItem(0);
        vPager.addOnPageChangeListener(this);

        ivShapeCircle = (ImageView) view.findViewById(R.id.iv_shape_circle);

        tvFollow = (TextView) view.findViewById(R.id.tv_follow);
        tvRecommend = (TextView) view.findViewById(R.id.tv_recommend);
        tvFollow.setSelected(true);//推荐默认选中
        ll_tab_left = (LinearLayout) view.findViewById(R.id.ll_tab_left);
        ll_tab_right = (LinearLayout) view.findViewById(R.id.ll_tab_right);
        ll_tab_left.setOnClickListener(this);
        ll_tab_right.setOnClickListener(this);
        tv_invest_list = (TextView) view.findViewById(R.id.tv_invest_list);
        tv_invest_list.setTextColor(getResources().getColor(R.color.colorWhite));
        tv_regular_list = (TextView) view.findViewById(R.id.tv_regular_list);
        /**
         * 标题栏三个按钮设置点击效果
         */
        tvFollow.setOnClickListener(clickListener);
        tvRecommend.setOnClickListener(clickListener);

        ll_tab_left.setSelected(true);
        tv_invest_list.setSelected(true);

        return view;
    }


    private OnClickListener clickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.tv_follow:
                    //当我们设置setCurrentItem的时候就会触发viewpager的OnPageChangeListener借口,
                    //所以我们不需要去改变标题栏字体啥的
                    vPager.setCurrentItem(0);
                    ll_tab_left.setSelected(true);
                    ll_tab_right.setSelected(false);
                    tv_regular_list.setTextColor(getResources().getColor(R.color.touzilicai_color));
                    tv_invest_list.setTextColor(getResources().getColor(R.color.colorWhite));
                    break;
                case R.id.tv_recommend:
                    vPager.setCurrentItem(1);
                    ll_tab_left.setSelected(false);
                    ll_tab_right.setSelected(true);

                    tv_invest_list.setTextColor(getResources().getColor(R.color.touzilicai_color));
                    tv_regular_list.setTextColor(getResources().getColor(R.color.colorWhite));
                    break;

            }
        }
    };


    public void setCurrentItem(int index) {
       // vPager.setCurrentItem(index);
        if (index == 0) {
            vPager.setCurrentItem(0);
            ll_tab_left.setSelected(true);
            ll_tab_right.setSelected(false);
            tv_regular_list.setTextColor(getResources().getColor(R.color.touzilicai_color));
            tv_invest_list.setTextColor(getResources().getColor(R.color.colorWhite));
        } else {
            vPager.setCurrentItem(1);
            ll_tab_left.setSelected(false);
            ll_tab_right.setSelected(true);
            vPager.setCurrentItem(1);
            tv_invest_list.setTextColor(getResources().getColor(R.color.touzilicai_color));
            tv_regular_list.setTextColor(getResources().getColor(R.color.colorWhite));
        }


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_tab_left:
                vPager.setCurrentItem(0);
                ll_tab_left.setSelected(true);
                ll_tab_right.setSelected(false);
                tv_regular_list.setTextColor(getResources().getColor(R.color.touzilicai_color));
                tv_invest_list.setTextColor(getResources().getColor(R.color.colorWhite));
                break;

            case R.id.ll_tab_right:
                ll_tab_left.setSelected(false);
                ll_tab_right.setSelected(true);
                vPager.setCurrentItem(1);
                tv_invest_list.setTextColor(getResources().getColor(R.color.touzilicai_color));
                tv_regular_list.setTextColor(getResources().getColor(R.color.colorWhite));
                break;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        switch (position) {
            case 0:
                vPager.setCurrentItem(0);
                ll_tab_left.setSelected(true);
                ll_tab_right.setSelected(false);
                tv_regular_list.setTextColor(getResources().getColor(R.color.touzilicai_color));
                tv_invest_list.setTextColor(getResources().getColor(R.color.colorWhite));
                break;
            case 1:
                ll_tab_left.setSelected(false);
                ll_tab_right.setSelected(true);
                vPager.setCurrentItem(1);
                tv_invest_list.setTextColor(getResources().getColor(R.color.touzilicai_color));
                tv_regular_list.setTextColor(getResources().getColor(R.color.colorWhite));
                break;
        }

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
