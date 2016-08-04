package com.cca.zhihui;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.cca.zhihui.utils.GetDataShared;
import com.cca.zhuihui.R;
import com.jeremyfeinstein.slidingmenu.lib.CustomViewAbove.OnPageChangeListener;
/**
 * 引导界面
 * @author Administrator
 *
 */
public class GuirdActivity extends Activity implements  android.support.v4.view.ViewPager.OnPageChangeListener {
	private ViewPager mViewPager; //ViewPager
	private Button mButton;           //开始体验按钮
	private List<ImageView> mList; //页面集合
	private LinearLayout mLinearLayout;//装点的容器
	private View mPointSelect; //动态选择的点
	private int mSpace;
	private static int items[]={R.drawable.guide_1,R.drawable.guide_2,R.drawable.guide_3};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//取消标题
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_guird);
		mViewPager=(ViewPager) findViewById(R.id.guird_pager);
		mButton=(Button) findViewById(R.id.btn_start_button);
		mLinearLayout=(LinearLayout) findViewById(R.id.ll_guird_point);
		mPointSelect=(View) findViewById(R.id.re_point_selected);
		
		initData();
		

		//计算点于点之间的布局
		mPointSelect.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
			@Override
			public void onGlobalLayout() {
				//当整个UI的数布局改变的时候调用
				if(mList==null){
					return ;
				}
				//监听一次
				mPointSelect.getViewTreeObserver().removeGlobalOnLayoutListener(this);
				mSpace = mLinearLayout.getChildAt(1).getLeft()-mLinearLayout.getChildAt(0).getLeft();
			}
		});
		
	}
	/**
	 * 初始化数据
	 */
	private void initData() {
		mList = new ArrayList<ImageView>();
		
		for(int i=0;i<items.length;i++){
			//新建ImageView
			ImageView iv=new ImageView(this);
			iv.setImageResource(items[i]);
			//填充屏幕
			iv.setScaleType(ScaleType.FIT_XY);
			mList.add(iv);
			
			//动态添加一个静态点
			View point=new View(this);
			point.setBackgroundResource(R.drawable.guird_point_normal);
			LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(30, 30);
			//居中
			if(i!=0){
				params.leftMargin=15;
			}
			mLinearLayout.addView(point, params);
			
		}
		//给viewpager设置适配器adapter-----list
		mViewPager.setAdapter(new GuirdViewPagerAdapter());
		
		//设置ViewPager页面的选择监听器
		mViewPager.setOnPageChangeListener(this);
	}
	/**
	 * viewpageer的适配器
	 * @author Administrator
	 *
	 */
	class GuirdViewPagerAdapter extends PagerAdapter{

		@Override
		public int getCount() {
			if(mList!=null){return mList.size();}
			return 0;
		}

		@Override
		public boolean isViewFromObject(View view, Object obj) {
			return view==obj;

		}
		/**
		 * 销毁一个页卡
		 */
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			//从viewpager中移除imageview
			//ImageView iv=mList.get(position);
			container.removeView((View)object);
		}
		/**
		 * 实例化一个页卡
		 */
		@Override
		public Object instantiateItem(ViewGroup container, int position) {
		
			ImageView iv=mList.get(position);
			//添加到viewpager中
			container.addView(iv);
			return iv;
		}
	}
	
	//在当滑动状态改变时的回调
	@Override
	public void onPageScrollStateChanged(int position) {
		
		
		}
	
	//正在滑动的时候回调
	//position当前所处的页面
	//positionset 百分比
	//arg2 滑动时的具体距离
	@Override
	public void onPageScrolled(int position, float positionoffset, int arg2) {
		//1、对滑动的做操作

		RelativeLayout.LayoutParams params= (android.widget.RelativeLayout.LayoutParams) mPointSelect.getLayoutParams();
		//2、设置marginLeft
		params.leftMargin=(int) (mSpace*position+mSpace*positionoffset+0.5f);
		
		mPointSelect.setLayoutParams(params);
	}
	//当viewpager某个页面选中时回调
	//@position当前选中的页面
	@Override
	public void onPageSelected(int position) {
		//设置只有滑动到最后一个页面时才显示按钮
		mButton.setVisibility((position==mList.size()-1?View.VISIBLE:View.GONE));
	}
	/**
	 * 点击按钮实现开始体验
	 * @param view
	 */
	public void startShared(View view){
		if(view.getId()==R.id.btn_start_button){
			goMainUIActivity();
			
		}
	}
	//跳转到主页
	private void goMainUIActivity() {
		GetDataShared.setBoolean(this, WelcomeActivity.KEY_IS_FIRST, false);
		Intent intent=new Intent(GuirdActivity.this,MainUIActivity.class);
		startActivity(intent);
		finish();
	}
}
