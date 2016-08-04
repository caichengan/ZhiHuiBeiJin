package com.cca.zhihui;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.cca.zhihui.utils.GetDataShared;
import com.cca.zhuihui.R;
import com.jeremyfeinstein.slidingmenu.lib.CustomViewAbove.OnPageChangeListener;
/**
 * ��������
 * @author Administrator
 *
 */
public class GuirdActivity extends Activity implements  android.support.v4.view.ViewPager.OnPageChangeListener {
	private ViewPager mViewPager; //ViewPager
	private Button mButton;           //��ʼ���鰴ť
	private List<ImageView> mList; //ҳ�漯��
	private LinearLayout mLinearLayout;//װ�������
	private View mPointSelect; //��̬ѡ��ĵ�
	private int mSpace;
	private static int items[]={R.drawable.guide_1,R.drawable.guide_2,R.drawable.guide_3};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//ȡ������
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_guird);
		mViewPager=(ViewPager) findViewById(R.id.guird_pager);
		mButton=(Button) findViewById(R.id.btn_start_button);
		mLinearLayout=(LinearLayout) findViewById(R.id.ll_guird_point);
		mPointSelect=(View) findViewById(R.id.re_point_selected);
		
		initData();
		

		//������ڵ�֮��Ĳ���
		mPointSelect.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
			@Override
			public void onGlobalLayout() {
				//������UI�������ָı��ʱ�����
				if(mList==null){
					return ;
				}
				//����һ��
				mPointSelect.getViewTreeObserver().removeGlobalOnLayoutListener(this);
				mSpace = mLinearLayout.getChildAt(1).getLeft()-mLinearLayout.getChildAt(0).getLeft();
			}
		});
		
	}
	/**
	 * ��ʼ������
	 */
	private void initData() {
		mList = new ArrayList<ImageView>();
		
		for(int i=0;i<items.length;i++){
			//�½�ImageView
			ImageView iv=new ImageView(this);
			iv.setImageResource(items[i]);
			//�����Ļ
			iv.setScaleType(ScaleType.FIT_XY);
			mList.add(iv);
			
			//��̬���һ����̬��
			View point=new View(this);
			point.setBackgroundResource(R.drawable.guird_point_normal);
			LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(30, 30);
			//����
			if(i!=0){
				params.leftMargin=15;
			}
			mLinearLayout.addView(point, params);
			
		}
		//��viewpager����������adapter-----list
		mViewPager.setAdapter(new GuirdViewPagerAdapter());
		
		//����ViewPagerҳ���ѡ�������
		mViewPager.setOnPageChangeListener(this);
	}
	/**
	 * viewpageer��������
	 * @author Administrator
	 *
	 */
	class GuirdViewPagerAdapter extends PagerAdapter{

		@Override
		public int getCount() {
			if(mList!=null){return mList.size();}
			return 0;
		}

		@Override
		public boolean isViewFromObject(View view, Object obj) {
			return view==obj;

		}
		/**
		 * ����һ��ҳ��
		 */
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			//��viewpager���Ƴ�imageview
			//ImageView iv=mList.get(position);
			container.removeView((View)object);
		}
		/**
		 * ʵ����һ��ҳ��
		 */
		@Override
		public Object instantiateItem(ViewGroup container, int position) {
		
			ImageView iv=mList.get(position);
			//��ӵ�viewpager��
			container.addView(iv);
			return iv;
		}
	}
	
	//�ڵ�����״̬�ı�ʱ�Ļص�
	@Override
	public void onPageScrollStateChanged(int position) {
		
		
		}
	
	//���ڻ�����ʱ��ص�
	//position��ǰ������ҳ��
	//positionset �ٷֱ�
	//arg2 ����ʱ�ľ������
	@Override
	public void onPageScrolled(int position, float positionoffset, int arg2) {
		//1���Ի�����������

		RelativeLayout.LayoutParams params= (android.widget.RelativeLayout.LayoutParams) mPointSelect.getLayoutParams();
		//2������marginLeft
		params.leftMargin=(int) (mSpace*position+mSpace*positionoffset+0.5f);
		
		mPointSelect.setLayoutParams(params);
	}
	//��viewpagerĳ��ҳ��ѡ��ʱ�ص�
	//@position��ǰѡ�е�ҳ��
	@Override
	public void onPageSelected(int position) {
		//����ֻ�л��������һ��ҳ��ʱ����ʾ��ť
		mButton.setVisibility((position==mList.size()-1?View.VISIBLE:View.GONE));
	}
	/**
	 * �����ťʵ�ֿ�ʼ����
	 * @param view
	 */
	public void startShared(View view){
		if(view.getId()==R.id.btn_start_button){
			goMainUIActivity();
			
		}
	}
	//��ת����ҳ
	private void goMainUIActivity() {
		GetDataShared.setBoolean(this, WelcomeActivity.KEY_IS_FIRST, false);
		Intent intent=new Intent(GuirdActivity.this,MainUIActivity.class);
		startActivity(intent);
		finish();
	}
}
