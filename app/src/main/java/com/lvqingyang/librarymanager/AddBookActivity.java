package com.lvqingyang.librarymanager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.target.Target;
import com.lvqingyang.librarymanager.Reptile.DouBanBook;
import com.lvqingyang.librarymanager.base.BaseActivity;
import com.lvqingyang.librarymanager.net.RequestHelper;
import com.lvqingyang.librarymanager.net.RequestListener;
import com.lvqingyang.librarymanager.tool.AppConstants;
import com.lvqingyang.librarymanager.tool.ColorArt;

import me.zhanghai.android.materialratingbar.MaterialRatingBar;

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
 * Date：2017/7/9
 * Email：biloba12345@gamil.com
 * Info：
 */

public class AddBookActivity extends BaseActivity {
    private android.widget.ImageView imgiv;
    private android.widget.TextView nametv;
    private android.widget.TextView authortv;
    private android.widget.TextView publishertv;
    private android.widget.TextView pubdatetv;
    private android.widget.TextView scoretv;
    private static final String KEY_BOOK = "BOOK";
    private DouBanBook mBook;
    private RelativeLayout mRelativeLayout;
    private MaterialRatingBar mRatingBar;
    private FloatingActionButton mFab;

    public static void start(Context context,DouBanBook b) {
        Intent starter = new Intent(context, AddBookActivity.class);
        starter.putExtra(KEY_BOOK,b);
        context.startActivity(starter);
    }

    @Override
    protected void initContentView(Bundle bundle) {
        setContentView(R.layout.dialog_add_book);
    }

    @Override
    protected void initView() {
        final android.support.v7.widget.Toolbar toolbar=initToolbar(R.string.add_book,true);
        this.scoretv = (TextView) findViewById(R.id.score_tv);
        this.pubdatetv = (TextView) findViewById(R.id.pubdate_tv);
        this.publishertv = (TextView) findViewById(R.id.publisher_tv);
        this.authortv = (TextView) findViewById(R.id.author_tv);
        this.nametv = (TextView) findViewById(R.id.name_tv);
        this.imgiv = (ImageView) findViewById(R.id.img_iv);
        mRelativeLayout = (RelativeLayout) findViewById(R.id.bg_rl);
        mRatingBar = (MaterialRatingBar) findViewById(R.id.rb);
        mFab = (FloatingActionButton) findViewById(R.id.fab);


        mBook= (DouBanBook) getIntent().getSerializableExtra(KEY_BOOK);

        scoretv.setText(mBook.getScore());
        pubdatetv.setText("出版时间："+mBook.getPubdate());
        publishertv.setText("出版社："+mBook.getPublisher());
        authortv.setText("作者："+mBook.getAuthor());
        nametv.setText(mBook.getName());
        mRatingBar.setNumStars(5);
        mRatingBar.setRating((float) Double.parseDouble(mBook.getScore())/2);
        Glide.with(this)
                .load(mBook.getImg())
                .listener(new com.bumptech.glide.request.RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        ColorArt colorAt=new ColorArt(AppConstants.drawableToBitmap(resource));
                        mRelativeLayout.setBackgroundColor(colorAt.getBackgroundColor());
                        toolbar.setBackgroundColor(colorAt.getBackgroundColor());
                        return false;
                    }
                })
                .into(imgiv);
    }

    @Override
    protected void setListener() {
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestHelper.addDouBanBook(AddBookActivity.this, mBook, new RequestListener() {
                    @Override
                    public void onResponse(String res) {
                        showSuccToast(R.string.add_succ);
                        finish();
                    }

                    @Override
                    public void onError() {
                        showErrorToast(R.string.error);
                    }
                });
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
