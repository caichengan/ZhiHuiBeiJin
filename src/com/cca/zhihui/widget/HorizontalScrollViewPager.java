package com.cca.zhihui.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class HorizontalScrollViewPager extends ViewPager
{

	private float	downX;
	private float	downY;
	public HorizontalScrollViewPager(Context context, AttributeSet attrs) {
		
		super(context, attrs);
	}

	public HorizontalScrollViewPager(Context context) {
		
		super(context);
	}
	@Override
	public boolean dispatchTouchEvent(MotionEvent ev)
	{
		//1、从右往左
		//如果是第一个页面，手指从右往左移动，自已响应自己响应touch
		//如果是第二个页面，手指从右往左滑动，进入下一个页面，自己响应touch
		
		//如果是最后一个页面，手指从右往左滑动，进入下一个页面父容器touch
		
		//2、从左往右
		//如果是在第一个页面，手指从左往右， 父容器响应
		
		//如果是第二个页面，手指从左往右，自己响应touch
		
		//如果是最后一个页面，手指从右往左， 自己响应touch
		switch(ev.getAction()){
			case MotionEvent.ACTION_DOWN:
				downX = ev.getX();
				downY = ev.getY();
				
				break;
			case MotionEvent.ACTION_UP:
							
				break;
			case MotionEvent.ACTION_MOVE:
				getParent().requestDisallowInterceptTouchEvent(true);
				float moveX=ev.getX();
				float moveY=ev.getY();
				
				float diffx=moveX-downX;
				float diffy=moveY-downY;
				
				//Touch的响应逻辑
				if(Math.abs(diffx)>Math.abs(diffy)){
					//用户水平操作有效
					//如果是在第一个页面，手指从左往右， 父容器响应
					if(diffx>0 && getCurrentItem()==0){
						getParent().requestDisallowInterceptTouchEvent(false);
					}else if(diffx>0 && getCurrentItem()!=0){
						//如果是在除去第一个页面外，手指从左往右滑动，自己响应touch
						getParent().requestDisallowInterceptTouchEvent(true);
					}else if(diffx<0 && (getAdapter().getCount()-1)==getCurrentItem()){
						//最后一个页面，手指从右往左滑动，父容器响应
						getParent().requestDisallowInterceptTouchEvent(false);
					}else{
						//从右往左
						//如果在第一个页面，手指进入第二个页面，自己响应touch
						getParent().requestDisallowInterceptTouchEvent(true);
					}
				}else{
					//touch交给父容器
					getParent().requestDisallowInterceptTouchEvent(false);
				}
				break;
		}
		return super.dispatchTouchEvent(ev);
	}
}
