package com.cca.zhihui.fragment;

import android.view.View;
import android.widget.TextView;

import com.cca.zhihui.baseactivity.BaseFragment;
/**
 * 
 *@����:com.cca.zhihui.fragment
 *@����:LeftFragment
 *@ʱ��:����12:05:42
 * @author Administrator
 *@����:���˵�
 */
public class LeftFragment extends BaseFragment {

	@Override
	protected View initView() {
		TextView tv=new TextView(mActivity);
		tv.setText("�˵�����");
		
		return tv;
	}
}



