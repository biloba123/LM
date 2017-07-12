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
public class OrderBooks implements Parcelable {

    private Book Book;
    private String BarCode;
    public void setBook(Book Book) {
         this.Book = Book;
     }
     public Book getBook() {
         return Book;
     }

    public void setBarCode(String BarCode) {
         this.BarCode = BarCode;
     }
     public String getBarCode() {
         return BarCode;
     }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.Book, flags);
        dest.writeString(this.BarCode);
    }

    public OrderBooks() {
    }

    protected OrderBooks(Parcel in) {
        this.Book = in.readParcelable(com.pojo.Book.class.getClassLoader());
        this.BarCode = in.readString();
    }

    public static final Parcelable.Creator<OrderBooks> CREATOR = new Parcelable.Creator<OrderBooks>() {
        public OrderBooks createFromParcel(Parcel source) {
            return new OrderBooks(source);
        }

        public OrderBooks[] newArray(int size) {
            return new OrderBooks[size];
        }
    };
}