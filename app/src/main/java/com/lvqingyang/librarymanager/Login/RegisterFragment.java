package com.lvqingyang.librarymanager.Login;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lvqingyang.librarymanager.R;
import com.lvqingyang.librarymanager.base.BaseFragment;
import com.lvqingyang.librarymanager.net.RequestHelper;
import com.lvqingyang.librarymanager.net.RequestListener;

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

public class RegisterFragment extends BaseFragment {

    private TextView logintext;
    private EditText nicket;
    private EditText telet;
    private EditText pwdet;
    private LinearLayout login;
    private TextView registertext;
    private static final String TAG = "RegisterFragment";

    private OnRegisterListener mListener;


    public static RegisterFragment newInstance() {
        Bundle args = new Bundle();
        RegisterFragment fragment = new RegisterFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected View initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_register,container,false);
        return view;
    }

    @Override
    protected void initView(View view) {
        this.registertext = (TextView) view.findViewById(R.id.register_text);
        this.login = (LinearLayout) view.findViewById(R.id.login);
        this.pwdet = (EditText) view.findViewById(R.id.pwd_et);
        this.telet = (EditText) view.findViewById(R.id.tel_et);
        this.nicket = (EditText) view.findViewById(R.id.nick_et);
        this.logintext = (TextView) view.findViewById(R.id.login_text);
    }

    @Override
    protected void setListener() {
        logintext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onLogin();
            }
        });

        //注册
        registertext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkInput()) {
                    showLoadingDialog(R.string.registering);
                    RequestHelper.register(getActivity(), nicket.getText().toString(), pwdet.getText().toString()
                            , telet.getText().toString(), new RequestListener() {
                                @Override
                                public void onResponse(String res) {
                                    Log.d(TAG, "onResponse: "+res);
                                    showSuccDialog(getString(R.string.register_succ),"工号："+res+"\n"+getString(R.string.wait),null);
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
        if (nicket.getText().toString().isEmpty()) {
            showInfoToast(R.string.empty_nick);
            return false;
        }

        if (pwdet.getText().toString().isEmpty()) {
            showInfoToast(R.string.empty_pwd);
            return false;
        }
        if (telet.getText().toString().isEmpty()) {
            showInfoToast(R.string.empty_tel);
            return false;
        }


        return true;
    }


    public  interface OnRegisterListener{
        void onLogin();
    }

    //    与Activity交互
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnRegisterListener) {
            mListener = (OnRegisterListener) context;
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
