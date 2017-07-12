package com.lvqingyang.librarymanager.Main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lvqingyang.librarymanager.R;
import com.lvqingyang.librarymanager.base.BaseFragment;
import com.lvqingyang.librarymanager.bean.Manager;
import com.lvqingyang.librarymanager.net.RequestHelper;
import com.lvqingyang.librarymanager.net.RequestListener;
import com.lvqingyang.librarymanager.tool.SolidRVBaseAdapter;

import java.util.List;
import java.util.Random;

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

public class ContactFragment extends BaseFragment {

    private RecyclerView recyclerView;
    private static final String TAG = "ContactFragment";
    private static final String KEY_SHOW_TAG = "SHOW_TAG";

    public interface ManagerListener{
        List<Manager> findManager(List<Manager> managers);
        void onClick(Manager manager, SolidRVBaseAdapter adapter);
    }

    public ManagerListener listener;


    public static ContactFragment newInstance(boolean isShowTag) {
        Bundle args = new Bundle();
        args.putBoolean(KEY_SHOW_TAG,isShowTag);
        ContactFragment fragment = new ContactFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public ContactFragment setListener(ManagerListener listener) {
        this.listener = listener;
        return this;
    }

    @Override
    protected View initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_contact,container,false);
    }

    @Override
    protected void initView(View view) {
        recyclerView= (RecyclerView) view.findViewById(R.id.recycler_view);
    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void initData() {
        RequestHelper.getAllManagers(getActivity(), new RequestListener() {
            @Override
            public void onResponse(String res) {
                Log.d(TAG, "onResponse: "+res);
                final List<Manager> managerlist=new Gson().fromJson(res, new TypeToken<List<Manager>>(){}.getType());
                List<Manager> managers=listener.findManager(managerlist);
                SolidRVBaseAdapter adapter=new SolidRVBaseAdapter<Manager>(getActivity(), managers) {
                    @Override
                    protected void onBindDataToView(SolidCommonViewHolder holder, Manager bean, int position) {
                        holder.setText(R.id.name_tv,bean.getName());
                        holder.setText(R.id.tel_tv,bean.getPhone());
                        holder.setText(R.id.code_tv,bean.getId()+"");
                        if (bean.getName() != null&&bean.getName().length()>0) {
                            holder.setText(R.id.first_tv,bean.getName().charAt(0)+"");
                        }

                        int[] colorArr=mContext.getResources().getIntArray(R.array.colors);
                        int index=new Random().nextInt(colorArr.length-1)%colorArr.length;
                        ((ImageView)holder.getView(R.id.color_cv)).setImageResource(R.color.accent_amber+index);
                        TextView tagTv=holder.getView(R.id.tag_tv);
                        if (getArguments().getBoolean(KEY_SHOW_TAG)) {
                            switch (bean.getAuthority()) {
                                case 0:
                                    tagTv.setText(R.string.uncheck);
                                    tagTv.setBackgroundResource(R.drawable.tag_bg_uncheck);
                                    break;
                                case 1:
                                    tagTv.setText(R.string.appept);
                                    tagTv.setBackgroundResource(R.drawable.tag_bg_accept);
                                    break;
                                case -3:
                                    tagTv.setText(R.string.deny);
                                    tagTv.setBackgroundResource(R.drawable.tag_bg_deny);
                                    break;
                                default:
                                    tagTv.setVisibility(View.GONE);
                            }
                        }else {
                            tagTv.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public int getItemLayoutID(int viewType) {
                        return R.layout.item_contact__;
                    }

                    @Override
                    protected void onItemClick(int position, Manager bean) {
                        super.onItemClick(position, bean);
                        if (listener != null) {
                            listener.onClick(bean, this);
                        }
                    }
                };
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            }

            @Override
            public void onError() {
                showErrorToast(R.string.error);
            }
        });
    }

    @Override
    protected void setData() {

    }

    @Override
    protected void getBundleExtras(Bundle bundle) {

    }
}
