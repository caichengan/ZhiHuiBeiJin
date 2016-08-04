package com.cca.zhihui;

import com.cca.zhihui.fragment.ContentFragment;

import com.cca.zhihui.fragment.LeftFragment;
import com.cca.zhuihui.R;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;


import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Window;
/**
 * 
 * @author Administrator
 *主界面
 */
public class MainUIActivity extends SlidingFragmentActivity {

	private final static String TAG_CONTENT="content"; //主页
	private final static String TAG_LEFT_MENU="left_menu"; //左侧
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//取消标题
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		//设置内容区域
		setContentView(R.layout.activity_mainui);
		
		//设置behind部分
		setBehindContentView(R.layout.main_left);
		//获得SlidingMenu的实例
		SlidingMenu menu=getSlidingMenu();
		//设置模式
		menu.setMode(SlidingMenu.LEFT);
		//指定的是菜单的边缘到屏幕边缘的距离
		menu.setBehindOffset(180);
		//菜单的区域宽度
		//menu.setBehindWidth(100);
		menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		
		//加载fragment的方式加载页面
		initFragment();
		
	}

	private void initFragment() {
		FragmentManager  fm=getSupportFragmentManager();
		
		//开启事务
		FragmentTransaction transaction=fm.beginTransaction();
		
		//添加主页fragmeng
		transaction.replace(R.id.main_container, new ContentFragment(), TAG_CONTENT);
		
		//添加左侧fragmeng
		transaction.replace(R.id.main_left, new LeftFragment(), TAG_LEFT_MENU);
		
		transaction.commit();
		
	}
}
