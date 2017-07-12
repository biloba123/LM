package com.lvqingyang.librarymanager.Reptile;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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

public class DouBanBook implements Serializable{
    private List<String> Tags=new ArrayList<>();
    public String ISBN ;
    public String Name ;
    public String Author ;
    public String Publisher ;
    public String Pubdate ;
    public String Pages ;
    public String CLC ;//中图法分类号
    public String Price ;
    public String Subject ;//主题词
    public String Img ;//图片地址
    public String Summary ;//简介
    public String Catalog ;//目录
    public String Score ;//评分
    public String BorrowTimes ;
    public String BrowseTimes ;
    public String LikeTimes ;
    public String CommentTimes ;

    public List<String> getTags() {
        return Tags;
    }

    public void setTags(List<String> tags) {
        Tags = tags;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public String getPublisher() {
        return Publisher;
    }

    public void setPublisher(String publisher) {
        Publisher = publisher;
    }

    public String getPubdate() {
        return Pubdate;
    }

    public void setPubdate(String pubdate) {
        Pubdate = pubdate;
    }

    public String getPages() {
        return Pages;
    }

    public void setPages(String pages) {
        Pages = pages;
    }

    public String getCLC() {
        return CLC;
    }

    public void setCLC(String CLC) {
        this.CLC = CLC;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getSubject() {
        return Subject;
    }

    public void setSubject(String subject) {
        Subject = subject;
    }

    public String getImg() {
        return Img;
    }

    public void setImg(String img) {
        Img = img;
    }

    public String getSummary() {
        return Summary;
    }

    public void setSummary(String summary) {
        Summary = summary;
    }

    public String getCatalog() {
        return Catalog;
    }

    public void setCatalog(String catalog) {
        Catalog = catalog;
    }

    public String getScore() {
        return Score;
    }

    public void setScore(String score) {
        Score = score;
    }

    public String getBorrowTimes() {
        return BorrowTimes;
    }

    public void setBorrowTimes(String borrowTimes) {
        BorrowTimes = borrowTimes;
    }

    public String getBrowseTimes() {
        return BrowseTimes;
    }

    public void setBrowseTimes(String browseTimes) {
        BrowseTimes = browseTimes;
    }

    public String getLikeTimes() {
        return LikeTimes;
    }

    public void setLikeTimes(String likeTimes) {
        LikeTimes = likeTimes;
    }

    public String getCommentTimes() {
        return CommentTimes;
    }

    public void setCommentTimes(String commentTimes) {
        CommentTimes = commentTimes;
    }
}
