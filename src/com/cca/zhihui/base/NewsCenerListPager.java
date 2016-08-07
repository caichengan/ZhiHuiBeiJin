package com.cca.zhihui.base;

import java.util.List;

import android.content.Context;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.cca.zhihui.bean.NewsCenterMenuBean.NewsCenterNewsItemBean;
import com.cca.zhihui.bean.NewsListBean;
import com.cca.zhihui.bean.NewsListBean.NewsListPagerNewsBean;
import com.cca.zhihui.bean.NewsListBean.NewsListPagerTopnesBean;
import com.cca.zhihui.utils.Constans;
import com.cca.zhihui.utils.GetDataShared;
import com.cca.zhihui.widget.HorizontalScrollViewPager;
import com.cca.zhihui.widget.RefreshListView;
import com.cca.zhihui.widget.RefreshListView.OnRefreshListener;
import com.cca.zhuihui.R;
import com.google.gson.Gson;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * 
 * @包名:com.cca.zhihui.base
 * @类名:NewsCenerListPager
 * @时间:下午3:55:02
 * @author Administrator
 * @描述:新闻页面对应的list页面
 */
public class NewsCenerListPager extends NewsCenterBaseMenu implements OnPageChangeListener, OnRefreshListener 
{

	protected static final String			TAG	= "NewsCenerListPager";

	public static final long	TIME_DELAY	= 2000;

	@ViewInject(R.id.news_list_pager)
	private HorizontalScrollViewPager						mPager;
	
	@ViewInject(R.id.news_list_datas)
	private RefreshListView					mListView;

	@ViewInject(R.id.news_list_pic_pager)
	private TextView						mTextView;

	@ViewInject(R.id.news_list_point_container)
	private LinearLayout					mPointContainer;

	private BitmapUtils						mBitmapUtils;// xUtils类图片加载的工具类
	private NewsCenterNewsItemBean			newData;

	private List<NewsListPagerTopnesBean>	mPicData;  //加載新聞頁面的圖片數據集合

	private NewsListPagerAdapter	adapter;

	private AutoSwitchPicTask	mswitchPicTask;



	private List<NewsListPagerNewsBean>	mListData;  //list的數據集合
	public NewsCenerListPager(Context context, NewsCenterNewsItemBean data) {
		super(context);
		this.newData = data;
		
		mBitmapUtils = new BitmapUtils(mContext);
	}
	//重写父类的方法，拿到根视图
	@Override
	protected View initView()
	{
		View view = View.inflate(mContext, R.layout.news_list_pager, null);
		
		// ViewUtils注入
		ViewUtils.inject(this, view);
		
		//二次注入
		View topNewsView=View.inflate(mContext, R.layout.news_list_top, null);
		ViewUtils.inject(this, topNewsView);
		
		//给listview添加headerview
		mListView.addCustomView(topNewsView);
		
		mListView.setOnRefreshListener(this);
			
		
		return view;

	}

	@Override
	public void initData()
	{
		// 网络加载数据
		loadNetData();
	}
	// 网络加载数据
	public void loadNetData()
	{
		final String url = Constans.SERVICE_URL + newData.url;
		String json=GetDataShared.getString(mContext, url);
		if(!TextUtils.isEmpty(json)){
			// 解析数据
			processData(json);
		}
		
		HttpUtils utils = new HttpUtils();
		utils.send(HttpMethod.GET, url, new RequestCallBack<String>() {

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo)
			{
				// 返回成功，获得数据串
				String result = responseInfo.result;
				Log.i(TAG, "---------网络数据加载成功------" + result);
				//存储数据
				GetDataShared.setString(mContext, url, result);
				// 解析数据
				processData(result);
				

			}
			@Override
			public void onFailure(HttpException error, String msg)
			{

				Log.i(TAG, "-------网络数据加载失败-------");
			}
		});
	}
	//解析json
	protected void processData(String json)
	{
		// 1、解析json串
		Gson gson = new Gson();
		NewsListBean bean = gson.fromJson(json, NewsListBean.class);
		mPicData = bean.data.topnews;
		mListData = bean.data.news;
		adapter = new NewsListPagerAdapter();
		mPager.setAdapter(adapter);
		
		//动态添加点
		//1、清空点
		mPointContainer.removeAllViews();
		for(int i=0;i<mPicData.size();i++){
			View point=new View(mContext);
			point.setBackgroundResource(R.drawable.dot_normal);
			LayoutParams params=new LinearLayout.LayoutParams(5,5);
			params.leftMargin=10;
			if(i==0){
				mTextView.setText(mPicData.get(i).title);
				point.setBackgroundResource(R.drawable.dot_normal);
				
			}
			
			mPointContainer.addView(point, params);
		}
		
		//3、切换点--------给ViewPager设置监听，页面滑动时
		mPager.setOnPageChangeListener(this);
		
		
		//4、处理延时轮播
		if(mswitchPicTask==null){
		mswitchPicTask = new AutoSwitchPicTask();
		}
		mswitchPicTask.start();
		
		//5、mPager设置touch监听,触摸时停止轮播，抬起时开始轮播
		mPager.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event)
			{
				
				switch(event.getAction()){
					case MotionEvent.ACTION_DOWN:
						mswitchPicTask.stop();//停止轮播
						break;
					case MotionEvent.ACTION_UP:
					case MotionEvent.ACTION_CANCEL:
						mswitchPicTask.start();//开始轮播
						break;
					default :
							break;
						
				}
				return false;
			}
		});
		
		//6、给list加载数据
		mListView.setAdapter(new NewsListDatas());
		
	}
	//给listview设置适配器
	class NewsListDatas extends BaseAdapter{

		@Override
		public int getCount()
		{
			if(mListData!=null){
				return mListData.size();
			}
			return 0;
		}

		@Override
		public Object getItem(int position)
		{
			return mListData.get(position);
		}

		@Override
		public long getItemId(int position)
		{
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent)
		{
			
			ViewHolder holder=null;
			if(convertView==null){
				holder=new ViewHolder();
				convertView=View.inflate(mContext, R.layout.news_item_list_data, null);
				holder.imglist=(ImageView) convertView.findViewById(R.id.img_list_data);
				holder.textlist=(TextView) convertView.findViewById(R.id.news_list_text);
				holder.timelist=(TextView) convertView.findViewById(R.id.news_list_time);
			
				convertView.setTag(holder);
			}else{
				holder=(ViewHolder) convertView.getTag();
			}
			
			
			
			NewsListPagerNewsBean  bean=mListData.get(position);
			mBitmapUtils.display(holder.imglist, bean.listimage);

		
			holder.textlist.setText(bean.title);
			holder.timelist.setText(bean.pubdate);
			
			return convertView;
		}
		
		
		
	}
	static  class ViewHolder{
		ImageView imglist;
		TextView textlist;
		TextView timelist;
	}
	
	//设置轮播时的处理
	class AutoSwitchPicTask extends Handler implements Runnable{
		public void run()
		{
			//让viewpager选中下一个
			int item=mPager.getCurrentItem();
			if(item==mPager.getAdapter().getCount()-1){
				item=-1;
			}
			mPager.setCurrentItem(++item);
			postDelayed(this, TIME_DELAY);
			
		}
		public void start()
		{
			stop();
			postDelayed(this, TIME_DELAY);
		}
		public void stop()
		{
			removeCallbacksAndMessages(null);

		}
	}
	
	//设置适配器
	class NewsListPagerAdapter extends PagerAdapter
	{
		@Override
		public int getCount()
		{
			if (mPicData != null) { return mPicData.size(); }
			return 0;
		}
		@Override
		public boolean isViewFromObject(View arg0, Object arg1)
		{
			return arg0 == arg1;
		}
		// 实例化一个页卡
		@Override
		public Object instantiateItem(ViewGroup container, int position)
		{
			ImageView iv = new ImageView(mContext);
			iv.setScaleType(ScaleType.FIT_XY); // 缩放的类型，填充
			iv.setImageResource(R.drawable.pic_item_list_default);
			//設置网络图片
			NewsListPagerTopnesBean bean = mPicData.get(position);
			 String imguri = bean.topimage;
			
			// 网络加载图片数据
			mBitmapUtils.display(iv, imguri);
			container.addView(iv);
			return iv;
	}
		@Override
		public void destroyItem(ViewGroup container, int position, Object object)
		{
			container.removeView((View) object);
		}
	}
	
	//Viewpager的监听
	@Override
	public void onPageScrollStateChanged(int arg0)
	{
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2)
	{
		// TODO Auto-generated method stub
		
	}
	//当页面选中时执行方法
	@Override
	public void onPageSelected(int position)
	{
		int count=mPointContainer.getChildCount();
		
		for(int i=0;i<count;i++){
			View view=mPointContainer.getChildAt(i);
			view.setBackgroundResource(R.drawable.dot_normal);
		}
		NewsListPagerTopnesBean bean=mPicData.get(position);
		
		mPointContainer.getChildAt(position).setBackgroundResource(R.drawable.dot_focus);
		
		//设置标题
		mTextView.setText(bean.title);
		
	}
	@Override
	public void onRefreshing()
	{
		final String url = Constans.SERVICE_URL + newData.url;
		String json=GetDataShared.getString(mContext, url);
		if(!TextUtils.isEmpty(json)){
			// 解析数据
			processData(json);
		}
		
		HttpUtils utils = new HttpUtils();
		utils.send(HttpMethod.GET, url, new RequestCallBack<String>() {

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo)
			{
				// 返回成功，获得数据串
				String result = responseInfo.result;
				Log.i(TAG, "---------网络数据加载成功------" + result);
				//存储数据
				GetDataShared.setString(mContext, url, result);
				// 解析数据
				processData(result);
				
				//刷新完成，告示listView去收起刷新
				mListView.refreshFinish();
				

			}
			@Override
			public void onFailure(HttpException error, String msg)
			{

				Log.i(TAG, "-------网络数据加载失败-------");
				//刷新完成，告示listView去收起刷新
				mListView.refreshFinish();
			}
		});
	}

}
