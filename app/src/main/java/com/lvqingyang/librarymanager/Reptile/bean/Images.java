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
public class Images implements Parcelable {


    private String small;
    private String large;
    private String medium;
    public void setSmall(String small) {
         this.small = small;
     }
     public String getSmall() {
         return small;
     }

    public void setLarge(String large) {
         this.large = large;
     }
     public String getLarge() {
         return large;
     }

    public void setMedium(String medium) {
         this.medium = medium;
     }
     public String getMedium() {
         return medium;
     }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.small);
        dest.writeString(this.large);
        dest.writeString(this.medium);
    }

    public Images() {
    }

    protected Images(Parcel in) {
        this.small = in.readString();
        this.large = in.readString();
        this.medium = in.readString();
    }

    public static final Parcelable.Creator<Images> CREATOR = new Parcelable.Creator<Images>() {
        @Override
        public Images createFromParcel(Parcel source) {
            return new Images(source);
        }

        @Override
        public Images[] newArray(int size) {
            return new Images[size];
        }
    };
}