package com.cca.zhihui.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
/**
 * 
 *@包名:com.cca.zhihui.view
 *@类名:NoTouchViewPager
 *@时间:上午9:31:14
 * @author Administrator
 *@描述:  除了首页和尾页都能响应菜单侧滑事件
 */
public class NoTouchViewPager extends ViewPager
{

	public NoTouchViewPager(Context context) {
		super(context);
	}

	public NoTouchViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

@Override
public boolean onInterceptTouchEvent(MotionEvent arg0)
{
	// TODO Auto-generated method stub
	return false;
}

	@Override
	public boolean onTouchEvent(MotionEvent arg0)
	{
		return false;
	}
	

	
}
