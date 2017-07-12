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
public class Rating implements Parcelable {



    private int max;
    private int numRaters;
    private String average;
    private int min;
    public void setMax(int max) {
         this.max = max;
     }
     public int getMax() {
         return max;
     }

    public void setNumRaters(int numRaters) {
         this.numRaters = numRaters;
     }
     public int getNumRaters() {
         return numRaters;
     }

    public void setAverage(String average) {
         this.average = average;
     }
     public String getAverage() {
         return average;
     }

    public void setMin(int min) {
         this.min = min;
     }
     public int getMin() {
         return min;
     }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.max);
        dest.writeInt(this.numRaters);
        dest.writeString(this.average);
        dest.writeInt(this.min);
    }

    public Rating() {
    }

    protected Rating(Parcel in) {
        this.max = in.readInt();
        this.numRaters = in.readInt();
        this.average = in.readString();
        this.min = in.readInt();
    }

    public static final Parcelable.Creator<Rating> CREATOR = new Parcelable.Creator<Rating>() {
        @Override
        public Rating createFromParcel(Parcel source) {
            return new Rating(source);
        }

        @Override
        public Rating[] newArray(int size) {
            return new Rating[size];
        }
    };
}