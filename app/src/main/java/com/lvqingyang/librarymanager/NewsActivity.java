package com.lvqingyang.librarymanager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.lvqingyang.librarymanager.base.BaseActivity;
import com.lvqingyang.librarymanager.bean.News;

public class NewsActivity extends BaseActivity {

    private static final String KEY_NEWS = "NEWS";
    private TextView mTitleTv;
    private TextView mTimeTv;
    private TextView mContentTv;
    private News mNews;

    public static void start(Context context, News n) {
        Intent starter = new Intent(context, NewsActivity.class);
        starter.putExtra(KEY_NEWS,n);
        context.startActivity(starter);
    }


    @Override
    protected void initContentView(Bundle bundle) {
        setContentView(R.layout.activity_news);
    }

    @Override
    protected void initView() {
        initToolbar(R.string.news,true);
        mTitleTv = (TextView) findViewById(R.id.title_tv);
        mTimeTv = (TextView) findViewById(R.id.time_tv);
        mContentTv = (TextView) findViewById(R.id.content_tv);
    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void initData() {
        mNews= (News) getIntent().getSerializableExtra(KEY_NEWS);
    }

    @Override
    protected void setData() {
        mTitleTv.setText(mNews.getTitle());
        mTimeTv.setText(mNews.getCreateTime());
        mContentTv.setText(mNews.getContent());
    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }
}
