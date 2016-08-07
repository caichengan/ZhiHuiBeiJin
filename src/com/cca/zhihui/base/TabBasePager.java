package com.cca.zhihui.base;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.cca.zhihui.MainUIActivity;
import com.cca.zhuihui.R;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

/**
 * 
 * @����:com.cca.zhihui.baseactivity
 * @����:TabBasePager
 * @ʱ��:����4:14:51
 * @author Administrator
 * @����: ÿ��ҳ�湲ͬ�����ݣ���ȡ������Ϊ����
 */
public abstract class TabBasePager implements OnClickListener
{
	protected View	mRootView; //����ͼ
	protected Context mContext; //������
	protected TextView mTextView;//title
	protected ImageView mIvMenu;//menu
	protected FrameLayout mContentContainer;//��������

	public TabBasePager(Context context) {
		this.mContext=context;
		this.mRootView = initView();
	}
	protected  View initView(){
		View view =View.inflate(mContext, R.layout.content_tab, null);
		//ʵ�����ݵĲ���
		mTextView=(TextView) view.findViewById(R.id.content_text_title);
		mIvMenu=	(ImageView) view.findViewById(R.id.content_img_menu);
		mContentContainer=(FrameLayout) view.findViewById(R.id.content_tab_container);
		mIvMenu.setOnClickListener(this);
		return view;
	}
	//���ݼ��صķ��������������д
	public  void initData(){
	}
	public View getRootView()
	{
		return mRootView;
	}
	
	//�˵����Ĳ���
	@Override
	public void onClick(View v)
	{

		if(v==mIvMenu){
			toggleSetMenu();
		}
	}
	/**
	 * ����˵���ͼ��رղ˵������ر��ŵ���ʹ�
	 */
	private void toggleSetMenu()
	{
		//��ȡ������ȡ�ò˵�ʵ��������˵������˵��򿪾͹رգ��رվʹ�
		MainUIActivity ui=(MainUIActivity) mContext;
		SlidingMenu menu=ui.getSlidingMenu();
		menu.toggle();
		
	}
	/**
	 * �˵��л��ķ���������Ҫʵ�ֲ˵��л�����Ҫ��д�������
	 * @param position
	 */
	public void switchMenuPager(int position)
	{
		
	}
}
