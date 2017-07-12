package com.lvqingyang.librarymanager.Reptile;

import android.util.Log;

import com.google.gson.Gson;
import com.lvqingyang.librarymanager.Reptile.bean.Book2;
import com.lvqingyang.librarymanager.Reptile.bean.BookId;
import com.lvqingyang.librarymanager.Reptile.bean.SQLBook;
import com.lvqingyang.librarymanager.tool.MyOkHttp;

import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.bumptech.glide.gifdecoder.GifHeaderParser.TAG;

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

public class APIHelper {
    private static final String API_BOOK_INFO = "https://api.douban.com/v2/book_/";
    public static void getBookList(final List<BookId> ids, final ReptileListener<DouBanBook> listener){
        Observable.create(new Observable.OnSubscribe<List<DouBanBook>>() {
            @Override
            public void call(Subscriber<? super List<DouBanBook>> subscriber) {
                int i = 0;
                try {
                    List<DouBanBook> bookList=new ArrayList<DouBanBook>();
                    MyOkHttp okHttp=MyOkHttp.getInstance();
                    Gson gson=new Gson();

                    for (; i < ids.size(); i++) {
                        BookId id=ids.get(i);
                        Book2 b1=gson.fromJson(okHttp.run(API_BOOK_INFO+id.getBookId()),Book2.class);
                        DouBanBook db=b1.book2ToDouban();
                        Log.d(TAG, "call: 第" +i+"本\n"+gson.toJson(db));
                        bookList.add(db);

                        //save to sqlite
                        SQLBook book=new SQLBook(id.getBookId(),gson.toJson(db));
                        book.save();

                        Thread.sleep(BookReptile.SLEEP_TIME);
                    }

                    subscriber.onNext(bookList);
                    subscriber.onCompleted();
                } catch (Exception e) {
                    e.printStackTrace();
                    if (!(e instanceof SocketTimeoutException)) {
                        subscriber.onError(new Exception(i+""));
                    }else {
                        i--;
                    }
                }
            }
        })
                .subscribeOn(Schedulers.io()) // 指定 subscribe() 发生在 IO 线程
                .observeOn(AndroidSchedulers.mainThread()) // 指定 Subscriber 的回调发生在主线程
                .subscribe(new Observer<List<DouBanBook>>() {
                    @Override
                    public void onNext(List<DouBanBook> bookList) {
                        listener.onResult(bookList);
                    }

                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX\n"+e.getMessage());
                        listener.onError(e);
                    }
                });
    }
}
