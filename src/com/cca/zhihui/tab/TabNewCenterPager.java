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
 *@����:com.cca.zhihui.baseactivity
 *@����:TabHomePager
 *@ʱ��:����5:08:06
 * @author Administrator
 *@����:����tab��Ӧ���������Ľ���
 */
public class TabNewCenterPager extends TabBasePager
{
	protected static final String	TAG	= "TabNewCenterPager";
	private List<NewsCenterBaseMenu>			mList;//�˵�ҳ��ļ���
	private NewsCenterMenuBean		mData; //ҳ���Ӧ������
	private List<NewCenterMenuListBean> mMenuData;//�˵���Ӧ�����ݼ���
	
	public TabNewCenterPager(Context context) {
		super(context);
	}
	
	/**
	 * ��������
	 */
	@Override
	public void initData()
	{
		//1�����ñ�������
		mTextView.setText("��������");
		mIvMenu.setVisibility(View.VISIBLE);
		mList=new ArrayList<NewsCenterBaseMenu>();
		
		
		
	/*	//2������������������
		TextView tv=new TextView(mContext);
		tv.setText("����������������");
		tv.setTextColor(Color.RED);
		tv.setGravity(Gravity.CENTER);
		
		LayoutParams params=new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
		mContentContainer.addView(tv,params);*/
		/*
		//ͨ�������ȡ���ݣ����ؽ���
		//HttpUtils utils=new HttpUtils();
		
		
		RequestParams params=new RequestParams();
		//��Ϣͷ
		params.addHeader("", "");
		
		//1���������
		//post
		NameValuePair pair=new BasicNameValuePair("", "");
		params.addBodyParameter(pair);
		
		//get
		NameValuePair pair=new BasicNameValuePair("", "");
		params.addQueryStringParameter(pair);
		
		utils.send(HttpMethod.GET, Constans.NEWS_CENTER_URL, params, callBack);
		*/
		/**
		 * ���洦�������������֮ǰ�Ƚ��ж�ȡ��������
		 */
		
		String json=GetDataShared.getString(mContext, Constans.NEWS_CENTER_URL);
		if(!TextUtils.isEmpty(json)){
			Log.i(TAG, "���ʱ�������"+json);
			processJson(json);
		
		}
		//ͨ�������ȡ���ݣ����ؽ���
		HttpUtils utils=new HttpUtils();
		utils.send(HttpMethod.GET, Constans.NEWS_CENTER_URL, new RequestCallBack<String>(){

			//��������ɹ�
			@Override
			public void onSuccess(ResponseInfo<String> responseInfo)
			{
				//ȡ�����ֵ
				String result=responseInfo.result;
				//
				GetDataShared.setString(mContext, Constans.NEWS_CENTER_URL, result);
				
				Log.i(TAG, "������������");
				Log.i(TAG, "���ʳɹ�"+result);
				processJson( result);
			
				
			}
			//��������ʧ��
			@Override
			public void onFailure(HttpException error, String msg)
			{
				//��ӡջ������Ϣ
			error.printStackTrace();
				Log.i(TAG, "����ʧ��"+msg);
			}});
		
	
	
		
	}

	//��json���ݽ��н��������ҽ����չʾ��ҳ����
	public void processJson(String json)
	{
		//1������json������
		Gson gson=new Gson();
		mData = gson.fromJson(json,  NewsCenterMenuBean.class);
		mMenuData=mData.data;
		//2��������չʾ��������
		//2.1չʾ�����˵�
		MainUIActivity mainui=(MainUIActivity) mContext;
		LeftFragment leftfm=mainui.getLeftFragment();

		leftfm.setMenuData(mMenuData);
		//2.2չʾ����������
		for(int i=0;i<mMenuData.size();i++){
			
			NewCenterMenuListBean  bean=mMenuData.get(i);
			NewsCenterBaseMenu menuPager=null;
			switch(bean.type){
				case  1://����
					menuPager=new NewsCenterNewsMenu(mContext,bean);
					break;
				case  10://ר��
					menuPager=new NewsCenterTopicMenu(mContext);
					break;
				case  2://��ͼ
					menuPager=new NewsCenterPicMenu(mContext);
					break;
				case  3://����
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
		
		//������л�
		String title=mMenuData.get(position).title;
		mTextView.setText(title);

		//ҳ���л�
		NewsCenterBaseMenu menuPager=mList.get(position);
		View view=menuPager.getRootView();
		
		mContentContainer.addView(view);
		
		//��������
		menuPager.initData();
		
		Log.i(TAG, "----------�л���-------"+position+"----------��������");
		
	}
}
