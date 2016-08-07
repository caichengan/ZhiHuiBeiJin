package com.cca.zhihui.base;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.cca.zhihui.MainUIActivity;
import com.cca.zhuihui.R;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

/**
 * 
 * @包名:com.cca.zhihui.baseactivity
 * @类名:TabBasePager
 * @时间:下午4:14:51
 * @author Administrator
 * @描述: 每个页面共同的内容，抽取出来成为基类
 */
public abstract class TabBasePager implements OnClickListener
{
	protected View	mRootView; //根视图
	protected Context mContext; //上下文
	protected TextView mTextView;//title
	protected ImageView mIvMenu;//menu
	protected FrameLayout mContentContainer;//内容容器

	public TabBasePager(Context context) {
		this.mContext=context;
		this.mRootView = initView();
	}
	protected  View initView(){
		View view =View.inflate(mContext, R.layout.content_tab, null);
		//实现内容的查找
		mTextView=(TextView) view.findViewById(R.id.content_text_title);
		mIvMenu=	(ImageView) view.findViewById(R.id.content_img_menu);
		mContentContainer=(FrameLayout) view.findViewById(R.id.content_tab_container);
		mIvMenu.setOnClickListener(this);
		return view;
	}
	//数据加载的方法，子类可以重写
	public  void initData(){
	}
	public View getRootView()
	{
		return mRootView;
	}
	
	//菜单栏的操作
	@Override
	public void onClick(View v)
	{

		if(v==mIvMenu){
			toggleSetMenu();
		}
	}
	/**
	 * 点击菜单栏图标关闭菜单，若关闭着点击就打开
	 */
	private void toggleSetMenu()
	{
		//获取上下文取得菜单实例：点击菜单，若菜单打开就关闭，关闭就打开
		MainUIActivity ui=(MainUIActivity) mContext;
		SlidingMenu menu=ui.getSlidingMenu();
		menu.toggle();
		
	}
	/**
	 * 菜单切换的方法，子类要实现菜单切换，就要复写这个方法
	 * @param position
	 */
	public void switchMenuPager(int position)
	{
		
	}
}
