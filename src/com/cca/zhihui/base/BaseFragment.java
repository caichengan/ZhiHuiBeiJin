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
 *@����:com.cca.zhihui.baseactivity
 *@����:BaseFragment
 *@ʱ��:����1:40:21
 * @author Administrator
 *@����:��������BaseFragment�����������Ƶ�����
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
		//��������
		initData();
		
	}

	protected void initData() {
		
	}
	//��������ȥ����ʵ��
	protected abstract View initView();

}
