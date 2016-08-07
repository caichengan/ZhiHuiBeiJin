package com.cca.zhihui.widget;

import java.sql.Date;
import java.text.SimpleDateFormat;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.cca.zhuihui.R;

public class RefreshListView extends ListView
{

	private LinearLayout  mHeaderlayout;//包含刷新的view，和用户自定义的view
	private View mCustomHeaderView ;    //自定义的view
	private View mRefreshView;			//刷新的view
	private int	mRefreshViewHight;        //刷新的view的高度
	private float	mDownX;     
	private float	mDownY;
	
	private ProgressBar mProssBar;
	private ImageView mArrow;
	private TextView mTvtRehresh;
	private TextView mTvtTime;
	
	private OnRefreshListener mRefreshListener;
	
	private  RotateAnimation mUpDownAnimation;	//下拉刷新
	private RotateAnimation mDownUpAnimation;   //释放刷新
	

	
	private static final int STATE_PULL_DOWN_REFRESH=0; //下拉刷新
	private static final int STATE_RELEASE_REFRESH=1;//释放刷新 
	private static final int STATE_REFRESH=2;   //正在刷新
	private static final String	TAG	= "RefreshListView";
	private int mCurrentSatae=STATE_PULL_DOWN_REFRESH;
	
	public RefreshListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initHeaderLayout();
		initAnimation();

	}

	public RefreshListView(Context context) {
		super(context);
		initHeaderLayout();
		initAnimation();
	}
	

	private void initAnimation()
	{
		mDownUpAnimation=new RotateAnimation(0, 180,
		                                     Animation.RELATIVE_TO_SELF, 0.5f, 
		                                     Animation.RELATIVE_TO_SELF, 0.5f);
		mDownUpAnimation.setDuration(200);
		mDownUpAnimation.setFillEnabled(true);
		mDownUpAnimation.setFillAfter(true);
		
		
		mUpDownAnimation=new RotateAnimation(180, 360,
		                                     Animation.RELATIVE_TO_SELF, 0.5f, 
		                                     Animation.RELATIVE_TO_SELF, 0.5f);
		mUpDownAnimation.setDuration(200);
		mUpDownAnimation.setFillEnabled(true);
		mUpDownAnimation.setFillAfter(true);
	}

	private void initHeaderLayout()
	{
		//加载头布局
		mHeaderlayout=(LinearLayout) View.inflate(getContext(),R.layout.refresh_listview_header, null);
		//添加到listview的Headerview中
		this.addHeaderView(mHeaderlayout);
		
		//需要隐藏刷新的布局view
		mRefreshView=mHeaderlayout.findViewById(R.id.refresh_header_part);
		mProssBar=(ProgressBar) mHeaderlayout.findViewById(R.id.refresh_header_pb);
		mArrow=(ImageView) mHeaderlayout.findViewById(R.id.refresh_header_arrow);
		mTvtRehresh=(TextView) mHeaderlayout.findViewById(R.id.refresh_header_state);
		mTvtTime=(TextView) mHeaderlayout.findViewById(R.id.refresh_header_date);
		
		
		//给头布局设置padding为负数来隐藏控件
		//int widthMeasureSpace=MeasureSpec.makeMeasureSpec(0,MeasureSpec.UNSPECIFIED);
		//int heighMeasureSpace=MeasureSpec.makeMeasureSpec(0,MeasureSpec.UNSPECIFIED);
		//写两个0，为测量控件的大小，隐藏刷新部分
		mRefreshView.measure(0, 0);
		//获取隐藏部分的高度
		mRefreshViewHight = mRefreshView.getMeasuredHeight();
		Log.i(TAG, "-----"+mRefreshViewHight);
		mHeaderlayout.setPadding(0, -mRefreshViewHight, 0, 0);
	}
	
	//用户自定的listview部分
	public void addCustomView(View headerView){
		this.mCustomHeaderView=headerView;
		mHeaderlayout.addView(headerView);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent ev)
	{
		switch(ev.getAction()){
			case MotionEvent.ACTION_DOWN:
				//获得当前按下的xy坐标
				mDownX = ev.getX();
				mDownY = ev.getY();
			case MotionEvent.ACTION_MOVE:	
				float moveX=ev.getX();
				float moveY=ev.getX();
				
				//在屏幕上移动的距离
				float diffX= moveX-mDownX;
				float diffY=moveY-mDownY;
				//如果正在刷新，自己不响应，交给listview响应
				if(mCurrentSatae==STATE_REFRESH){
					break;
					
				}
				
				//判断当前页面是否是第一个
				if(getFirstVisiblePosition()==0 && diffY>0){
					
					//给头布局设置paddingTop
					int hiddenHeight=	(int) (mRefreshViewHight-diffY+0.5f);
					mHeaderlayout.setPadding(0, -hiddenHeight, 0, 0);
					
					//diffX<mRefreshViewHight  :下拉刷新
					if(diffY<mRefreshViewHight && mCurrentSatae==STATE_RELEASE_REFRESH){
						//更新状态
						mCurrentSatae=STATE_PULL_DOWN_REFRESH;
						
						//更新UI
						refreshUI();
						Log.i(TAG,"---下拉刷新");
						
					}else if(diffY>=mRefreshViewHight && mCurrentSatae==STATE_PULL_DOWN_REFRESH){
						//更新状态
						mCurrentSatae=STATE_RELEASE_REFRESH;
						
						//更新UI
						refreshUI();
						Log.i(TAG,"---释放刷新、刷新状态");
					}
				//自己响应touch
					return true;
				}
				break;
			case MotionEvent.ACTION_UP:
			case MotionEvent.ACTION_CANCEL:
				mDownY=0;
				
				//释放后刷新
				if(mCurrentSatae==STATE_PULL_DOWN_REFRESH){
					//如果是下拉刷新状态，直接缩回去，也就是隐藏
					mHeaderlayout.setPadding(0, -mRefreshViewHight, 0, 0);
					
				}else if(mCurrentSatae==STATE_RELEASE_REFRESH){
					//如果是释放刷新状态，用户希望去刷新数据----正在涮新数据
					mCurrentSatae=STATE_REFRESH;
					//设置paddingtop为0
					mHeaderlayout.setPadding(0, 0, 0, 0);
					
					//更新UI
					refreshUI();
					
					//最后一步：
					//通知调用者现在处于刷新状态，去网络获取数据
					//两个类之间的通讯，回调方法
					if(mRefreshListener!=null){
						mRefreshListener.onRefreshing();
					}
					
				
					}
		
			default:
				break;
		}
		return super.onTouchEvent(ev);
		
	}
	//更新UI
	public void refreshUI(){
		
		switch(mCurrentSatae){
			case STATE_PULL_DOWN_REFRESH://下拉刷新
				//1、：箭头显示、进度条要隐藏
				mArrow.setVisibility(View.VISIBLE);
				mProssBar.setVisibility(View.GONE);
				//2、状态显示
				
				mTvtRehresh.setText("下拉刷新");
				
				//3、箭头动画
				mArrow.startAnimation(mUpDownAnimation);
				break;
			case STATE_RELEASE_REFRESH://释放刷新
				//1、：箭头显示、进度条要隐藏
				mArrow.setVisibility(View.VISIBLE);
				mProssBar.setVisibility(View.GONE);
				//2、状态显示
				
				mTvtRehresh.setText("释放刷新");
				
				//3、箭头动画
				mArrow.startAnimation(mDownUpAnimation);
				break;
			case STATE_REFRESH://正在刷新
				mArrow.clearAnimation();
				//1、：箭头隐藏、进度条要显示
				mArrow.setVisibility(View.GONE);
				mProssBar.setVisibility(View.VISIBLE);
				//2、状态显示
				mTvtRehresh.setText("正在刷新");
				
				break;
			default:
					break;
		}
	}
	//暴露一个方法
	public void setOnRefreshListener(OnRefreshListener listener){
		this.mRefreshListener=listener;
	}
	
	//定义一个接口，里面定义回调方法
	public interface OnRefreshListener{
		/**
		 * 正在刷新时的回调
		 */
		void onRefreshing();
	}

	/**
	 * 刷新完成收起刷新页面，状态重置
	 */
	public void refreshFinish()
	{
		//设置当前更新的时间
		mTvtTime.setText(getCurrentTime());
		Log.i(TAG, "刷新完成------");
		//隐藏 刷新的view
		mHeaderlayout.setPadding(0, -mRefreshViewHight, 0, 0);
		
		//状态重置
		mCurrentSatae=STATE_PULL_DOWN_REFRESH;
		
		//UI更新
		refreshUI();
		
	}
	/**
	 * 获取当前的时间
	 */
	public String getCurrentTime(){
		
		long time=System.currentTimeMillis();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
		
		return sdf.format(new Date(time));
		
	}

}
