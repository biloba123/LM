/**
  * Copyright 2017 bejson.com 
  */
package com.pojo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Auto-generated: 2017-06-17 22:7:17
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Book implements Parcelable {

    private String ISBN;
    private String Name;
    private String Author;
    private String Img;
    private String Publisher;
    private String Pubdate;
    private String CLC;
    private String Pages;
    private double Price;
    private String Subject;
    private String Summary;
    private String Catalog;
    private int BorrowTimes;
    private int BrowserTimes;
    private int LikeTimes;
    private int CommentTimes;
    public void setISBN(String ISBN) {
         this.ISBN = ISBN;
     }
     public String getISBN() {
         return ISBN;
     }

    public void setName(String Name) {
         this.Name = Name;
     }
     public String getName() {
         return Name;
     }

    public void setAuthor(String Author) {
         this.Author = Author;
     }
     public String getAuthor() {
         return Author;
     }

    public void setImg(String Img) {
         this.Img = Img;
     }
     public String getImg() {
         return Img;
     }

    public void setPublisher(String Publisher) {
         this.Publisher = Publisher;
     }
     public String getPublisher() {
         return Publisher;
     }

    public String getPubdate() {
        return Pubdate;
    }

    public void setPubdate(String pubdate) {
        Pubdate = pubdate;
    }

    public void setCLC(String CLC) {
         this.CLC = CLC;
     }
     public String getCLC() {
         return CLC;
     }

    public void setPages(String Pages) {
         this.Pages = Pages;
     }
     public String getPages() {
         return Pages;
     }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public void setSubject(String Subject) {
         this.Subject = Subject;
     }
     public String getSubject() {
         return Subject;
     }

    public void setSummary(String Summary) {
         this.Summary = Summary;
     }
     public String getSummary() {
         return Summary;
     }

    public void setCatalog(String Catalog) {
         this.Catalog = Catalog;
     }
     public String getCatalog() {
         return Catalog;
     }

    public void setBorrowTimes(int BorrowTimes) {
         this.BorrowTimes = BorrowTimes;
     }
     public int getBorrowTimes() {
         return BorrowTimes;
     }

    public void setBrowserTimes(int BrowserTimes) {
         this.BrowserTimes = BrowserTimes;
     }
     public int getBrowserTimes() {
         return BrowserTimes;
     }

    public void setLikeTimes(int LikeTimes) {
         this.LikeTimes = LikeTimes;
     }
     public int getLikeTimes() {
         return LikeTimes;
     }

    public void setCommentTimes(int CommentTimes) {
         this.CommentTimes = CommentTimes;
     }
     public int getCommentTimes() {
         return CommentTimes;
     }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.ISBN);
        dest.writeString(this.Name);
        dest.writeString(this.Author);
        dest.writeString(this.Img);
        dest.writeString(this.Publisher);
        dest.writeString(this.Pubdate);
        dest.writeString(this.CLC);
        dest.writeString(this.Pages);
        dest.writeDouble(this.Price);
        dest.writeString(this.Subject);
        dest.writeString(this.Summary);
        dest.writeString(this.Catalog);
        dest.writeInt(this.BorrowTimes);
        dest.writeInt(this.BrowserTimes);
        dest.writeInt(this.LikeTimes);
        dest.writeInt(this.CommentTimes);
    }

    public Book() {
    }

    protected Book(Parcel in) {
        this.ISBN = in.readString();
        this.Name = in.readString();
        this.Author = in.readString();
        this.Img = in.readString();
        this.Publisher = in.readString();
        this.Pubdate = in.readString();
        this.CLC = in.readString();
        this.Pages = in.readString();
        this.Price = in.readDouble();
        this.Subject = in.readString();
        this.Summary = in.readString();
        this.Catalog = in.readString();
        this.BorrowTimes = in.readInt();
        this.BrowserTimes = in.readInt();
        this.LikeTimes = in.readInt();
        this.CommentTimes = in.readInt();
    }

    public static final Parcelable.Creator<Book> CREATOR = new Parcelable.Creator<Book>() {
        public Book createFromParcel(Parcel source) {
            return new Book(source);
        }

        public Book[] newArray(int size) {
            return new Book[size];
        }
    };
}