package com.lvqingyang.librarymanager.tool;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.lvqingyang.librarymanager.bean.Manager;

/**
 *　　┏┓　　  ┏┓+ +
 *　┏┛┻━ ━ ━┛┻┓ + +
 *　┃　　　　　　  ┃
 *　┃　　　━　　    ┃ ++ + + +
 *     ████━████     ┃+
 *　┃　　　　　　  ┃ +
 *　┃　　　┻　　  ┃
 *　┃　　　　　　  ┃ + +
 *　┗━┓　　　┏━┛
 *　　　┃　　　┃　　　　　　　　　　　
 *　　　┃　　　┃ + + + +
 *　　　┃　　　┃
 *　　　┃　　　┃ +  神兽保佑
 *　　　┃　　　┃    代码无bug！　
 *　　　┃　　　┃　　+　　　　　　　　　
 *　　　┃　 　　┗━━━┓ + +
 *　　　┃ 　　　　　　　┣┓
 *　　　┃ 　　　　　　　┏┛
 *　　　┗┓┓┏━┳┓┏┛ + + + +
 *　　　　┃┫┫　┃┫┫
 *　　　　┗┻┛　┗┻┛+ + + +
 * ━━━━━━神兽出没━━━━━━
 * Author：LvQingYang
 * Date：2017/3/15
 * Email：biloba12345@gamil.com
 * Info：封装SharpPrefence
 */



public class MyPrefence {
   private static final String PREFENCE_NAME = "library";
   private static MyPrefence mPrefence;
   private SharedPreferences mSharedPreferences;
   private SharedPreferences.Editor mEditor;

    private static final String KEY_IS_LOGIN = "IS_LOGIN";
    private static final String KEY_USER = "USER";


   private MyPrefence(Context context){
       mSharedPreferences=context.getSharedPreferences(PREFENCE_NAME,Context.MODE_PRIVATE);
       mEditor=mSharedPreferences.edit();
   }

   public synchronized static MyPrefence getInstance(Context context) {
       if ( mPrefence== null) {
           mPrefence = new MyPrefence(context);
       }
       return mPrefence;
   }

   public String getString(String tag){
       return  mSharedPreferences.getString(tag,null);
   }

   public void saveString(String tag,String value){
       mEditor.putString(tag,value).apply();
   }

   public int getInt(String tag){
       return mSharedPreferences.getInt(tag,-1);
   }

    public int getInt(String tag,int def){
        return mSharedPreferences.getInt(tag,def);
    }

   public void saveInt(String tag,int value){
       mEditor.putInt(tag,value).apply();
   }

   public boolean getBool(String tag){
       return mSharedPreferences.getBoolean(tag,false);
   }

   public void saveBool(String tag,boolean value){
       mEditor.putBoolean(tag,value).apply();
   }



   //登录成功后保存用户信息
    public void saveUser(Manager manager){
        saveBool(KEY_IS_LOGIN,true);
        saveString(KEY_USER,new Gson().toJson(manager));
    }

    public Manager getUser(){
        String s=getString(KEY_USER);
        if (s == null) {
            return null;
        }else {
            return new Gson().fromJson(s, Manager.class);
        }
    }

    //是否登录
    public boolean isLogined(){
        return getBool(KEY_IS_LOGIN);
    }

    //退出登录
    public void logOut(){
        saveBool(KEY_IS_LOGIN,false);
        mEditor.remove(KEY_USER)
                .apply();
    }
}
