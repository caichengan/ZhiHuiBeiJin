# ZhiHuiBeiJin
一款类似于网易新闻客户端的app

### 注意：因为工程中用到开源框架中的SlidingMenu和xUtils，在运行之前需要先把这两者的libiary先导入工程中才可以正常运行。

### ViewPager和Tab的结合使用，在这里使用了开源库ViewPagerIndicator来实现其功能，这里还引用gson-2.21.jar 包来进行解析json数据。

### 下拉刷新
1. 实现原理： 通过设置listView 的headerLayout 的paddingTop来实现
2. 控件的测量：
	1. measure: 测量控件的宽度和高度（widthMeasureSpec，heightMeasureSpec）
		1. 32位的01010101010101010
	2. MeasureSpec ： 
		1. mode： 
			1. EXACTLY 30dp 
			2. AT_MOST 100dp
			3. UNSPECIFIED 
		2. size： 实际大小

3. 刷新状态的介绍：
	1. 需要下拉刷新
	2. 释放刷新
	3. 正在刷新


### 上拉刷新的实现和下拉刷新大同小异