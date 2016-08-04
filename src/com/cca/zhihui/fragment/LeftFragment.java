package com.cca.zhihui.fragment;

import java.util.List;

import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.cca.zhihui.MainUIActivity;
import com.cca.zhihui.baseactivity.BaseFragment;
import com.cca.zhihui.bean.NewsCenterMenuBean.NewCenterMenuListBean;
import com.cca.zhuihui.R;
/**
 * 
 *@包名:com.cca.zhihui.fragment
 *@类名:LeftFragment
 *@时间:下午12:05:42
 * @author Administrator
 *@描述:左侧菜单
 */
public class LeftFragment extends BaseFragment implements OnItemClickListener {

	public static final String	TAG	= "LeftFragment";
	private List<NewCenterMenuListBean> mMenuData;  //菜单对应的数据
	private ListView	listview;
	private int mCurrent;
	private NewsMenuAdapter	menuAdapter;
	@Override
	protected View initView() {
	/*	TextView tv=new TextView(mActivity);
		tv.setText("菜单内容");
		return tv;*/
		
		listview = new ListView(mActivity);
		listview.setBackgroundColor(Color.BLACK);//设置背景色为黑色
		listview.setDividerHeight(0); //去掉分割线
		listview.setPadding(0, 50, 0,0);
		listview.setCacheColorHint(Color.TRANSPARENT);
		listview.setSelector(android.R.color.transparent);
		return listview;
		
	}

	/**
	 * 设置菜单数据
	 * @param mMenuData
	 */
	public void setMenuData(List<NewCenterMenuListBean> datas)
	{
		//设置默认选中的第一个item
		this.mCurrent=0;
		
		//数据的接收
		this.mMenuData=datas;
		
		menuAdapter = new NewsMenuAdapter();
		//数据展示---adapter---list
		listview.setAdapter(menuAdapter);
	
		//设置listview条目的点击事件
		listview.setOnItemClickListener(this);
	}
	//listview适配器
	class NewsMenuAdapter extends BaseAdapter{
		@Override
		public int getCount()
		{
			if(mMenuData!=null){
				return mMenuData.size();
			}
			return 0;
		}

		@Override
		public Object getItem(int position)
		{
			return null;
		}

		@Override
		public long getItemId(int position)
		{
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent)
		{
			if(convertView==null){
				//没有复用时
				convertView=View.inflate(mActivity, R.layout.newsmenu, null);
			}
			
			TextView tv=(TextView) convertView.findViewById(R.id.news_menu);
			String title=mMenuData.get(position).title;
			tv.setText(title);
			
			//判断么默认中下的
			/*if(mCurrent==position){
				tv.setEnabled(true);
			}
			else{
				tv.setEnabled(false);
			}*/
			
			tv.setEnabled(mCurrent==position?true:false);
			
			return tv;
		}
		
	}
	/**
	 * listview的条目的点击事件
	 */
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id)
	{
		//不做处理
		if(mCurrent==position){
			return;
		}
		//1、选中对应的项
		this.mCurrent=position;
		//UI更新
		menuAdapter.notifyDataSetChanged();
		
		//2、收起菜单
		MainUIActivity mainui=(MainUIActivity) mActivity;
		mainui.getSlidingMenu().toggle();
		
		//3、右侧内容区域的改变
		ContentFragment contentFra=mainui.getContentFragment();
		contentFra.switchMenuPager(mCurrent);
	}
	
}



