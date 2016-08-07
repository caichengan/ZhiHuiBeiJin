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

	private LinearLayout  mHeaderlayout;//����ˢ�µ�view�����û��Զ����view
	private View mCustomHeaderView ;    //�Զ����view
	private View mRefreshView;			//ˢ�µ�view
	private int	mRefreshViewHight;        //ˢ�µ�view�ĸ߶�
	private float	mDownX;     
	private float	mDownY;
	
	private ProgressBar mProssBar;
	private ImageView mArrow;
	private TextView mTvtRehresh;
	private TextView mTvtTime;
	
	private OnRefreshListener mRefreshListener;
	
	private  RotateAnimation mUpDownAnimation;	//����ˢ��
	private RotateAnimation mDownUpAnimation;   //�ͷ�ˢ��
	

	
	private static final int STATE_PULL_DOWN_REFRESH=0; //����ˢ��
	private static final int STATE_RELEASE_REFRESH=1;//�ͷ�ˢ�� 
	private static final int STATE_REFRESH=2;   //����ˢ��
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
		//����ͷ����
		mHeaderlayout=(LinearLayout) View.inflate(getContext(),R.layout.refresh_listview_header, null);
		//��ӵ�listview��Headerview��
		this.addHeaderView(mHeaderlayout);
		
		//��Ҫ����ˢ�µĲ���view
		mRefreshView=mHeaderlayout.findViewById(R.id.refresh_header_part);
		mProssBar=(ProgressBar) mHeaderlayout.findViewById(R.id.refresh_header_pb);
		mArrow=(ImageView) mHeaderlayout.findViewById(R.id.refresh_header_arrow);
		mTvtRehresh=(TextView) mHeaderlayout.findViewById(R.id.refresh_header_state);
		mTvtTime=(TextView) mHeaderlayout.findViewById(R.id.refresh_header_date);
		
		
		//��ͷ��������paddingΪ���������ؿؼ�
		//int widthMeasureSpace=MeasureSpec.makeMeasureSpec(0,MeasureSpec.UNSPECIFIED);
		//int heighMeasureSpace=MeasureSpec.makeMeasureSpec(0,MeasureSpec.UNSPECIFIED);
		//д����0��Ϊ�����ؼ��Ĵ�С������ˢ�²���
		mRefreshView.measure(0, 0);
		//��ȡ���ز��ֵĸ߶�
		mRefreshViewHight = mRefreshView.getMeasuredHeight();
		Log.i(TAG, "-----"+mRefreshViewHight);
		mHeaderlayout.setPadding(0, -mRefreshViewHight, 0, 0);
	}
	
	//�û��Զ���listview����
	public void addCustomView(View headerView){
		this.mCustomHeaderView=headerView;
		mHeaderlayout.addView(headerView);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent ev)
	{
		switch(ev.getAction()){
			case MotionEvent.ACTION_DOWN:
				//��õ�ǰ���µ�xy����
				mDownX = ev.getX();
				mDownY = ev.getY();
			case MotionEvent.ACTION_MOVE:	
				float moveX=ev.getX();
				float moveY=ev.getX();
				
				//����Ļ���ƶ��ľ���
				float diffX= moveX-mDownX;
				float diffY=moveY-mDownY;
				//�������ˢ�£��Լ�����Ӧ������listview��Ӧ
				if(mCurrentSatae==STATE_REFRESH){
					break;
					
				}
				
				//�жϵ�ǰҳ���Ƿ��ǵ�һ��
				if(getFirstVisiblePosition()==0 && diffY>0){
					
					//��ͷ��������paddingTop
					int hiddenHeight=	(int) (mRefreshViewHight-diffY+0.5f);
					mHeaderlayout.setPadding(0, -hiddenHeight, 0, 0);
					
					//diffX<mRefreshViewHight  :����ˢ��
					if(diffY<mRefreshViewHight && mCurrentSatae==STATE_RELEASE_REFRESH){
						//����״̬
						mCurrentSatae=STATE_PULL_DOWN_REFRESH;
						
						//����UI
						refreshUI();
						Log.i(TAG,"---����ˢ��");
						
					}else if(diffY>=mRefreshViewHight && mCurrentSatae==STATE_PULL_DOWN_REFRESH){
						//����״̬
						mCurrentSatae=STATE_RELEASE_REFRESH;
						
						//����UI
						refreshUI();
						Log.i(TAG,"---�ͷ�ˢ�¡�ˢ��״̬");
					}
				//�Լ���Ӧtouch
					return true;
				}
				break;
			case MotionEvent.ACTION_UP:
			case MotionEvent.ACTION_CANCEL:
				mDownY=0;
				
				//�ͷź�ˢ��
				if(mCurrentSatae==STATE_PULL_DOWN_REFRESH){
					//���������ˢ��״̬��ֱ������ȥ��Ҳ��������
					mHeaderlayout.setPadding(0, -mRefreshViewHight, 0, 0);
					
				}else if(mCurrentSatae==STATE_RELEASE_REFRESH){
					//������ͷ�ˢ��״̬���û�ϣ��ȥˢ������----������������
					mCurrentSatae=STATE_REFRESH;
					//����paddingtopΪ0
					mHeaderlayout.setPadding(0, 0, 0, 0);
					
					//����UI
					refreshUI();
					
					//���һ����
					//֪ͨ���������ڴ���ˢ��״̬��ȥ�����ȡ����
					//������֮���ͨѶ���ص�����
					if(mRefreshListener!=null){
						mRefreshListener.onRefreshing();
					}
					
				
					}
		
			default:
				break;
		}
		return super.onTouchEvent(ev);
		
	}
	//����UI
	public void refreshUI(){
		
		switch(mCurrentSatae){
			case STATE_PULL_DOWN_REFRESH://����ˢ��
				//1������ͷ��ʾ��������Ҫ����
				mArrow.setVisibility(View.VISIBLE);
				mProssBar.setVisibility(View.GONE);
				//2��״̬��ʾ
				
				mTvtRehresh.setText("����ˢ��");
				
				//3����ͷ����
				mArrow.startAnimation(mUpDownAnimation);
				break;
			case STATE_RELEASE_REFRESH://�ͷ�ˢ��
				//1������ͷ��ʾ��������Ҫ����
				mArrow.setVisibility(View.VISIBLE);
				mProssBar.setVisibility(View.GONE);
				//2��״̬��ʾ
				
				mTvtRehresh.setText("�ͷ�ˢ��");
				
				//3����ͷ����
				mArrow.startAnimation(mDownUpAnimation);
				break;
			case STATE_REFRESH://����ˢ��
				mArrow.clearAnimation();
				//1������ͷ���ء�������Ҫ��ʾ
				mArrow.setVisibility(View.GONE);
				mProssBar.setVisibility(View.VISIBLE);
				//2��״̬��ʾ
				mTvtRehresh.setText("����ˢ��");
				
				break;
			default:
					break;
		}
	}
	//��¶һ������
	public void setOnRefreshListener(OnRefreshListener listener){
		this.mRefreshListener=listener;
	}
	
	//����һ���ӿڣ����涨��ص�����
	public interface OnRefreshListener{
		/**
		 * ����ˢ��ʱ�Ļص�
		 */
		void onRefreshing();
	}

	/**
	 * ˢ���������ˢ��ҳ�棬״̬����
	 */
	public void refreshFinish()
	{
		//���õ�ǰ���µ�ʱ��
		mTvtTime.setText(getCurrentTime());
		Log.i(TAG, "ˢ�����------");
		//���� ˢ�µ�view
		mHeaderlayout.setPadding(0, -mRefreshViewHight, 0, 0);
		
		//״̬����
		mCurrentSatae=STATE_PULL_DOWN_REFRESH;
		
		//UI����
		refreshUI();
		
	}
	/**
	 * ��ȡ��ǰ��ʱ��
	 */
	public String getCurrentTime(){
		
		long time=System.currentTimeMillis();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
		
		return sdf.format(new Date(time));
		
	}

}
