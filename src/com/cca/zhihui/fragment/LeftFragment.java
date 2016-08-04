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
 *@����:com.cca.zhihui.fragment
 *@����:LeftFragment
 *@ʱ��:����12:05:42
 * @author Administrator
 *@����:���˵�
 */
public class LeftFragment extends BaseFragment implements OnItemClickListener {

	public static final String	TAG	= "LeftFragment";
	private List<NewCenterMenuListBean> mMenuData;  //�˵���Ӧ������
	private ListView	listview;
	private int mCurrent;
	private NewsMenuAdapter	menuAdapter;
	@Override
	protected View initView() {
	/*	TextView tv=new TextView(mActivity);
		tv.setText("�˵�����");
		return tv;*/
		
		listview = new ListView(mActivity);
		listview.setBackgroundColor(Color.BLACK);//���ñ���ɫΪ��ɫ
		listview.setDividerHeight(0); //ȥ���ָ���
		listview.setPadding(0, 50, 0,0);
		listview.setCacheColorHint(Color.TRANSPARENT);
		listview.setSelector(android.R.color.transparent);
		return listview;
		
	}

	/**
	 * ���ò˵�����
	 * @param mMenuData
	 */
	public void setMenuData(List<NewCenterMenuListBean> datas)
	{
		//����Ĭ��ѡ�еĵ�һ��item
		this.mCurrent=0;
		
		//���ݵĽ���
		this.mMenuData=datas;
		
		menuAdapter = new NewsMenuAdapter();
		//����չʾ---adapter---list
		listview.setAdapter(menuAdapter);
	
		//����listview��Ŀ�ĵ���¼�
		listview.setOnItemClickListener(this);
	}
	//listview������
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
				//û�и���ʱ
				convertView=View.inflate(mActivity, R.layout.newsmenu, null);
			}
			
			TextView tv=(TextView) convertView.findViewById(R.id.news_menu);
			String title=mMenuData.get(position).title;
			tv.setText(title);
			
			//�ж�ôĬ�����µ�
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
	 * listview����Ŀ�ĵ���¼�
	 */
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id)
	{
		//��������
		if(mCurrent==position){
			return;
		}
		//1��ѡ�ж�Ӧ����
		this.mCurrent=position;
		//UI����
		menuAdapter.notifyDataSetChanged();
		
		//2������˵�
		MainUIActivity mainui=(MainUIActivity) mActivity;
		mainui.getSlidingMenu().toggle();
		
		//3���Ҳ���������ĸı�
		ContentFragment contentFra=mainui.getContentFragment();
		contentFra.switchMenuPager(mCurrent);
	}
	
}



