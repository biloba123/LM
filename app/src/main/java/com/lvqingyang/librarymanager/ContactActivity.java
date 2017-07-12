package com.lvqingyang.librarymanager;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.Menu;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lvqingyang.librarymanager.adapter.ContactAdapter;
import com.lvqingyang.librarymanager.base.BaseActivity;
import com.lvqingyang.librarymanager.bean.Contact;
import com.lvqingyang.librarymanager.bean.Manager;
import com.lvqingyang.librarymanager.net.RequestHelper;
import com.lvqingyang.librarymanager.net.RequestListener;

import java.util.ArrayList;
import java.util.List;

import me.yokeyword.indexablerv.IndexableAdapter;
import me.yokeyword.indexablerv.IndexableLayout;

public class ContactActivity extends BaseActivity {

    private me.yokeyword.indexablerv.IndexableLayout il;
    private ContactAdapter mAdapter;
    private static final String TAG = "ContactActivity";
    List<Contact> contactList=new ArrayList<Contact>();


    public static void start(Context context) {
        Intent starter = new Intent(context, ContactActivity.class);
//        starter.putExtra();
        context.startActivity(starter);
    }

    @Override
    protected void initContentView(Bundle bundle) {
        setContentView(R.layout.activity_contact);
        this.il = (IndexableLayout) findViewById(R.id.il);
    }

    @Override
    protected void initView() {
        initToolbar(R.string.contact,true);

        mAdapter=new ContactAdapter(this);
        il.setLayoutManager(new LinearLayoutManager(this));
        il.setAdapter(mAdapter);
        il.setOverlayStyle_MaterialDesign(getResources().getColor(R.color.contactColor));
        il.setCompareMode(IndexableLayout.MODE_ALL_LETTERS);
    }

    @Override
    protected void setListener() {
        mAdapter.setOnItemContentClickListener(new IndexableAdapter.OnItemContentClickListener<Contact>() {
            @Override
            public void onItemClick(View v, int originalPosition, int currentPosition, Contact entity) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+contactList.get( originalPosition).getManager().getPhone()));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void initData() {
        RequestHelper.getAllManagers(this, new RequestListener() {
            @Override
            public void onResponse(String res) {
                cancelLoadingDialog();
                Log.d(TAG, "onResponse: "+res);
                List<Manager> managerlist=new Gson().fromJson(res, new TypeToken<List<Manager>>(){}.getType());
                for (Manager manager : managerlist) {
//                    if (manager.getAuthority()==1||manager.getAuthority()==-3) {
                        contactList.add(new Contact(manager));
//                    }
                }
                mAdapter.setDatas(contactList);
            }

            @Override
            public void onError() {
                cancelLoadingDialog();
                showErrorToast(R.string.error);
            }
        });
    }

    @Override
    protected void setData() {

    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_contact,menu);
        return super.onCreateOptionsMenu(menu);
    }
}
