package com.lvqingyang.librarymanager.net;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lvqingyang.librarymanager.R;
import com.lvqingyang.librarymanager.Reptile.DouBanBook;
import com.lvqingyang.librarymanager.Reptile.bean.SQLBook;
import com.lvqingyang.librarymanager.tool.Md5Util;
import com.lvqingyang.librarymanager.tool.MyOkHttp;
import com.lvqingyang.librarymanager.tool.NetWorkUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import es.dmoral.toasty.Toasty;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

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
 * Date：2017/6/12
 * Email：biloba12345@gamil.com
 * Info：
 */

public class RequestHelper {
    private static final String key="wust_wechet_library";
    private static final String API_LOGIN = "http://zeblog.cn/api/Android/Login";
    private static final String API_REGISTER = "http://zeblog.cn/api/Android/Register";
    private static final String API_GET_ORDER= "http://zeblog.cn/api/Android/GetOrder";
    private static final String API_CONFIRM_BORROW = "http://zeblog.cn/api/Android/ConfirmBorrow";
    private static final String API_CONFIRM_RESERVER= "http://zeblog.cn/api/Android/ConfirmReserve";
    private static final String API_ACCEPT_REGISTER = "http://zeblog.cn/api/Android/AcceptRegister";
    private static final String API_REFUSE_REGISTER = "http://zeblog.cn/api/Android/RefuseRegister";
    private static final String API_GET_MANAGERS = "http://zeblog.cn/api/Android/GetAllManager";
    private static final String API_GET_NEW_REGISTER = "http://zeblog.cn/api/Android/GetAllNewRegister";
    private static final String API_RESET_PWD = "http://zeblog.cn/api/Android/ChangePassword";
    private static final String API_GET_NEWS="http://zeblog.cn/api/Android/GetLibraryNews";
    private static final String API_GET_STATISTIC="http://zeblog.cn/api/Android/GetMainInfo";
    private static final String API_ADD_DOU_BAN="http://zeblog.cn/api/Android/AddDouBanBook";
    private static final String TAG = "RequestHelper";

    private static void getResult(Context c,StringBuilder sb, final RequestListener listener){
        if (NetWorkUtils.isNetworkConnected(c)) {
            //相关参数
            Date date=new Date();
            DateFormat format=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String time=format.format(date);
            String chkvalue=key+time;
            chkvalue= Md5Util.MD5(chkvalue);
            chkvalue=chkvalue.substring(3).toLowerCase();

            final String url=sb.append("time="+time).append("&checkvalue="+chkvalue).toString();
            Log.d(TAG, "getResult: "+url);
            Observable.create(new Observable.OnSubscribe<String>() {
                @Override
                public void call(Subscriber<? super String> subscriber) {
                    try {
                        subscriber.onNext(MyOkHttp.getInstance()
                                .run(url));
                        subscriber.onCompleted();
                    } catch (Exception e) {
                        subscriber.onError(e);
                    }
                }
            })
                    .subscribeOn(Schedulers.io()) // 指定 subscribe() 发生在 IO 线程
                    .observeOn(AndroidSchedulers.mainThread()) // 指定 Subscriber 的回调发生在主线程
                    .subscribe(new Observer<String>() {
                        @Override
                        public void onNext(String response) {
                            listener.onResponse(response);
                        }

                        @Override
                        public void onCompleted() {
                        }

                        @Override
                        public void onError(Throwable e) {
                            e.printStackTrace();
                            listener.onError();
                        }
                    });
        }else {
            Toasty.warning(c, c.getString(R.string.check), Toast.LENGTH_SHORT).show();
        }
    }


    public static void addDouBanBook(Context c, final List<SQLBook> books, final RequestListener listener){
        if (NetWorkUtils.isNetworkConnected(c)) {
            Observable.create(new Observable.OnSubscribe<String>() {
                @Override
                public void call(Subscriber<? super String> subscriber) {
                    try {
                        for (int i = books.size() - 1; i >= 0; i--) {
                            subscriber.onNext(MyOkHttp.getInstance().post(API_ADD_DOU_BAN,books.get(i).getJson()));
                        }
                        subscriber.onCompleted();
                    } catch (Exception e) {
                        subscriber.onError(e);
                    }
                }
            })
                    .subscribeOn(Schedulers.io()) // 指定 subscribe() 发生在 IO 线程
                    .observeOn(AndroidSchedulers.mainThread()) // 指定 Subscriber 的回调发生在主线程
                    .subscribe(new Observer<String>() {
                        @Override
                        public void onNext(String response) {
                            listener.onResponse(response);
                        }

                        @Override
                        public void onCompleted() {
                        }

                        @Override
                        public void onError(Throwable e) {
                            e.printStackTrace();
                            listener.onError();
                        }
                    });
        }else {
            Toasty.warning(c, c.getString(R.string.check), Toast.LENGTH_SHORT).show();
        }
    }

    public static void addDouBanBook(Context c, final DouBanBook book, final RequestListener listener){
        if (NetWorkUtils.isNetworkConnected(c)) {
            Observable.create(new Observable.OnSubscribe<String>() {
                @Override
                public void call(Subscriber<? super String> subscriber) {
                    try {
                        subscriber.onNext(MyOkHttp.getInstance().post(API_ADD_DOU_BAN,new Gson().toJson(book)));
                        subscriber.onCompleted();
                    } catch (Exception e) {
                        subscriber.onError(e);
                    }
                }
            })
                    .subscribeOn(Schedulers.io()) // 指定 subscribe() 发生在 IO 线程
                    .observeOn(AndroidSchedulers.mainThread()) // 指定 Subscriber 的回调发生在主线程
                    .subscribe(new Observer<String>() {
                        @Override
                        public void onNext(String response) {
                            listener.onResponse(response);
                        }

                        @Override
                        public void onCompleted() {
                        }

                        @Override
                        public void onError(Throwable e) {
                            e.printStackTrace();
                            listener.onError();
                        }
                    });
        }else {
            Toasty.warning(c, c.getString(R.string.check), Toast.LENGTH_SHORT).show();
        }
    }


    public static void login(Context c,String  id,String  password,RequestListener listener){
        StringBuilder sb=new StringBuilder(API_LOGIN)
                .append("?id="+id)
                .append("&password="+password+"&");
        getResult(c,sb,listener );
    }

    public static void register(Context c,String  name,String  password,String  phone,RequestListener listener){
        StringBuilder sb=new StringBuilder(API_REGISTER)
                .append("?name="+name)
                .append("&password="+password)
                .append("&phone="+phone+"&");
        getResult(c,sb,listener );
    }


    public static void getOrder(Context c,String orderId,RequestListener listener){
        StringBuilder sb=new StringBuilder(API_GET_ORDER)
                .append("?OrderID="+orderId+"&");
        getResult(c,sb,listener );
    }

    public static void confirmBorrow(Context c,String OpenID,String OrderID,RequestListener listener){
        StringBuilder sb=new StringBuilder(API_CONFIRM_BORROW)
                .append("?OpenID="+OpenID+"&")
                .append("OrderID="+OrderID+"&");
        getResult(c,sb,listener );
    }

    public static void confirmReturn(Context c,String OpenID,String OrderID,RequestListener listener){
        StringBuilder sb=new StringBuilder(API_CONFIRM_RESERVER)
                .append("?OpenID="+OpenID+"&")
                .append("OrderID="+OrderID+"&");
        getResult(c,sb,listener );
    }


    public static void acceptRegister(Context c,String id,RequestListener listener){
        StringBuilder sb=new StringBuilder(API_ACCEPT_REGISTER)
                .append("?id="+id+"&");
        getResult(c,sb,listener );
    }

    public static void refuseRegister(Context c,String id,RequestListener listener){
        StringBuilder sb=new StringBuilder(API_REFUSE_REGISTER)
                .append("?id="+id+"&");
        getResult(c,sb,listener );
    }

    public static void getAllManagers(Context c,RequestListener listener){
        StringBuilder sb=new StringBuilder(API_GET_MANAGERS+"?");
        getResult(c,sb,listener );
    }

    public static void getNews(Context c,RequestListener listener){
        StringBuilder sb=new StringBuilder(API_GET_NEWS+"?");
        getResult(c,sb,listener );
    }

    public static void getStatistic(Context c,RequestListener listener){
        StringBuilder sb=new StringBuilder(API_GET_STATISTIC+"?");
        getResult(c,sb,listener );
    }

    public static void getNewRegister(Context c,RequestListener listener){
        StringBuilder sb=new StringBuilder(API_GET_NEW_REGISTER+"?");
        getResult(c,sb,listener );
    }

    public static void changePassword(Context c,String id,String old_password,String new_password,RequestListener listener){
        StringBuilder sb=new StringBuilder(API_RESET_PWD)
                .append("?id="+id)
                .append("&old_password="+old_password)
                .append("&new_password="+new_password+"&");
        getResult(c,sb,listener );
    }

}
