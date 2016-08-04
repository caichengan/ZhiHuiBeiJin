package com.cca.zhihui.base.newscontentmenu;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.cca.zhihui.base.NewsCenterBaseMenu;

/**
 * 
 *@����:com.cca.zhihui.base.newscontentmenu
 *@����:NewsCenterNewsMenu
 *@ʱ��:����9:36:02
 * @author Administrator
 *@����:��������ҳ���л�����Ӧ������
 */
public class NewsCenterInteractMenu extends NewsCenterBaseMenu
{

	public NewsCenterInteractMenu(Context context) {
		super(context);
	}

	@Override
	protected View initView()
	{
		
		TextView tv=new TextView(mContext);
		tv.setText("����������");
		tv.setTextSize(20);
		tv.setTextColor(Color.RED);
		tv.setGravity(Gravity.CENTER);
		return tv;
	}

	
}
