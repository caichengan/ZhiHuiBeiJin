package com.cca.zhihui.bean;

import java.util.List;

/**
 * 
 *@����:com.cca.zhihui.bean
 *@����:NewsCenterMenuBean
 *@ʱ��:����11:22:21
 * @author Administrator
 *@����:TODO
 */
public class NewsCenterMenuBean
{
	public List<NewCenterMenuListBean> data;
	public List<Long> extend;
	public long retcode;
	
	
	public class NewCenterMenuListBean{
		public List<NewsCenterNewsItemBean> children;
		public long id;
		public String title;
		public int type;
		
		public String url;
		public  String url1;
		
		public String dayurl;
		public String excurl;
		public String weekurl;
		
	}
	public class NewsCenterNewsItemBean{
		public long id;
		public String title;
		public int type;
		public String url;
	}
	
	
	

}
