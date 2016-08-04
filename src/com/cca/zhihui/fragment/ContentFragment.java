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
 *@����:com.cca.zhihui.fragment
 *@����:ContentFragment
 *@ʱ��:����5:27:40
 * @author Administrator
 *@����:���Ǽ���ҳ�����ݵ�����
 */
public class ContentFragment extends BaseFragment implements OnCheckedChangeListener {
	public static final String	tag	= "ContentFragment";
	
	@ViewInject(R.id.content_pager) 	//viewPager
	private NoTouchViewPager mPager;	
	@ViewInject(R.id.content_tab_rad)     //�ײ�����
	
	private RadioGroup mRadioGroup;    
	private List<TabBasePager> mList;
	@Override
	protected View initView() {
	View view1=	View.inflate(mActivity, R.layout.content, null);
		//ViewUtils���ߵ�ע��
	ViewUtils.inject(this, view1);
		return view1;
	}
	@Override
	protected void initData() {
		//��ʼ������
		mList=new ArrayList<TabBasePager>();
		//��������TODO �����ҳ��
		mList.add(new TabHomePager(mActivity));
		mList.add(new TabNewCenterPager(mActivity));
		mList.add(new TabSmasterservicePager(mActivity));
		mList.add(new TabGovPager(mActivity));
		mList.add(new TabSettingPager(mActivity));
		//��������
		mPager.setAdapter(new ViewPagerAdapter());
		
		//����RadioGroup�ĵ���¼�
		mRadioGroup.setOnCheckedChangeListener(this);
		
		//��RadioGroup����Ĭ��ֵ
		mRadioGroup.check(R.id.tab_home);
	}
	//viewpager�ĵ�������
	 class ViewPagerAdapter extends PagerAdapter{
		 //���ؼ��ϴ�С
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
		//����һ��ҳ��
		@Override
		public void destroyItem(ViewGroup container, int position, Object object)
		{
			Log.i(tag, "���ٵ��ǵ�"+position+"��ҳ��");
			container.removeView((View) object);
		}
		//ʵ����һ��ҳ��
		@Override
		public Object instantiateItem(ViewGroup container, int position)
		{
			Log.i(tag, "���ص��ǵ�"+position+"��ҳ��");
			TabBasePager pager=mList.get(position);
			View view=pager.getRootView();
			//viewpager��Ҫ�����ͼ
			container.addView(view);
			//��ҳ���������������
			pager.initData();
			return view;
		}
	}
	 /**
	  * �����ҳ��ֱ���������tab��������
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
		//��viewpager����ѡ�е�ҳ��
		mPager.setCurrentItem(checkCurrentId);
	}
	/**
	 * ����menu�Ƿ�ɼ����Ƿ��ܷ�໬
	 * @param enable
	 */
	public void setSlindingMenu(boolean enable){
		MainUIActivity mainui=(MainUIActivity) mActivity;
		SlidingMenu menu=mainui.getSlidingMenu();
		menu.setTouchModeAbove(enable?SlidingMenu.TOUCHMODE_FULLSCREEN:SlidingMenu.TOUCHMODE_NONE);
		
	}
}
