package com.cca.zhihui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.viewpagerindicator.TabPageIndicator;
/**
 * 
 *@包名:com.cca.zhihui.view
 *@类名:TouchTabPagerIndicator
 *@时间:上午9:27:45
 * @author Administrator
 *@描述:TODO  重写dispatchTouchEvent方法，要求父容器不拦截分发事件
 */
public class TouchTabPagerIndicator extends TabPageIndicator
{

	public TouchTabPagerIndicator(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public TouchTabPagerIndicator(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean dispatchTouchEvent(MotionEvent ev)
	{
		
		/**
		 * true: 不希望父容器去拦截touch事件
		 * false:希望父容器去拦截touch事件
		 */
		getParent().requestDisallowInterceptTouchEvent(true);
		
		return super.dispatchTouchEvent(ev);
	}

}
