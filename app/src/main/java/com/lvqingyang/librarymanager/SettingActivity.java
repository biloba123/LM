package com.lvqingyang.librarymanager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.lvqingyang.librarymanager.Login.LoginActivity;
import com.lvqingyang.librarymanager.base.BaseActivity;
import com.lvqingyang.librarymanager.tool.MyPrefence;
import com.lvqingyang.librarymanager.view.SettingItem;

public class SettingActivity extends BaseActivity {

    private com.lvqingyang.librarymanager.view.SettingItem logoutsi;
    private static Activity mActivity;

    public static void start(Context context) {
        if (context instanceof Activity) {
            mActivity= (Activity) context;
        }
        Intent starter = new Intent(context, SettingActivity.class);
//        starter.putExtra();
        context.startActivity(starter);
    }


    @Override
    protected void initContentView(Bundle bundle) {
        setContentView(R.layout.activity_setting);
    }

    @Override
    protected void initView() {
        initToolbar(R.string.setting,true);
        this.logoutsi = (SettingItem) findViewById(R.id.logout_si);
    }

    @Override
    protected void setListener() {
        logoutsi.setClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mActivity != null) {
                    mActivity.finish();
                    mActivity=null;
                }
                MyPrefence.getInstance(SettingActivity.this).logOut();
                LoginActivity.start(SettingActivity.this);
                finish();
            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setData() {

    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }
}
