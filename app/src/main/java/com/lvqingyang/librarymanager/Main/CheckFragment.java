package com.lvqingyang.librarymanager.Main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lvqingyang.librarymanager.R;
import com.lvqingyang.librarymanager.base.BaseFragment;
import com.lvqingyang.librarymanager.bean.Manager;
import com.lvqingyang.librarymanager.net.RequestHelper;
import com.lvqingyang.librarymanager.net.RequestListener;
import com.lvqingyang.librarymanager.tool.SolidRVBaseAdapter;

import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * 　　┏┓　　  ┏┓+ +
 * 　┏┛┻━ ━ ━┛┻┓ + +
 * 　┃　　　             ┃
 * 　┃　　　━　　   ┃ ++ + + +
 * ████━████     ┃+
 * 　┃　　　　　　  ┃ +
 * 　┃　　　┻　　  ┃
 * 　┃　　　　　　  ┃ + +
 * 　┗━┓　　　┏━┛
 * 　　　┃　　　┃
 * 　　　┃　　　┃ + + + +
 * 　　　┃　　　┃
 * 　　　┃　　　┃ +  神兽保佑
 * 　　　┃　　　┃    代码无bug！
 * 　　　┃　　　┃　　+
 * 　　　┃　 　　┗━━━┓ + +
 * 　　　┃ 　　　　　　　┣┓
 * 　　　┃ 　　　　　　　┏┛
 * 　　　┗┓┓┏━┳┓┏┛ + + + +
 * 　　　　┃┫┫　┃┫┫
 * 　　　　┗┻┛　┗┻┛+ + + +
 * ━━━━━━神兽出没━━━━━━
 * Author：LvQingYang
 * Date：2017/6/13
 * Email：biloba12345@gamil.com
 * Info：
 */

public class CheckFragment extends BaseFragment {

    private android.support.design.widget.TabLayout tablayout;
    private android.support.v4.view.ViewPager viewpager;
    private static final String TAG = "CheckFragment";


    public static CheckFragment newInstance() {
        
        Bundle args = new Bundle();
        
        CheckFragment fragment = new CheckFragment();
        fragment.setArguments(args);
        return fragment;
    }
    
    @Override
    protected View initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_check,container,false);
    }

    @Override
    protected void initView(View view) {
        this.viewpager = (ViewPager) view.findViewById(R.id.view_pager);
        this.tablayout = (TabLayout) view.findViewById(R.id.tab_layout);
    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setData() {
        viewpager.setAdapter(new ViewPagerAdapter(getChildFragmentManager()));
        viewpager.setOffscreenPageLimit(viewpager.getAdapter().getCount());
        // 设置ViewPager的数据等
        tablayout.setupWithViewPager(viewpager);
        //tab均分,适合少的tab
        tablayout.setTabMode(TabLayout.MODE_FIXED);
    }

    @Override
    protected void getBundleExtras(Bundle bundle) {

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
                final SweetAlertDialog dialog=new SweetAlertDialog(getActivity(),SweetAlertDialog.NORMAL_TYPE)
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
                                RequestHelper.refuseRegister(getActivity(), manager.getId()+"", new RequestListener() {
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
                                RequestHelper.acceptRegister(getActivity(), manager.getId()+"", new RequestListener() {
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
