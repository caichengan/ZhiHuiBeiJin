package com.cca.zhihui.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
/**
 * 
 *@����:com.cca.zhihui.view
 *@����:NoTouchViewPager
 *@ʱ��:����9:31:14
 * @author Administrator
 *@����:  ������ҳ��βҳ������Ӧ�˵��໬�¼�
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
