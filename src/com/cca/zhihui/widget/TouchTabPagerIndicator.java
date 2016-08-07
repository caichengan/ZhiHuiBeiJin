package com.cca.zhihui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.viewpagerindicator.TabPageIndicator;
/**
 * 
 *@����:com.cca.zhihui.view
 *@����:TouchTabPagerIndicator
 *@ʱ��:����9:27:45
 * @author Administrator
 *@����:TODO  ��дdispatchTouchEvent������Ҫ�����������طַ��¼�
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
		 * true: ��ϣ��������ȥ����touch�¼�
		 * false:ϣ��������ȥ����touch�¼�
		 */
		getParent().requestDisallowInterceptTouchEvent(true);
		
		return super.dispatchTouchEvent(ev);
	}

}
