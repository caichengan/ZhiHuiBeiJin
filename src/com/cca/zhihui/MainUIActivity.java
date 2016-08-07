package com.cca.zhihui;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Window;

import com.cca.zhihui.fragment.ContentFragment;
import com.cca.zhihui.fragment.LeftFragment;
import com.cca.zhuihui.R;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;
/**
 * 
 *@����:com.cca.zhihui
 *@����:MainUIActivity
 *@ʱ��:����1:38:55
 * @author Administrator
 *@����:�����������ҳ�����˵�
 */
public class MainUIActivity extends SlidingFragmentActivity {

	private final static String TAG_CONTENT="content"; //��ҳ
	private final static String TAG_LEFT_MENU="left_menu"; //���
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//ȡ������
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		//������������
		setContentView(R.layout.activity_mainui);
		
		//����behind����
		setBehindContentView(R.layout.main_left);
		//���SlidingMenu��ʵ��
		SlidingMenu menu=getSlidingMenu();
		//����ģʽ
		menu.setMode(SlidingMenu.LEFT);
		//ָ�����ǲ˵��ı�Ե����Ļ��Ե�ľ���
		menu.setBehindOffset(180);
		//�˵���������
		//menu.setBehindWidth(100);
		menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		
		//����fragment�ķ�ʽ����ҳ��
		initFragment();
		
	}

	private void initFragment() {
		FragmentManager  fm=getSupportFragmentManager();
		
		//��������
		FragmentTransaction transaction=fm.beginTransaction();
		
		//�����ҳfragmeng
		transaction.replace(R.id.main_container, new ContentFragment(), TAG_CONTENT);
		
		//������fragmeng
		transaction.replace(R.id.main_left, new LeftFragment(), TAG_LEFT_MENU);
		
		transaction.commit();
		
	}

	/**
	 * ��ȡ���˵�
	 */
	public LeftFragment getLeftFragment()
	{
		FragmentManager fm=getSupportFragmentManager();
		return (LeftFragment) fm.findFragmentByTag(TAG_LEFT_MENU);
		
	}

	/**
	 * ��õ�ǰ��ʵ��Fragment
	 * @return
	 */
	public ContentFragment getContentFragment()

	{
		FragmentManager fm=getSupportFragmentManager();
		return (ContentFragment) fm.findFragmentByTag(TAG_CONTENT);

	}
}
