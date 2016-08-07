package com.cca.zhihui.bean;

import java.util.List;

/**
 * JSON的解析的JAVA bean
 * 
 * @包名:com.cca.zhihui.bean
 * @类名:NewsListPager
 * @时间:上午11:45:55
 * @author Administrator
 * @描述:
 */
public class NewsListBean
{
	public NewsListPagerBean	data;
	public int		retcode;

	public class NewsListPagerBean
	{
		public String							countcommenturl;
		public String							more;
		public List<NewsListPagerNewsBean>		news;
		public String							title;
		public List<NewsListPagerTopicBean>		topic;
		public List<NewsListPagerTopnesBean>	topnews;
	}

	public class NewsListPagerNewsBean
	{
		public boolean	comment;
		public String	commentlist;
		public String	commenturl;
		public long		id;
		public String	listimage;
		public String	pubdate;
		public String	title;
		public String	type;
		public String	url;
	}

	public class NewsListPagerTopicBean
	{
		public String	description;
		public long		id;
		public String	listimage;		// http://10.0.2.2:8080/zhbj/10007/1452327318UU91.jpg
		public int		sort;
		public String	title;
		public String	url;
	}

	public class NewsListPagerTopnesBean
	{
		public boolean	comment;
		public String	commentlist;	// http://10.0.2.2:8080/zhbj/10007/comment_1.json
		public String	commenturl;		// http://zhbj.qianlong.com/client/user/newComment/35301
		public long		id;
		public String	pubdate;
		public String	title;
		public String	topimage;		// http://10.0.2.2:8080/zhbj/10007/1452327318UU91.jpg
		public String	type;
		public String	url;			// http://10.0.2.2:8080/zhbj/10007/724D6A55496A11726628.html
	}
}
