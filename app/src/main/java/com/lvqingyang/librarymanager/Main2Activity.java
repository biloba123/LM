package com.lvqingyang.librarymanager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.zxing.activity.CaptureActivity;
import com.lvqingyang.librarymanager.Main.BookActivity;
import com.lvqingyang.librarymanager.Reptile.bean.Book2;
import com.lvqingyang.librarymanager.Reptile.bean.SQLBook;
import com.lvqingyang.librarymanager.base.BaseActivity;
import com.lvqingyang.librarymanager.bean.Manager;
import com.lvqingyang.librarymanager.bean.News;
import com.lvqingyang.librarymanager.bean.Statistic;
import com.lvqingyang.librarymanager.net.RequestHelper;
import com.lvqingyang.librarymanager.net.RequestListener;
import com.lvqingyang.librarymanager.tool.MyOkHttp;
import com.lvqingyang.librarymanager.tool.MyPrefence;
import com.lvqingyang.librarymanager.tool.SolidRVBaseAdapter;
import com.pojo.Book;
import com.pojo.OrderBooks;
import com.pojo.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class Main2Activity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener, BaseSliderView.OnSliderClickListener {

    private NavigationView navigationView;
    private SliderLayout mSliderLayout;
    private HashMap<String,Integer> mImages=new HashMap<>();
    private static final String TAG = "Main2Activity";
    private android.widget.LinearLayout statisticll;
    private android.widget.LinearLayout borrowll;
    private android.widget.LinearLayout returnll;
    private android.support.v7.widget.RecyclerView newsrv;
    private LinearLayout mNewsLl;
    public static final int REQUEST_RETURN = 685;
    public static final int REQUEST_LEND = 555;
    private static final int REQUEST_ADD = 34;
    private LinearLayout mAddBookLl;


    public static void start(Context context) {
        Intent starter = new Intent(context, Main2Activity.class);
//        starter.putExtra();
        context.startActivity(starter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        List<SQLBook>list= DataSupport.findAll(SQLBook.class);
//        Log.d(TAG, "onResume: "+list.size());
//        for (SQLBook sqlBook : list) {
//            Log.d(TAG, "onResume: "+sqlBook.getJson());
//        }
//        RequestHelper.addDouBanBook(this, list, new RequestListener() {
//            @Override
//            public void onResponse(String res) {
//                Log.d(TAG, "onResponse: "+res);
//            }
//
//            @Override
//            public void onError() {
//
//            }
//        });


//        BookReptile.getTagList(new ReptileListener<Tag>() {
//            @Override
//            public void onResult(List<Tag> list) {
//                BookReptile.getBookList(list.subList(112,list.size()), new ReptileListener<String>() {
//                    @Override
//                    public void onResult(List<String> list) {
//                        APIHelper.getBookList(list, new ReptileListener<DouBanBook>() {
//                            @Override
//                            public void onResult(List<DouBanBook> list) {
//                                for (DouBanBook douBanBook : list) {
//                                    Log.d(TAG, "onResult: "+new Gson().toJson(douBanBook));
//                                }
//                            }
//
//                            @Override
//                            public void onError(Throwable e) {
//
//                            }
//                        });
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//                });
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                showErrorToast("爬取出错");
//            }
//        });

//        DataSupport.deleteAll(SQLBook.class);

//        List<BookId> ids= DataSupport.findAll(BookId.class);
//        APIHelper.getBookList(ids.subList(903,ids.size()), new ReptileListener<DouBanBook>() {
//                @Override
//                public void onResult(List<DouBanBook> list) {
////                    for (DouBanBook douBanBook : list) {
////                        Log.d(TAG, "onResult: "+new Gson().toJson(douBanBook));
////                    }
//                }
//
//                @Override
//                public void onError(Throwable e) {
//
//                }
//            });

    }


    @Override
    protected void initContentView(Bundle bundle) {
        setContentView(R.layout.activity_main2);
    }

    @Override
    protected void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.scan);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);

        mSliderLayout = (SliderLayout) findViewById(R.id.slider);

        this.newsrv = (RecyclerView) findViewById(R.id.news_rv);
        this.returnll = (LinearLayout) findViewById(R.id.return_ll);
        this.borrowll = (LinearLayout) findViewById(R.id.borrow_ll);
        this.statisticll = (LinearLayout) findViewById(R.id.statistic_ll);
        mAddBookLl = (LinearLayout) findViewById(R.id.add_book_ll);
        mNewsLl = (LinearLayout) findViewById(R.id.news_ll);
        newsrv.setLayoutManager(new LinearLayoutManager(this) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
    }

    @Override
    protected void setListener() {
        View headerLayout = navigationView.getHeaderView(0);
        TextView nameTv= (TextView) headerLayout.findViewById(R.id.nick_tv);
        TextView telTv=(TextView)headerLayout.findViewById(R.id.tel_tv);
        Manager m= MyPrefence.getInstance(this).getUser();
        nameTv.setText(m.getName());
        telTv.setText(m.getPhone());


        statisticll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showStatisticDialog();
            }
        });

        borrowll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main2Activity.this, CaptureActivity.class);
                startActivityForResult(intent, REQUEST_LEND);
            }
        });

        returnll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main2Activity.this, CaptureActivity.class);
                startActivityForResult(intent, REQUEST_RETURN);
            }
        });

        mAddBookLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main2Activity.this, CaptureActivity.class);
                startActivityForResult(intent, REQUEST_ADD);
            }
        });
    }

    @Override
    protected void initData() {
        mImages.put("借阅伴侣上线啦",R.drawable.t1);
        mImages.put("新用户借书有优惠",R.drawable.t2);
        mImages.put("热门图书推荐",R.drawable.t3);
        mImages.put("新书上架",R.drawable.t4);

        RequestHelper.getNews(this, new RequestListener() {
            @Override
            public void onResponse(String res) {
                Log.d(TAG, "onResponse: "+res);
                List<News> news=new Gson().fromJson(res, new TypeToken<List<News>>(){}.getType());
                newsrv.setAdapter(new SolidRVBaseAdapter<News>(Main2Activity.this, news) {
                    @Override
                    protected void onBindDataToView(SolidCommonViewHolder holder, News bean, int position) {
                        holder.setText(R.id.title_tv,bean.getTitle());
//                        holder.setText(R.id.content_tv,bean.getContent().trim());
                        News.AdminEntity a=bean.getAdmin();
                        bean.setCreateTime(bean.getCreateTime().replace('T',' ').substring(0,19));
                        holder.setText(R.id.author_tv,(a==null?"":a.getName()+" • ")+
                                bean.getCreateTime());
                    }

                    @Override
                    public int getItemLayoutID(int viewType) {
                        return R.layout.item_news;
                    }

                    @Override
                    protected void onItemClick(int position, News bean) {
                        super.onItemClick(position, bean);
                        NewsActivity.start(Main2Activity.this, bean);
                    }
                });
                mNewsLl.setVisibility(View.VISIBLE);
            }

            @Override
            public void onError() {
                showErrorToast(R.string.error);
            }
        });
    }

    @Override
    protected void setData() {
        for(String name : mImages.keySet()){
            TextSliderView textSliderView = new TextSliderView(this);
            // initialize a SliderLayout
            textSliderView
                    .description(name)
                    .image(mImages.get(name))
                    .setScaleType(BaseSliderView.ScaleType.CenterCrop)
                    .setOnSliderClickListener(this);

            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra",name);

            mSliderLayout.addSlider(textSliderView);
        }
        mSliderLayout.setPresetTransformer(SliderLayout.Transformer.Default);
        mSliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mSliderLayout.setCustomAnimation(new DescriptionAnimation());
        mSliderLayout.setDuration(4000);
    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {

        }else if (id==R.id.nav_contact){
            ContactActivity.start(this);
        }else if (id== R.id.nav_check_new) {
            CheckActivity.start(this);
        }else if (id== R.id.nav_setting) {
            SettingActivity.start(this);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onStart() {
        mSliderLayout.startAutoCycle();
        super.onStart();
    }

    @Override
    public void onStop() {
        mSliderLayout.stopAutoCycle();
        super.onStop();
    }

    //点击轮播
    @Override
    public void onSliderClick(BaseSliderView slider) {

    }

    @Override
    public void onActivityResult(final int requestCode, final int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==REQUEST_RETURN||requestCode==REQUEST_LEND) {
            if (resultCode== Activity.RESULT_OK) {
                Bundle bundle = data.getExtras();
                String scanResult = bundle.getString("result");
                Log.d(TAG, "onActivityResult: "+scanResult);
                try {
                    final JSONObject obj=new JSONObject(scanResult);
                    RequestHelper.getOrder(this, obj.getString("OrderID"), new RequestListener() {
                        @Override
                        public void onResponse(String res) {
                            Log.d(TAG, "onResponse: "+res);
                            try {
                                JSONObject object=new JSONObject(res);
                                Gson gson=new Gson();
                                Log.d(TAG, "onResponse: "+object.getString("User")+"\n"+object.getJSONArray("OrderBooks"));
                                User user= gson.fromJson(object.getString("User"),User.class);
                                List<OrderBooks> orderBooksList=new ArrayList<OrderBooks>();

                                JSONArray jsonArray=object.getJSONArray("OrderBooks");
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jo=jsonArray.getJSONObject(i);
                                    Book book=gson.fromJson(jo.getString("Book"),Book.class);
                                    OrderBooks orderBooks=new OrderBooks();
                                    orderBooks.setBook(book);
                                    orderBooks.setBarCode(jo.getString("BarCode"));
                                    orderBooksList.add(orderBooks);
                                }
                                BookActivity.start(Main2Activity.this, user,orderBooksList, obj.getString("OrderID"),requestCode);
                                Log.d(TAG, "onResponse: "+user.getName()+"  "+orderBooksList.size());

                            } catch (JSONException e) {
                                e.printStackTrace();
                                onError();
                            }
                        }

                        @Override
                        public void onError() {

                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }else if (requestCode==REQUEST_ADD) {
            if (resultCode== Activity.RESULT_OK) {
                Bundle bundle = data.getExtras();
                String scanResult = bundle.getString("result");
                Log.d(TAG, "onActivityResult: " + scanResult);
                showAddBookDialog(scanResult);
            }
        }
    }

    private void showStatisticDialog(){
        RequestHelper.getStatistic(this, new RequestListener() {
            @Override
            public void onResponse(String res) {
                Log.d(TAG, "onResponse: "+res);
                Statistic s=new Gson().fromJson(res, Statistic.class);
                View v=getLayoutInflater().inflate(R.layout.dialog_statistic,null);
                TextView todayRegisterCount = (TextView) v.findViewById(R.id.todayRegisterCount);
                TextView userTotalCount = (TextView) v.findViewById(R.id.userTotalCount);
                TextView todayReserveCount = (TextView) v.findViewById(R.id.todayReserveCount);
                TextView todayBorrowCount = (TextView) v.findViewById(R.id.todayBorrowCount);
                TextView borrowedCount = (TextView) v.findViewById(R.id.borrowedCount);
                TextView canBorrowCount = (TextView) v.findViewById(R.id.canBorrowCount);
                todayRegisterCount.setText(s.getTodayRegisterCount()+"");
                userTotalCount.setText(s.getUserTotalCount()+"");
                todayReserveCount.setText(s.getTodayReserveCount()+"");
                todayBorrowCount.setText(s.getTodayBorrowCount()+"");
                borrowedCount.setText(s.getBorrowedCount()+"");
                canBorrowCount.setText(s.getCanBorrowCount()+"");

                new AlertDialog.Builder(Main2Activity.this)
                        .setTitle(R.string.statistic)
                        .setView(v)
                        .setPositiveButton(android.R.string.ok,null)
                        .create()
                        .show();
            }

            @Override
            public void onError() {
                showErrorToast(R.string.error);
            }
        });

    }

    private void showAddBookDialog(final String isbn){

        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                try {
                    subscriber.onNext(MyOkHttp.getInstance().run("https://api.douban.com/v2/book/isbn/"+isbn));
                    subscriber.onCompleted();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        })
                .subscribeOn(Schedulers.io()) // 指定 subscribe() 发生在 IO 线程
                .observeOn(AndroidSchedulers.mainThread()) // 指定 Subscriber 的回调发生在主线程
                .subscribe(new Observer<String>() {
                    @Override
                    public void onNext(String res) {
                        Book2 book2=new Gson().fromJson(res,Book2.class);
                        AddBookActivity.start(Main2Activity.this, book2.book2ToDouban());
                    }

                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        showErrorToast(R.string.error);
                    }
                });

    }
}
