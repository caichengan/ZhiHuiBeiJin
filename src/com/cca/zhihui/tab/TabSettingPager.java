package com.cca.zhihui.tab;

import com.cca.zhihui.base.TabBasePager;

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
 *@����:����tab��Ӧ�����ý���
 */
public class TabSettingPager extends TabBasePager
{

	public TabSettingPager(Context context) {
		super(context);
	}
	
	@Override
	public void initData()
	{
		//1�����ñ�������
		mTextView.setText("���ý���");
		mIvMenu.setVisibility(View.GONE);
		
		//2������������������
		TextView tv=new TextView(mContext);
		tv.setText("���ý�������");
		tv.setTextColor(Color.RED);
		tv.setGravity(Gravity.CENTER);
		
		LayoutParams params=new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
		mContentContainer.addView(tv,params);
		
	}

}
