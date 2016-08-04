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
 *@包名:com.cca.zhihui.baseactivity
 *@类名:TabHomePager
 *@时间:下午5:08:06
 * @author Administrator
 *@描述:这是tab对应的新闻中心界面
 */
public class TabNewCenterPager extends TabBasePager
{
	private List<TextView> mList;
	public TabNewCenterPager(Context context) {
		super(context);
	}
	
	/**
	 * 加载数据
	 */
	@Override
	public void initData()
	{
		//1、设置标题内容
		mTextView.setText("新闻中心");
		mIvMenu.setVisibility(View.VISIBLE);
		
	/*	//2、设置内容区域数据
		TextView tv=new TextView(mContext);
		tv.setText("新闻中心内容区域");
		tv.setTextColor(Color.RED);
		tv.setGravity(Gravity.CENTER);
		
		LayoutParams params=new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
		mContentContainer.addView(tv,params);*/
		
		mList=new ArrayList<TextView>();
		for(int i=0;i<4;i++){
			TextView tv1=new TextView(mContext);
			tv1.setText("第"+(i+1)+"个页面");
			mList.add(tv1);
			
			
		}
		//设置内容区域视图的展示的默认值
		switchPager(0);
		
		
	}

	public void switchPager(int i)
	{
		//清空内容的数据
		mContentContainer.removeAllViews();
		//伪代码展示
		TextView tv1=new TextView(mContext);
		tv1.setTextColor(Color.RED);
		tv1.setTextColor(Color.RED);
		tv1.setGravity(Gravity.CENTER);
		LayoutParams params=new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
		mContentContainer.addView(tv1,params);
		
	}
}
