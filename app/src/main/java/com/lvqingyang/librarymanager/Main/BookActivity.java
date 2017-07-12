package com.lvqingyang.librarymanager.Main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.lvqingyang.librarymanager.Main2Activity;
import com.lvqingyang.librarymanager.R;
import com.lvqingyang.librarymanager.base.BaseActivity;
import com.lvqingyang.librarymanager.net.RequestHelper;
import com.lvqingyang.librarymanager.net.RequestListener;
import com.lvqingyang.librarymanager.tool.SolidRVBaseAdapter;
import com.pojo.OrderBooks;
import com.pojo.User;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class BookActivity extends BaseActivity {

    private de.hdodenhof.circleimageview.CircleImageView headiv;
    private android.widget.TextView nametv;
    private android.widget.TextView teltv;
    private android.widget.TextView idtv;
    private RecyclerView recyclerview;
    private static final String KEY_USER = "USER";
    private static final String KEY_BOOKS = "Y_BOOKS";
    private static final String KEY_REQUEST = "_REQUEST";
    private static final String KEY_ORDER_ID = "ORDER_ID";
    private User mUser;
    private List<OrderBooks> mBooks;
    private int mRequest;
    private static final String TAG = "BookActivity";
    private String mOrderId;
    private FloatingActionButton mFloatingActionButton;


    public static void start(Context context, User u, List<OrderBooks> bookList,String orrderId, int request) {
        Intent starter = new Intent(context, BookActivity.class);
        starter.putExtra(KEY_USER,u);
        starter.putExtra(KEY_REQUEST,request);
        starter.putExtra(KEY_ORDER_ID,orrderId);
        starter.putParcelableArrayListExtra(KEY_BOOKS, (ArrayList<? extends Parcelable>) bookList);
        context.startActivity(starter);
    }

    @Override
    protected void initContentView(Bundle bundle) {
        setContentView(R.layout.activity_book);
    }

    @Override
    protected void initView() {
        mRequest=getIntent().getIntExtra(KEY_REQUEST,-1);
        if (mRequest== Main2Activity.REQUEST_LEND) {
            initToolbar(getString(R.string.borrow),true);
        }else {
            initToolbar(getString(R.string.return_),true);
        }
        this.recyclerview = (RecyclerView) findViewById(R.id.recycler_view);
        this.idtv = (TextView) findViewById(R.id.id_tv);
        this.teltv = (TextView) findViewById(R.id.tel_tv);
        this.nametv = (TextView) findViewById(R.id.name_tv);
        this.headiv = (CircleImageView) findViewById(R.id.head_iv);
        mFloatingActionButton = (FloatingActionButton) findViewById(R.id.fab);
    }

    @Override
    protected void setListener() {
        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mRequest== Main2Activity.REQUEST_LEND) {
                    RequestHelper.confirmBorrow(BookActivity.this, mUser.getOpenID(), mOrderId, new RequestListener() {
                        @Override
                        public void onResponse(String res) {
                            Log.d(TAG, "onResponse: "+res);
                            if (res.equals("1")) {
                                showSuccToast(R.string.borrow_succ);
                                finish();
                            }else {
                                showErrorToast(R.string.error_);
                            }
                        }

                        @Override
                        public void onError() {
                            showErrorToast(R.string.error);
                        }
                    });
                }else {
                    RequestHelper.confirmReturn(BookActivity.this, mUser.getOpenID(), mOrderId, new RequestListener() {
                        @Override
                        public void onResponse(String res) {
                            Log.d(TAG, "onResponse: "+res);
                            if (res.equals("1")) {
                                showSuccToast(R.string.return_succ);
                                finish();
                            }else {
                                showErrorToast(R.string.error_);
                            }
                        }

                        @Override
                        public void onError() {
                            showErrorToast(R.string.error);
                        }
                    });
                }
            }
        });
    }
    
    @Override
    protected void initData() {
        Log.d(TAG, "initData: ");
        Intent data=getIntent();
        mUser=data.getParcelableExtra(KEY_USER);
        mBooks=data.getParcelableArrayListExtra(KEY_BOOKS);
        mOrderId=data.getStringExtra(KEY_ORDER_ID);


        
    }

    @Override
    protected void setData() {
        nametv.setText(mUser.getName());
        teltv.setText(mUser.getTelephone());
        idtv.setText(mUser.getIdCard());

        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        recyclerview.setAdapter(new SolidRVBaseAdapter<OrderBooks>(this, mBooks) {
            @Override
            protected void onBindDataToView(SolidCommonViewHolder holder, OrderBooks bean, int position) {
                holder.setText(R.id.name_tv,bean.getBook().getName());
                holder.setText(R.id.author_tv,bean.getBook().getAuthor());
                holder.setText(R.id.publisher_tv,bean.getBook().getPublisher());
                holder.setText(R.id.barcode_tv,bean.getBarCode());
                if (!bean.getBook().getImg().equals("不详")) {
                    holder.setImageFromInternet(R.id.cover_iv,bean.getBook().getImg());
                }
            }

            @Override
            public int getItemLayoutID(int viewType) {
                return R.layout.item_book;
            }

        });
    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(mRequest== Main2Activity.REQUEST_LEND?
//                R.menu.menu_book:R.menu.menu_book_,menu);
//        return super.onCreateOptionsMenu(menu);
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.menu_borrow:
//
//                break;
//            case R.id.menu_return:
//
//                break;
//        }
//        return super.onOptionsItemSelected(item);
//    }
}
