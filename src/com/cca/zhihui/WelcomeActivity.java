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
		
		//开启动画
		AnimationSet set=new AnimationSet(false);
		/**
		 * 旋转动画
		 */
		RotateAnimation mRotateAnimation=new  RotateAnimation(0, 360, 
				Animation.RELATIVE_TO_SELF,0.5f, Animation.RELATIVE_TO_SELF,0.5f);
		mRotateAnimation.setFillEnabled(true);
		mRotateAnimation.setFillAfter(true);
		mRotateAnimation.setDuration(1000);
		
		/**
		 * 透明渐变色
		 */
		AlphaAnimation mAlphaAnimation=new AlphaAnimation(0, 1.0f);
		mAlphaAnimation.setDuration(1000);
		
		/**
		 * 缩放
		 */
		ScaleAnimation mScaleAnimation=new ScaleAnimation(0, 1.0f, 0, 1.0f,
				Animation.RELATIVE_TO_SELF,0.5f, Animation.RELATIVE_TO_SELF,0.5f);
		mScaleAnimation.setDuration(1000);
		
		//添加动画
		set.addAnimation(mRotateAnimation);
		set.addAnimation(mAlphaAnimation);
		set.addAnimation(mScaleAnimation);
		
		/**
		 * 开启动画
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
				//动画结束的时候调用
				//先判断是否是第一次打开应用（数据存储）
			//默认第一次
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
