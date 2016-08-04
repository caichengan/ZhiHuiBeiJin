package com.cca.zhihui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.RelativeLayout;

import com.cca.zhihui.utils.GetDataShared;
import com.cca.zhuihui.R;

public class WelcomeActivity extends Activity {

	private RelativeLayout mRelatineLayout;
	public static final  String KEY_IS_FIRST="first_key";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);
		mRelatineLayout=(RelativeLayout) findViewById(R.id.mRelatineLayout);
		
		//��������
		AnimationSet set=new AnimationSet(false);
		/**
		 * ��ת����
		 */
		RotateAnimation mRotateAnimation=new  RotateAnimation(0, 360, 
				Animation.RELATIVE_TO_SELF,0.5f, Animation.RELATIVE_TO_SELF,0.5f);
		mRotateAnimation.setFillEnabled(true);
		mRotateAnimation.setFillAfter(true);
		mRotateAnimation.setDuration(1000);
		
		/**
		 * ͸������ɫ
		 */
		AlphaAnimation mAlphaAnimation=new AlphaAnimation(0, 1.0f);
		mAlphaAnimation.setDuration(1000);
		
		/**
		 * ����
		 */
		ScaleAnimation mScaleAnimation=new ScaleAnimation(0, 1.0f, 0, 1.0f,
				Animation.RELATIVE_TO_SELF,0.5f, Animation.RELATIVE_TO_SELF,0.5f);
		mScaleAnimation.setDuration(1000);
		
		//��Ӷ���
		set.addAnimation(mRotateAnimation);
		set.addAnimation(mAlphaAnimation);
		set.addAnimation(mScaleAnimation);
		
		/**
		 * ��������
		 */
		mRelatineLayout.startAnimation(set);
		set.setAnimationListener(new AnimationListener() {
			
			@Override
			public void onAnimationStart(Animation animation) {
				
			}
			
			@Override
			public void onAnimationRepeat(Animation animation) {
				
			}
			@Override
			public void onAnimationEnd(Animation animation) {
				//����������ʱ�����
				//���ж��Ƿ��ǵ�һ�δ�Ӧ�ã����ݴ洢��
			//Ĭ�ϵ�һ��
				boolean isFirst=GetDataShared.getBoolean(WelcomeActivity.this, KEY_IS_FIRST, true);
				if(isFirst){
					Intent intent =new Intent(WelcomeActivity.this,GuirdActivity.class);
					startActivity(intent);
					finish();
				}else{
					
					Intent intent =new Intent(WelcomeActivity.this,MainUIActivity.class);
					startActivity(intent);
					finish();
				}
			}
		});
	}
}
