package com.cca.zhihui.baseactivity;

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
 *@����:����tab��Ӧ���ǻ۷������
 */
public class TabSmasterservicePager extends TabBasePager
{
	public TabSmasterservicePager(Context context) {
		super(context);
	}
	@Override
	public void initData()
	{
		//1�����ñ�������
		mTextView.setText("����");
		mIvMenu.setVisibility(View.VISIBLE);
		
		//2������������������
		TextView tv=new TextView(mContext);
		tv.setText("�ǻ۷�����������");
		tv.setTextColor(Color.RED);
		tv.setGravity(Gravity.CENTER);
		
		LayoutParams params=new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
		mContentContainer.addView(tv,params);
	}
}
