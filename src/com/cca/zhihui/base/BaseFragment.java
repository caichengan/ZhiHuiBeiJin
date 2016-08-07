package com.cca.zhihui.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
/**
 * 
 *@包名:com.cca.zhihui.baseactivity
 *@类名:BaseFragment
 *@时间:下午1:40:21
 * @author Administrator
 *@描述:父类容器BaseFragment，集成了相似的内容
 */
public abstract class BaseFragment extends Fragment {
	
	public Activity mActivity;
	@Override
	public void onCreate( Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.mActivity=getActivity();
		
	}
	@Override
	public View onCreateView(LayoutInflater inflater,
			 ViewGroup container,  Bundle savedInstanceState) {
		return initView();
	}
	
	@Override
	public void onActivityCreated( Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		//加载数据
		initData();
		
	}

	protected void initData() {
		
	}
	//留给子类去具体实现
	protected abstract View initView();

}
