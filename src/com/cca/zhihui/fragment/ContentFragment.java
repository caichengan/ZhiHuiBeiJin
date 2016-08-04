package com.cca.zhihui.fragment;

import java.util.ArrayList;
import java.util.List;

import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.cca.zhihui.MainUIActivity;
import com.cca.zhihui.baseactivity.BaseFragment;
import com.cca.zhihui.baseactivity.TabBasePager;
import com.cca.zhihui.baseactivity.TabGovPager;
import com.cca.zhihui.baseactivity.TabHomePager;
import com.cca.zhihui.baseactivity.TabNewCenterPager;
import com.cca.zhihui.baseactivity.TabSettingPager;
import com.cca.zhihui.baseactivity.TabSmasterservicePager;
import com.cca.zhihui.view.NoTouchViewPager;
import com.cca.zhuihui.R;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
/**
 * 
 *@包名:com.cca.zhihui.fragment
 *@类名:ContentFragment
 *@时间:下午5:27:40
 * @author Administrator
 *@描述:这是加载页面内容的容器
 */
public class ContentFragment extends BaseFragment implements OnCheckedChangeListener {
	public static final String	tag	= "ContentFragment";
	
	@ViewInject(R.id.content_pager) 	//viewPager
	private NoTouchViewPager mPager;	
	@ViewInject(R.id.content_tab_rad)     //底部容器
	
	private RadioGroup mRadioGroup;    
	private List<TabBasePager> mList;
	@Override
	protected View initView() {
	View view1=	View.inflate(mActivity, R.layout.content, null);
		//ViewUtils工具的注入
	ViewUtils.inject(this, view1);
		return view1;
	}
	@Override
	protected void initData() {
		//初始化数据
		mList=new ArrayList<TabBasePager>();
		//加载数据TODO 添加主页面
		mList.add(new TabHomePager(mActivity));
		mList.add(new TabNewCenterPager(mActivity));
		mList.add(new TabSmasterservicePager(mActivity));
		mList.add(new TabGovPager(mActivity));
		mList.add(new TabSettingPager(mActivity));
		//加载数据
		mPager.setAdapter(new ViewPagerAdapter());
		
		//设置RadioGroup的点击事件
		mRadioGroup.setOnCheckedChangeListener(this);
		
		//给RadioGroup设置默认值
		mRadioGroup.check(R.id.tab_home);
	}
	//viewpager的的适配器
	 class ViewPagerAdapter extends PagerAdapter{
		 //返回集合大小
		@Override
		public int getCount()
		{
			if(mList!=null){
				return mList.size();
			}
			return 0;
		}
		@Override
		public boolean isViewFromObject(View arg0, Object arg1)
		{
			return arg0==arg1;
		}
		//销毁一个页卡
		@Override
		public void destroyItem(ViewGroup container, int position, Object object)
		{
			Log.i(tag, "销毁的是第"+position+"个页面");
			container.removeView((View) object);
		}
		//实例化一个页卡
		@Override
		public Object instantiateItem(ViewGroup container, int position)
		{
			Log.i(tag, "加载的是第"+position+"个页面");
			TabBasePager pager=mList.get(position);
			View view=pager.getRootView();
			//viewpager需要存放视图
			container.addView(view);
			//给页面控制器加载数据
			pager.initData();
			return view;
		}
	}
	 /**
	  * 将五个页面分别和下面五个tab关联起来
	  */
	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId)
	{
		int checkCurrentId=-1;
		switch(checkedId){
			case R.id.tab_home:
				checkCurrentId=0;
				setSlindingMenu(false);
				break;
			case R.id.tab_news:
				checkCurrentId=1;
				setSlindingMenu(true);
				break;
			case R.id.tab_smaster:
				checkCurrentId=2;
				setSlindingMenu(true);
				break;
			case R.id.tab_gov:
				checkCurrentId=3;
				setSlindingMenu(true);
				break;
			case R.id.tab_setting:
				checkCurrentId=4;
				setSlindingMenu(false);
				break;
			
		}
		//给viewpager设置选中的页面
		mPager.setCurrentItem(checkCurrentId);
	}
	/**
	 * 设置menu是否可见即是否能否侧滑
	 * @param enable
	 */
	public void setSlindingMenu(boolean enable){
		MainUIActivity mainui=(MainUIActivity) mActivity;
		SlidingMenu menu=mainui.getSlidingMenu();
		menu.setTouchModeAbove(enable?SlidingMenu.TOUCHMODE_FULLSCREEN:SlidingMenu.TOUCHMODE_NONE);
		
	}
}
