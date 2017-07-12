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
public class User implements Parcelable {

    private String OpenID;
    private String Password;
    private String Name;
    private String IdCard;
    private String Telephone;
    private String RecommendDate;
    private String SearchHistory;
    private String RegisterDate;
    public void setOpenID(String OpenID) {
         this.OpenID = OpenID;
     }
     public String getOpenID() {
         return OpenID;
     }

    public void setPassword(String Password) {
         this.Password = Password;
     }
     public String getPassword() {
         return Password;
     }

    public void setName(String Name) {
         this.Name = Name;
     }
     public String getName() {
         return Name;
     }

    public void setIdCard(String IdCard) {
         this.IdCard = IdCard;
     }
     public String getIdCard() {
         return IdCard;
     }

    public void setTelephone(String Telephone) {
         this.Telephone = Telephone;
     }
     public String getTelephone() {
         return Telephone;
     }

    public void setRecommendDate(String RecommendDate) {
         this.RecommendDate = RecommendDate;
     }
     public String getRecommendDate() {
         return RecommendDate;
     }

    public void setSearchHistory(String SearchHistory) {
         this.SearchHistory = SearchHistory;
     }
     public String getSearchHistory() {
         return SearchHistory;
     }

    public String getRegisterDate() {
        return RegisterDate;
    }

    public void setRegisterDate(String registerDate) {
        RegisterDate = registerDate;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.OpenID);
        dest.writeString(this.Password);
        dest.writeString(this.Name);
        dest.writeString(this.IdCard);
        dest.writeString(this.Telephone);
        dest.writeString(this.RecommendDate);
        dest.writeString(this.SearchHistory);
        dest.writeString(this.RegisterDate);
    }

    public User() {
    }

    protected User(Parcel in) {
        this.OpenID = in.readString();
        this.Password = in.readString();
        this.Name = in.readString();
        this.IdCard = in.readString();
        this.Telephone = in.readString();
        this.RecommendDate = in.readString();
        this.SearchHistory = in.readString();
        this.RegisterDate = in.readString();
    }

    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        public User[] newArray(int size) {
            return new User[size];
        }
    };
}