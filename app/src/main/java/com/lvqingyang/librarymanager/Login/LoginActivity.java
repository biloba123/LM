package com.lvqingyang.librarymanager.Login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.lvqingyang.librarymanager.Main2Activity;
import com.lvqingyang.librarymanager.R;
import com.lvqingyang.librarymanager.base.BaseActivity;
import com.lvqingyang.librarymanager.tool.MyPrefence;


public class LoginActivity extends BaseActivity implements RegisterFragment.OnRegisterListener ,
        LoginFragment.OnLoginListener{

    private FragmentManager mFragmentManager;
    private LoginFragment mLoginFrag;
    private RegisterFragment mRegisterFrag;


    public static void start(Context context) {
        Intent starter = new Intent(context, LoginActivity.class);
//        starter.putExtra();
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        if (MyPrefence.getInstance(this).isLogined()){
            Main2Activity.start(this);
            finish();
        }
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initContentView(Bundle bundle) {
        setContentView(R.layout.activity_login);
    }

    @Override
    protected void initView() {
    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void initData() {
        mFragmentManager=getSupportFragmentManager();
        Fragment fragment=mFragmentManager.findFragmentByTag(LoginFragment.class.getName());
        mLoginFrag=fragment==null?LoginFragment.newInstance():(LoginFragment)fragment;
        fragment=mFragmentManager.findFragmentByTag(RegisterFragment.class.getName());
        mRegisterFrag=fragment==null?RegisterFragment.newInstance():(RegisterFragment)fragment;
    }

    @Override
    protected void setData() {
        mFragmentManager.beginTransaction()
                .add(R.id.container,mLoginFrag,LoginFragment.class.getName())
                .commit();
    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    //登录回调
    @Override
    public void onLogin() {
        mFragmentManager.popBackStack();
    }


    //注册回调
    @Override
    public void onRegister() {
        mFragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.fragment_right_in,R.anim.fragment_left_out,
                        R.anim.fragment_left_in,R.anim.fragment_right_out)
                .replace(R.id.container,mRegisterFrag,RegisterFragment.class.getName())
                .addToBackStack(null)
                .commit();
    }

}
