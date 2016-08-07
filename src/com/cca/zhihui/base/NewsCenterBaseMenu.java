package com.cca.zhihui.base;

import android.content.Context;
import android.view.View;

/**
 * 
 * @����:com.cca.zhihui.base
 * @����:NewsCenterBaseMenu
 * @ʱ��:����8:28:24
 * @author Administrator
 * @����:�����������ĵĻ���
 */
public abstract class NewsCenterBaseMenu
{
	protected Context	mContext;	// ������
	protected View		mRootView;	// ����ͼ

	public NewsCenterBaseMenu(Context context) {
		this.mContext = context;
		mRootView = initView();
	}

	/**
	 * ������ȥʵ���Լ���ʲô����
	 * 
	 * @return
	 */
	protected abstract View initView();

	// ���ݼ��صķ��������������д
	public void initData()
	{
		
	}

	//��ø���ͼ
	public View getRootView()
	{
		return mRootView;
	}

}
