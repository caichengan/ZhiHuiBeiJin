package com.cca.zhihui.baseactivity;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

/**
 * 
 *@����:com.cca.zhihui.baseactivity
 *@����:TabHomePager
 *@ʱ��:����5:08:06
 * @author Administrator
 *@����:����tab��Ӧ���������Ľ���
 */
public class TabNewCenterPager extends TabBasePager
{
	private List<TextView> mList;
	public TabNewCenterPager(Context context) {
		super(context);
	}
	
	/**
	 * ��������
	 */
	@Override
	public void initData()
	{
		//1�����ñ�������
		mTextView.setText("��������");
		mIvMenu.setVisibility(View.VISIBLE);
		
	/*	//2������������������
		TextView tv=new TextView(mContext);
		tv.setText("����������������");
		tv.setTextColor(Color.RED);
		tv.setGravity(Gravity.CENTER);
		
		LayoutParams params=new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
		mContentContainer.addView(tv,params);*/
		
		mList=new ArrayList<TextView>();
		for(int i=0;i<4;i++){
			TextView tv1=new TextView(mContext);
			tv1.setText("��"+(i+1)+"��ҳ��");
			mList.add(tv1);
			
			
		}
		//��������������ͼ��չʾ��Ĭ��ֵ
		switchPager(0);
		
		
	}

	public void switchPager(int i)
	{
		//������ݵ�����
		mContentContainer.removeAllViews();
		//α����չʾ
		TextView tv1=new TextView(mContext);
		tv1.setTextColor(Color.RED);
		tv1.setTextColor(Color.RED);
		tv1.setGravity(Gravity.CENTER);
		LayoutParams params=new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
		mContentContainer.addView(tv1,params);
		
	}
}
