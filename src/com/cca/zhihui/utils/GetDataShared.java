package com.cca.zhihui.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * 存储系统文件数据
 * @author Administrator
 *
 */
public class GetDataShared {

	private static final String DATA_SHARED="wangyixinwen";
	private static SharedPreferences sp;

	/**
	 * 设置deFault为false就不是第一次登陆
	 * @param context
	 * @param key
	 * @param deFault
	 * @return
	 */
	public static boolean setBoolean(Context context,String key,boolean deFault){
		SharedPreferences sp=GetDataShared.getSharedPreference(context);
		Editor edit=sp.edit();
		edit.putBoolean(key, deFault);
		edit.commit();
		return deFault;
		
	}
	/**
	 * 获取sp的实例
	 * @param context
	 * @return
	 */
	public static SharedPreferences getSharedPreference(Context context){
		if(sp==null){
		 sp=context.getSharedPreferences(DATA_SHARED, Context.MODE_PRIVATE);
		}
		return sp;
	}
/**
 * 根据关键字key获取存储的数据values
 * @param context
 * @param name
 * @return
 */
	public static boolean getBoolean(Context context,String key){
		SharedPreferences sp= getSharedPreference(context);
		return sp.getBoolean(key, false);
	
	}
	/**
	 * 获取缓存数据
	 * @param context
	 * @param key
	 * @param deFault
	 * @return
	 */
	public static boolean getBoolean(Context context,String key,boolean deFault){
		SharedPreferences sp= getSharedPreference(context);
		
	 return sp.getBoolean(key, deFault);
		
	}
	
	/**
	 * 设置deFault为false就不是第一次登陆
	 * @param context
	 * @param key
	 * @param deFault
	 * @return
	 */
	public static void setString(Context context,String key,String  deFault){
		SharedPreferences sp=GetDataShared.getSharedPreference(context);
		Editor edit=sp.edit();
		edit.putString(key, deFault);
		edit.commit();
	}
	/**
	 * 根据关键字key获取存储的数据values
	 * @param context
	 * @param name
	 * @return
	 */
		public static String getString(Context context,String key){
			SharedPreferences sp= getSharedPreference(context);
			return sp.getString(key, "");
		
		}
		/**
		 * 获取缓存数据
		 * @param context
		 * @param key
		 * @param deFault
		 * @return
		 */
		public static String getString(Context context,String key,String deFault){
			SharedPreferences sp= getSharedPreference(context);
			
		 return sp.getString(key, deFault);
			
		}
}
