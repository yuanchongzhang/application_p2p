package com.haiying.p2papp.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.haiying.p2papp.activity.R;

/**
 * 动态
 * 
 * @author Ansen
 * @create time 2015-09-08
 */
public class DynamicFragment2 extends Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_dynamic2, null);
		return rootView;
	}
}
