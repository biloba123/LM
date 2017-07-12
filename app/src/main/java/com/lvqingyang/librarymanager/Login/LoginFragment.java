package com.lvqingyang.librarymanager.Login;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lvqingyang.librarymanager.Main2Activity;
import com.lvqingyang.librarymanager.R;
import com.lvqingyang.librarymanager.base.BaseFragment;
import com.lvqingyang.librarymanager.bean.Manager;
import com.lvqingyang.librarymanager.net.RequestHelper;
import com.lvqingyang.librarymanager.net.RequestListener;
import com.lvqingyang.librarymanager.tool.MyPrefence;


/**
 * 　　┏┓　　  ┏┓+ +
 * 　┏┛┻━ ━ ━┛┻┓ + +
 * 　┃　　　　　　  ┃
 * 　┃　　　━　　    ┃ ++ + + +
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
 * Date：2017/4/12
 * Email：biloba12345@gamil.com
 * Info：
 */

public class LoginFragment extends BaseFragment {

    private TextView registertext;
    private EditText nameet;
    private EditText pwdet;
    private TextView logintext;
    private static final String TAG = "LoginFragment";

    private OnLoginListener mListener;


    public static LoginFragment newInstance() {
        Bundle args = new Bundle();
        LoginFragment fragment = new LoginFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected View initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_login,container,false);
        return view;
    }

    @Override
    protected void initView(View view) {
        this.logintext = (TextView) view.findViewById(R.id.login_text);
        this.pwdet = (EditText) view.findViewById(R.id.pwd_et);
        this.nameet = (EditText) view.findViewById(R.id.nick_et);
        this.registertext = (TextView) view.findViewById(R.id.register_text);
    }

    @Override
    protected void setListener() {
        registertext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onRegister();
            }
        });

        logintext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkInput()) {
                    showLoadingDialog(R.string.logining);
                    final String id=nameet.getText().toString(),
                        pwd=pwdet.getText().toString();
                    RequestHelper.login(getActivity(),
                            id,pwd, new RequestListener() {
                                @Override
                                public void onResponse(String res) {
                                    cancelLoadingDialog();
                                    Log.d(TAG, "onResponse: "+res);
                                    if (res.charAt(0)=='{') {
                                        Manager manager=new Gson().fromJson(res, Manager.class);
                                        if (manager.getAuthority()==0) {
                                            showInfoToast(R.string.wait_check);
                                        }else if (manager.getAuthority()>0){
                                            showSuccToast(R.string.login_succ);
                                            MyPrefence.getInstance(getContext())
                                                    .saveUser(manager);
                                            Main2Activity.start(getActivity());
                                            getActivity().finish();
                                        }
                                    }else {
                                        int resultCode=Integer.valueOf(res);
                                        if(resultCode<-2) {
                                            showErrorToast(R.string.error);
                                        }else if(resultCode<0){
                                            showErrorToast(R.string.wrong_pwd);
                                        }
                                    }
                                }

                                @Override
                                public void onError() {
                                    cancelLoadingDialog();
                                    showErrorToast(R.string.error);
                                }
                            });
                }
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
    protected void getBundleExtras(Bundle bundle) {

    }


    //检查输入
    private boolean checkInput(){
        if (nameet.getText().toString().isEmpty()) {
            showInfoToast(R.string.empty_code);
            return false;
        }

        if (pwdet.getText().toString().isEmpty()) {
            showInfoToast(R.string.empty_pwd);
            return false;
        }

        return true;
    }

    public  interface OnLoginListener{
        void onRegister();
    }

    //    与Activity交互
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnLoginListener) {
            mListener = (OnLoginListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnAddStudentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
}
