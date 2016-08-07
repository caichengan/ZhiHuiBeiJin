package com.cca.zhihui.base;

import android.content.Context;
import android.view.View;

/**
 * 
 * @包名:com.cca.zhihui.base
 * @类名:NewsCenterBaseMenu
 * @时间:下午8:28:24
 * @author Administrator
 * @描述:描述新闻中心的基类
 */
public abstract class NewsCenterBaseMenu
{
	protected Context	mContext;	// 上下文
	protected View		mRootView;	// 根视图

	public NewsCenterBaseMenu(Context context) {
		this.mContext = context;
		mRootView = initView();
	}

	/**
	 * 让子类去实现自己长什么样子
	 * 
	 * @return
	 */
	protected abstract View initView();

	// 数据加载的方法，子类可以重写
	public void initData()
	{
		
	}

	//获得根视图
	public View getRootView()
	{
		return mRootView;
	}

}
