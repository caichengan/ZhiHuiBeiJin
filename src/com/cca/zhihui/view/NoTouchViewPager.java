package com.cca.zhihui.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class NoTouchViewPager extends ViewPager
{

	public NoTouchViewPager(Context context) {
		super(context);
	}

	public NoTouchViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	protected boolean canScroll(View arg0, boolean arg1, int arg2, int arg3, int arg4)
	{
		return false;
	}

	@Override
	public boolean onTouchEvent(MotionEvent arg0)
	{
		return false;
	}

	
}
