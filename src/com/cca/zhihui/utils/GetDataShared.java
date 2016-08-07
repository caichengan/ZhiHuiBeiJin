package com.cca.zhihui.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * �洢ϵͳ�ļ�����
 * @author Administrator
 *
 */
public class GetDataShared {

	private static final String DATA_SHARED="wangyixinwen";
	private static SharedPreferences sp;

	/**
	 * ����deFaultΪfalse�Ͳ��ǵ�һ�ε�½
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
	 * ��ȡsp��ʵ��
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
 * ���ݹؼ���key��ȡ�洢������values
 * @param context
 * @param name
 * @return
 */
	public static boolean getBoolean(Context context,String key){
		SharedPreferences sp= getSharedPreference(context);
		return sp.getBoolean(key, false);
	
	}
	/**
	 * ��ȡ��������
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
	 * ����deFaultΪfalse�Ͳ��ǵ�һ�ε�½
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
	 * ���ݹؼ���key��ȡ�洢������values
	 * @param context
	 * @param name
	 * @return
	 */
		public static String getString(Context context,String key){
			SharedPreferences sp= getSharedPreference(context);
			return sp.getString(key, "");
		
		}
		/**
		 * ��ȡ��������
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
