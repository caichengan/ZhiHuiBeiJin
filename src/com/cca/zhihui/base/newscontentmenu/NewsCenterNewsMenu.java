package com.cca.zhihui.base.newscontentmenu;

import java.util.List;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.cca.zhihui.MainUIActivity;
import com.cca.zhihui.base.NewsCenerListPager;
import com.cca.zhihui.base.NewsCenterBaseMenu;
import com.cca.zhihui.bean.NewsCenterMenuBean.NewCenterMenuListBean;
import com.cca.zhihui.bean.NewsCenterMenuBean.NewsCenterNewsItemBean;
import com.cca.zhihui.widget.TouchTabPagerIndicator;
import com.cca.zhuihui.R;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

/**
 * 
 *@包名:com.cca.zhihui.base.newscontentmenu
 *@类名:NewsCenterNewsMenu
 *@时间:下午5:26:32
 * @author Administrator
 *@描述:新闻中心页面页面显示
 */
public class NewsCenterNewsMenu extends NewsCenterBaseMenu implements OnPageChangeListener
{
	@ViewInject(R.id.news_tab_indicator)
	private TouchTabPagerIndicator				mIndicator;
	@ViewInject(R.id.news_center_pager)
	private ViewPager						mViewPager;
	@ViewInject(R.id.news_arrow)
	private ImageView						arrow;		// 移动箭头
	
	private NewCenterMenuListBean			mMenuData;	// list数据集合
	private List<NewsCenterNewsItemBean>	mPagerData; // viewpager要显示的数据
	
	public NewsCenterNewsMenu(Context context, NewCenterMenuListBean data) {
		super(context);
		this.mMenuData = data;
		mPagerData = this.mMenuData.children;

	}
	@OnClick(R.id.news_arrow)
	public void onClick(View view)
	{
		// 点击箭头到下一个对象
		int item = mViewPager.getCurrentItem();
		mViewPager.setCurrentItem(++item);
	}
	@Override
	protected View initView()
	{
		//加载布局
		View view = View.inflate(mContext, R.layout.news_center_tab, null);

		// 使用ViewUtils注入
		ViewUtils.inject(this, view);

		return view;

	}
	@Override
	public void initData()
	{
		// 给viewpager设置适配器adapter---list
		mViewPager.setAdapter(new NewsPager());
		//给tabIndicator设置viewpager
		mIndicator.setViewPager(mViewPager);
		
		//给tabindicator设置页面的选择监听器，第一页面可滑出菜单，其他页面不可滑
		mIndicator.setOnPageChangeListener(this);
	}
	/**
	 * 
	 *@包名:com.cca.zhihui.base.newscontentmenu
	 *@类名:NewsPager
	 *@时间:下午5:26:18
	 * @author Administrator
	 * 
	 *@描述: viewpager的适配器
	 */
	class NewsPager extends PagerAdapter
	{
		@Override
		public int getCount()
		{
			if (mPagerData != null) { return mPagerData.size(); }
			return 0;
		}
		@Override
		public boolean isViewFromObject(View arg0, Object arg1)
		{
			return arg0 == arg1;
		}
		@Override
		public void destroyItem(ViewGroup container, int position, Object object)
		{
			container.removeView((View) object);
		}
		/**
		 * 获得标题
		 */
		@Override
		public CharSequence getPageTitle(int position)
		{
			if (mPagerData != null) { return mPagerData.get(position).title; }
			return super.getPageTitle(position);
		}
		
		@Override
		public Object instantiateItem(ViewGroup container, int position)
		{
			//  加载新闻页面的数据
			NewsCenterNewsItemBean bean = mPagerData.get(position);
			// 获取数据
			NewsCenerListPager newsPager=new NewsCenerListPager(mContext,bean);
			//加载视图
			View view=newsPager.getRootView();
			
			//TODO  图片视图加载不上去
			container.addView(view);
			
			//加载数据
			newsPager.initData();
			return view;
			
		}
	}
	@Override
	public void onPageScrollStateChanged(int arg0)
	{
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2)
	{
	}
	/**
	 * 页面选中时调用
	 */
	@Override
	public void onPageSelected(int position)
	{
		MainUIActivity mainui=(MainUIActivity) mContext;
		SlidingMenu  menu=mainui.getSlidingMenu();
	//当页面被选中的时候调用、是否能全屏（拉出菜单）
		menu.setTouchModeAbove(position==0 ? SlidingMenu.TOUCHMODE_FULLSCREEN:SlidingMenu.TOUCHMODE_NONE);
	}
}
/*
08-06 07:34:42.222: E/AndroidRuntime(807): FATAL EXCEPTION: main
08-06 07:34:42.222: E/AndroidRuntime(807): java.lang.StackOverflowError
08-06 07:34:42.222: E/AndroidRuntime(807): 	at android.view.View.invalidate(View.java:8429)
08-06 07:34:42.222: E/AndroidRuntime(807): 	at android.widget.TextView.invalidateDrawable(TextView.java:4573)
08-06 07:34:42.222: E/AndroidRuntime(807): 	at android.graphics.drawable.DrawableContainer.invalidateDrawable(DrawableContainer.java:241)
08-06 07:34:42.222: E/AndroidRuntime(807): 	at android.graphics.drawable.Drawable.invalidateSelf(Drawable.java:350)
08-06 07:34:42.222: E/AndroidRuntime(807): 	at android.graphics.drawable.NinePatchDrawable.setAlpha(NinePatchDrawable.java:206)
08-06 07:34:42.222: E/AndroidRuntime(807): 	at android.graphics.drawable.DrawableContainer.jumpToCurrentState(DrawableContainer.java:170)
08-06 07:34:42.222: E/AndroidRuntime(807): 	at android.view.View.jumpDrawablesToCurrentState(View.java:11557)
08-06 07:34:42.222: E/AndroidRuntime(807): 	at android.widget.TextView.jumpDrawablesToCurrentState(TextView.java:4507)
08-06 07:34:42.222: E/AndroidRuntime(807): 	at android.view.ViewGroup.jumpDrawablesToCurrentState(ViewGroup.java:5009)
08-06 07:34:42.222: E/AndroidRuntime(807): 	at android.view.ViewGroup.jumpDrawablesToCurrentState(ViewGroup.java:5009)
08-06 07:34:42.222: E/AndroidRuntime(807): 	at android.widget.FrameLayout.jumpDrawablesToCurrentState(FrameLayout.java:172)
08-06 07:34:42.222: E/AndroidRuntime(807): 	at android.view.ViewGroup.jumpDrawablesToCurrentState(ViewGroup.java:5009)

*/