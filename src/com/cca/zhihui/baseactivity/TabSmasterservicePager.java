package com.cca.zhihui.baseactivity;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

/**
 * 
 *@包名:com.cca.zhihui.baseactivity
 *@类名:TabHomePager
 *@时间:下午5:08:06
 * @author Administrator
 *@描述:这是tab对应的智慧服务界面
 */
public class TabSmasterservicePager extends TabBasePager
{
	public TabSmasterservicePager(Context context) {
		super(context);
	}
	@Override
	public void initData()
	{
		//1、设置标题内容
		mTextView.setText("生活");
		mIvMenu.setVisibility(View.VISIBLE);
		
		//2、设置内容区域数据
		TextView tv=new TextView(mContext);
		tv.setText("智慧服务内容区域");
		tv.setTextColor(Color.RED);
		tv.setGravity(Gravity.CENTER);
		
		LayoutParams params=new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
		mContentContainer.addView(tv,params);
	}
}
