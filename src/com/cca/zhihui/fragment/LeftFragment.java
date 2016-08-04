package com.cca.zhihui.fragment;

import android.view.View;
import android.widget.TextView;

import com.cca.zhihui.baseactivity.BaseFragment;
/**
 * 
 *@包名:com.cca.zhihui.fragment
 *@类名:LeftFragment
 *@时间:下午12:05:42
 * @author Administrator
 *@描述:左侧菜单
 */
public class LeftFragment extends BaseFragment {

	@Override
	protected View initView() {
		TextView tv=new TextView(mActivity);
		tv.setText("菜单内容");
		
		return tv;
	}
}



