package com.lvqingyang.librarymanager.Reptile;

import android.util.Log;

import com.lvqingyang.librarymanager.Reptile.bean.BookId;
import com.lvqingyang.librarymanager.tool.MyOkHttp;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
 * Date：2017/7/8
 * Email：biloba12345@gamil.com
 * Info：
 */

public class BookReptile {
    private static final String URL_TAG_LIST = "https://book_.douban.com/tag/?view=cloud";
    private static final String TAG = "BookReptile";
    public static final int SLEEP_TIME = 1500;

    public static void getTagList(final ReptileListener<Tag> listener){
        Observable.create(new Observable.OnSubscribe<List<Tag>>() {
            @Override
            public void call(Subscriber<? super List<Tag>> subscriber) {
                try {
                    List<Tag> tagList=new ArrayList<Tag>();


                    String response=MyOkHttp.getInstance().run(URL_TAG_LIST);
                    Document doc = Jsoup.parse(response);
                    Log.d(TAG, "call: "+response);
                    Elements tagEles=doc.body().select("table.tagCol").first().select("td");
                    for (Element tagEle : tagEles) {
                        //<td><a href="/tag/小说">小说</a><b>(4622465)</b></td>
                        Tag t=new Tag();
                        t.setTag(tagEle.select("a").first().text());
                        t.setUrl("https://book_.douban.com/"+tagEle.select("a").attr("href"));
                       tagList.add(t);
                    }

                    Thread.sleep(SLEEP_TIME);
                    subscriber.onNext(tagList);
                    subscriber.onCompleted();
                } catch (Exception e) {
                    subscriber.onError(e);
                }
            }
        })
                .subscribeOn(Schedulers.io()) // 指定 subscribe() 发生在 IO 线程
                .observeOn(AndroidSchedulers.mainThread()) // 指定 Subscriber 的回调发生在主线程
                .subscribe(new Observer<List<Tag>>() {
                    @Override
                    public void onNext(List<Tag> tagList) {
                        listener.onResult(tagList);
                    }

                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        listener.onError(e);
                    }
                });
    }


    public static void getBookList(final List<Tag> tags, final ReptileListener<String> listener){
        Observable.create(new Observable.OnSubscribe<List<String>>() {
            @Override
            public void call(Subscriber<? super List<String>> subscriber) {
                try {
                    List<String> idList=new ArrayList<String>();

                    for (Tag tag : tags) {
                        Log.d(TAG, "call: "+tag.getTag());
                        String bookListUrl=tag.getUrl()+"?start=0&type=T";
                        Document doc = Jsoup.connect(bookListUrl).timeout(20000).get();
                        Elements bookEles=doc.body().select("ul.subject-list").first().select("li.subject-item");
                        for (Element bookEle : bookEles) {
                            String url=bookEle.select("a.nbg").first().attr("href");
                            String id=url.replace("https://book_.douban.com/subject/","").replace("/","");
                            Log.d(TAG, "call: "+id);
                            idList.add(id);

                            BookId bookId=new BookId(id);
                            bookId.save();
                        }
                        Thread.sleep(SLEEP_TIME);
                    }


                    subscriber.onNext(idList);
                    subscriber.onCompleted();
                } catch (Exception e) {
                    subscriber.onError(e);
                }
            }
        })
                .subscribeOn(Schedulers.io()) // 指定 subscribe() 发生在 IO 线程
                .observeOn(AndroidSchedulers.mainThread()) // 指定 Subscriber 的回调发生在主线程
                .subscribe(new Observer<List<String>>() {
                    @Override
                    public void onNext(List<String> bookList) {
                        listener.onResult(bookList);
                    }

                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        listener.onError(e);
                    }
                });
    }

    public static DouBanBook parseBook(String url) throws IOException, InterruptedException {
        DouBanBook book=new DouBanBook();

        Document doc = Jsoup.parse(MyOkHttp.getInstance().run(url));
        Element body=doc.body();
        Element imgAndTitleEle=body.select("a.nbg").first().select("img").first();
        book.setImg(imgAndTitleEle.attr("src"));
        book.setName(imgAndTitleEle.attr("alt"));
        book.setAuthor(body.select("span.pl").first().select("a").text());
        Element infoEle=body.select("div#info").first();
        book.setAuthor(infoEle.select("a[href]").first().text());

        List<String> infos=new ArrayList<>();
        String[] a1=infoEle.toString().split("<span class=\"pl\">");
        for (String s : a1) {
            String[] a2=s.split("</span>");
            for (String s2 : a2) {
                infos.add(s2.replace("<br>","").trim());
            }
        }
        infos.add(0,"");
        book.setPublisher(infos.get(infos.indexOf("出版社:")+1));
        book.setISBN(infos.get(infos.indexOf("ISBN:")+1));
        book.setPages(infos.get(infos.indexOf("页数:")+1));
        book.setPrice(infos.get(infos.indexOf("定价:")+1));
        book.setPubdate(infos.get(infos.indexOf("出版年:")+1));

        book.setScore(body.select("strong").first().text());
//        book_.setSummary(body.select("div.indent#link-report").first().select("div.intro").last().text());
//        book_.setCatalog(body.select("div#dir_1008145_short").first().text());

        Thread.sleep(10000);
        return book;
    }



}
