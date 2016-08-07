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
		//1����������
		//����ǵ�һ��ҳ�棬��ָ���������ƶ���������Ӧ�Լ���Ӧtouch
		//����ǵڶ���ҳ�棬��ָ�������󻬶���������һ��ҳ�棬�Լ���Ӧtouch
		
		//��������һ��ҳ�棬��ָ�������󻬶���������һ��ҳ�游����touch
		
		//2����������
		//������ڵ�һ��ҳ�棬��ָ�������ң� ��������Ӧ
		
		//����ǵڶ���ҳ�棬��ָ�������ң��Լ���Ӧtouch
		
		//��������һ��ҳ�棬��ָ�������� �Լ���Ӧtouch
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
				
				//Touch����Ӧ�߼�
				if(Math.abs(diffx)>Math.abs(diffy)){
					//�û�ˮƽ������Ч
					//������ڵ�һ��ҳ�棬��ָ�������ң� ��������Ӧ
					if(diffx>0 && getCurrentItem()==0){
						getParent().requestDisallowInterceptTouchEvent(false);
					}else if(diffx>0 && getCurrentItem()!=0){
						//������ڳ�ȥ��һ��ҳ���⣬��ָ�������һ������Լ���Ӧtouch
						getParent().requestDisallowInterceptTouchEvent(true);
					}else if(diffx<0 && (getAdapter().getCount()-1)==getCurrentItem()){
						//���һ��ҳ�棬��ָ�������󻬶�����������Ӧ
						getParent().requestDisallowInterceptTouchEvent(false);
					}else{
						//��������
						//����ڵ�һ��ҳ�棬��ָ����ڶ���ҳ�棬�Լ���Ӧtouch
						getParent().requestDisallowInterceptTouchEvent(true);
					}
				}else{
					//touch����������
					getParent().requestDisallowInterceptTouchEvent(false);
				}
				break;
		}
		return super.dispatchTouchEvent(ev);
	}
}
