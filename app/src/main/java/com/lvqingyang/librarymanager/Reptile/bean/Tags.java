/**
  * Copyright 2017 bejson.com 
  */
package com.lvqingyang.librarymanager.Reptile.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Auto-generated: 2017-07-09 10:44:51
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Tags implements Parcelable {



    private int count;
    private String name;
    private String title;
    public void setCount(int count) {
         this.count = count;
     }
     public int getCount() {
         return count;
     }

    public void setName(String name) {
         this.name = name;
     }
     public String getName() {
         return name;
     }

    public void setTitle(String title) {
         this.title = title;
     }
     public String getTitle() {
         return title;
     }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.count);
        dest.writeString(this.name);
        dest.writeString(this.title);
    }

    public Tags() {
    }

    protected Tags(Parcel in) {
        this.count = in.readInt();
        this.name = in.readString();
        this.title = in.readString();
    }

    public static final Parcelable.Creator<Tags> CREATOR = new Parcelable.Creator<Tags>() {
        @Override
        public Tags createFromParcel(Parcel source) {
            return new Tags(source);
        }

        @Override
        public Tags[] newArray(int size) {
            return new Tags[size];
        }
    };
}