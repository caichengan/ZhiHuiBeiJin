package com.cca.zhihui.tab;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.cca.zhihui.MainUIActivity;
import com.cca.zhihui.base.NewsCenterBaseMenu;
import com.cca.zhihui.base.TabBasePager;
import com.cca.zhihui.base.newscontentmenu.NewsCenterInteractMenu;
import com.cca.zhihui.base.newscontentmenu.NewsCenterNewsMenu;
import com.cca.zhihui.base.newscontentmenu.NewsCenterPicMenu;
import com.cca.zhihui.base.newscontentmenu.NewsCenterTopicMenu;
import com.cca.zhihui.bean.NewsCenterMenuBean;
import com.cca.zhihui.bean.NewsCenterMenuBean.NewCenterMenuListBean;
import com.cca.zhihui.fragment.LeftFragment;
import com.cca.zhihui.utils.Constans;
import com.cca.zhihui.utils.GetDataShared;
import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

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
	protected static final String	TAG	= "TabNewCenterPager";
	private List<NewsCenterBaseMenu>			mList;//菜单页面的集合
	private NewsCenterMenuBean		mData; //页面对应的数据
	private List<NewCenterMenuListBean> mMenuData;//菜单对应的数据集合
	
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
		mList=new ArrayList<NewsCenterBaseMenu>();
		
		
		
	/*	//2、设置内容区域数据
		TextView tv=new TextView(mContext);
		tv.setText("新闻中心内容区域");
		tv.setTextColor(Color.RED);
		tv.setGravity(Gravity.CENTER);
		
		LayoutParams params=new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
		mContentContainer.addView(tv,params);*/
		/*
		//通过网络获取数据，加载进来
		//HttpUtils utils=new HttpUtils();
		
		
		RequestParams params=new RequestParams();
		//消息头
		params.addHeader("", "");
		
		//1、请求参数
		//post
		NameValuePair pair=new BasicNameValuePair("", "");
		params.addBodyParameter(pair);
		
		//get
		NameValuePair pair=new BasicNameValuePair("", "");
		params.addQueryStringParameter(pair);
		
		utils.send(HttpMethod.GET, Constans.NEWS_CENTER_URL, params, callBack);
		*/
		/**
		 * 缓存处理，进入网络访问之前先进行读取缓存数据
		 */
		
		String json=GetDataShared.getString(mContext, Constans.NEWS_CENTER_URL);
		if(!TextUtils.isEmpty(json)){
			Log.i(TAG, "访问本地数据"+json);
			processJson(json);
		
		}
		//通过网络获取数据，加载进来
		HttpUtils utils=new HttpUtils();
		utils.send(HttpMethod.GET, Constans.NEWS_CENTER_URL, new RequestCallBack<String>(){

			//访问网络成功
			@Override
			public void onSuccess(ResponseInfo<String> responseInfo)
			{
				//取出结果值
				String result=responseInfo.result;
				//
				GetDataShared.setString(mContext, Constans.NEWS_CENTER_URL, result);
				
				Log.i(TAG, "访问网络数据");
				Log.i(TAG, "访问成功"+result);
				processJson( result);
			
				
			}
			//访问网络失败
			@Override
			public void onFailure(HttpException error, String msg)
			{
				//打印栈错误信息
			error.printStackTrace();
				Log.i(TAG, "访问失败"+msg);
			}});
		
	
	
		
	}

	//对json数据进行解析，并且将结果展示到页面上
	public void processJson(String json)
	{
		//1、解析json串数据
		Gson gson=new Gson();
		mData = gson.fromJson(json,  NewsCenterMenuBean.class);
		mMenuData=mData.data;
		//2、将数据展示到界面上
		//2.1展示到左侧菜单
		MainUIActivity mainui=(MainUIActivity) mContext;
		LeftFragment leftfm=mainui.getLeftFragment();

		leftfm.setMenuData(mMenuData);
		//2.2展示到内容区域
		for(int i=0;i<mMenuData.size();i++){
			
			NewCenterMenuListBean  bean=mMenuData.get(i);
			NewsCenterBaseMenu menuPager=null;
			switch(bean.type){
				case  1://新闻
					menuPager=new NewsCenterNewsMenu(mContext,bean);
					break;
				case  10://专题
					menuPager=new NewsCenterTopicMenu(mContext);
					break;
				case  2://组图
					menuPager=new NewsCenterPicMenu(mContext);
					break;
				case  3://互动
					menuPager=new NewsCenterInteractMenu(mContext);
					break;
					
			}
			mList.add(menuPager);
		}
		switchMenuPager(0);
	}


	
	@Override
	public void switchMenuPager(int position)
	{
		mContentContainer.removeAllViews();
		
		//标题的切换
		String title=mMenuData.get(position).title;
		mTextView.setText(title);

		//页面切换
		NewsCenterBaseMenu menuPager=mList.get(position);
		View view=menuPager.getRootView();
		
		mContentContainer.addView(view);
		
		//加载数据
		menuPager.initData();
		
		Log.i(TAG, "----------切换到-------"+position+"----------内容区域");
		
	}
}
