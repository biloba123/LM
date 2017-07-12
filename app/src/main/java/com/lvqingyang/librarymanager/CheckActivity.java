package com.lvqingyang.librarymanager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.lvqingyang.librarymanager.Main.ContactFragment;
import com.lvqingyang.librarymanager.base.BaseActivity;
import com.lvqingyang.librarymanager.bean.Manager;
import com.lvqingyang.librarymanager.net.RequestHelper;
import com.lvqingyang.librarymanager.net.RequestListener;
import com.lvqingyang.librarymanager.tool.SolidRVBaseAdapter;

import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class CheckActivity extends BaseActivity {


    private android.support.design.widget.TabLayout tablayout;
    private android.support.v4.view.ViewPager viewpager;
    private static final String TAG = "CheckActivity";

    public static void start(Context context) {
        Intent starter = new Intent(context, CheckActivity.class);
//        starter.putExtra();
        context.startActivity(starter);
    }

    @Override
    protected void initContentView(Bundle bundle) {
        setContentView(R.layout.activity_check);
    }

    @Override
    protected void initView() {
        initToolbar(R.string.check_new,true);
        this.viewpager = (ViewPager) findViewById(R.id.view_pager);
        this.tablayout = (TabLayout) findViewById(R.id.tab_layout);

        viewpager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));
        viewpager.setOffscreenPageLimit(viewpager.getAdapter().getCount());
        // 设置ViewPager的数据等
        tablayout.setupWithViewPager(viewpager);
        //tab均分,适合少的tab
        tablayout.setTabMode(TabLayout.MODE_FIXED);
    }

    @Override
    protected void setListener() {

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

    //ViewPager适配器
    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
            addTabs(mFragmentList,mFragmentTitleList);
        }


        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }


        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    //添加tab
    private void addTabs(List<Fragment> fragments, List<String> titles){
        String stringArray[]=getResources().getStringArray(R.array.tabs);
        fragments.add(ContactFragment.newInstance(true).setListener(new ContactFragment.ManagerListener() {
            @Override
            public List<Manager> findManager(List<Manager> managers) {
                List<Manager> managers1=new ArrayList<Manager>();
                for (Manager manager : managers) {
                    if (manager.getAuthority()==0) {
                        managers1.add(manager);
                    }
                }
                return managers1;
            }

            @Override
            public void onClick(final Manager manager, final SolidRVBaseAdapter adapter) {
                final SweetAlertDialog dialog=new SweetAlertDialog(CheckActivity.this,SweetAlertDialog.NORMAL_TYPE)
                        .setTitleText(getString(R.string.check_))
                        .setContentText("是否通过" +manager.getName()+
                                "的请求？")
                        .setCancelText(getString(R.string.deny_))
                        .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(final SweetAlertDialog sweetAlertDialog) {
                                sweetAlertDialog.setTitleText(getString(R.string.loading))
                                        .setContentText(null)
                                        .setCancelText(null)
                                        .setConfirmClickListener(null)
                                        .changeAlertType(SweetAlertDialog.PROGRESS_TYPE);
                                RequestHelper.refuseRegister(CheckActivity.this, manager.getId()+"", new RequestListener() {
                                    @Override
                                    public void onResponse(String res) {
                                        Log.d(TAG, "onResponse: "+res);
                                        sweetAlertDialog.dismissWithAnimation();
                                        showSuccToast(R.string.succ);
                                        adapter.removeItem(manager);
                                    }

                                    @Override
                                    public void onError() {
                                        sweetAlertDialog.dismissWithAnimation();
                                        showErrorToast(R.string.error);
                                    }
                                });
                            }
                        })
                        .setConfirmText(getString(R.string.appept_))
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(final SweetAlertDialog sweetAlertDialog) {
                                sweetAlertDialog.setTitleText(getString(R.string.loading))
                                        .setContentText(null)
                                        .setCancelText(null)
                                        .setConfirmClickListener(null)
                                        .changeAlertType(SweetAlertDialog.PROGRESS_TYPE);
                                RequestHelper.acceptRegister(CheckActivity.this, manager.getId()+"", new RequestListener() {
                                    @Override
                                    public void onResponse(String res) {
                                        Log.d(TAG, "onResponse: "+res);
                                        sweetAlertDialog.dismissWithAnimation();
                                        showSuccToast(R.string.succ);
                                        adapter.removeItem(manager);
                                    }

                                    @Override
                                    public void onError() {
                                        sweetAlertDialog.dismissWithAnimation();
                                        showErrorToast(R.string.error);
                                    }
                                });
                            }
                        });
                dialog.setCanceledOnTouchOutside(true);
                dialog.show();

            }
        }));
        titles.add(stringArray[0]);
        fragments.add(ContactFragment.newInstance(true).setListener(new ContactFragment.ManagerListener() {
            @Override
            public List<Manager> findManager(List<Manager> managers) {
                List<Manager> managers1=new ArrayList<Manager>();
                for (Manager manager : managers) {
                    if (manager.getAuthority()==1||manager.getAuthority()==-3) {
                        managers1.add(manager);
                        managers1.add(manager);
                    }
                }
                return managers1;
            }

            @Override
            public void onClick(Manager manager, SolidRVBaseAdapter adapter) {

            }
        }));
        titles.add(stringArray[1]);
    }
}
