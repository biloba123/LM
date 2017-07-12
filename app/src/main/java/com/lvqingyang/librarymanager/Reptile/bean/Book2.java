/**
  * Copyright 2017 bejson.com 
  */
package com.lvqingyang.librarymanager.Reptile.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.lvqingyang.librarymanager.Reptile.DouBanBook;

import java.util.ArrayList;
import java.util.List;

/**
 * Auto-generated: 2017-07-09 10:44:51
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Book2 implements Parcelable {



    private Rating rating;
    private String subtitle;
    private List<String> author;
    private String pubdate;
    private List<Tags> tags;
    private String origin_title;
    private String image;
    private String binding;
    private List<String> translator;
    private String catalog;
    private String pages;
    private Images images;
    private String alt;
    private String id;
    private String publisher;
    private String isbn10;
    private String isbn13;
    private String title;
    private String url;
    private String alt_title;
    private String author_intro;
    private String summary;
    private String price;
    public void setRating(Rating rating) {
         this.rating = rating;
     }
     public Rating getRating() {
         return rating;
     }


     public DouBanBook book2ToDouban(){
         DouBanBook douBanBook=new DouBanBook();
         douBanBook.setCatalog(catalog);
         douBanBook.setPubdate(pubdate);
         douBanBook.setPrice(price);
         if (rating != null) {
             douBanBook.setScore(rating.getAverage());
         }
         douBanBook.setPages(pages);
         StringBuilder sb=new StringBuilder();
         if (author != null) {
             for (String s : author) {
                 sb.append(s);
             }
             douBanBook.setAuthor(sb.toString());
         }
         if (images != null) {
             douBanBook.setImg(images.getLarge());
         }
         douBanBook.setISBN(isbn13);
         douBanBook.setName(title);
         douBanBook.setPublisher(publisher);
         douBanBook.setSummary(summary);
         if (tags != null) {
             List<String> tagList=new ArrayList<>();
             for (Tags tag : tags) {
                 tagList.add(tag.getTitle());
             }
             douBanBook.setTags(tagList);
         }


         return douBanBook;
     }

    public void setSubtitle(String subtitle) {
         this.subtitle = subtitle;
     }
     public String getSubtitle() {
         return subtitle;
     }

    public void setAuthor(List<String> author) {
         this.author = author;
     }
     public List<String> getAuthor() {
         return author;
     }

    public void setPubdate(String pubdate) {
         this.pubdate = pubdate;
     }
     public String getPubdate() {
         return pubdate;
     }

    public void setTags(List<Tags> tags) {
         this.tags = tags;
     }
     public List<Tags> getTags() {
         return tags;
     }

    public void setOrigin_title(String origin_title) {
         this.origin_title = origin_title;
     }
     public String getOrigin_title() {
         return origin_title;
     }

    public void setImage(String image) {
         this.image = image;
     }
     public String getImage() {
         return image;
     }

    public void setBinding(String binding) {
         this.binding = binding;
     }
     public String getBinding() {
         return binding;
     }

    public void setTranslator(List<String> translator) {
         this.translator = translator;
     }
     public List<String> getTranslator() {
         return translator;
     }

    public void setCatalog(String catalog) {
         this.catalog = catalog;
     }
     public String getCatalog() {
         return catalog;
     }

    public void setPages(String pages) {
         this.pages = pages;
     }
     public String getPages() {
         return pages;
     }

    public void setImages(Images images) {
         this.images = images;
     }
     public Images getImages() {
         return images;
     }

    public void setAlt(String alt) {
         this.alt = alt;
     }
     public String getAlt() {
         return alt;
     }

    public void setId(String id) {
         this.id = id;
     }
     public String getId() {
         return id;
     }

    public void setPublisher(String publisher) {
         this.publisher = publisher;
     }
     public String getPublisher() {
         return publisher;
     }

    public void setIsbn10(String isbn10) {
         this.isbn10 = isbn10;
     }
     public String getIsbn10() {
         return isbn10;
     }

    public void setIsbn13(String isbn13) {
         this.isbn13 = isbn13;
     }
     public String getIsbn13() {
         return isbn13;
     }

    public void setTitle(String title) {
         this.title = title;
     }
     public String getTitle() {
         return title;
     }

    public void setUrl(String url) {
         this.url = url;
     }
     public String getUrl() {
         return url;
     }

    public void setAlt_title(String alt_title) {
         this.alt_title = alt_title;
     }
     public String getAlt_title() {
         return alt_title;
     }

    public void setAuthor_intro(String author_intro) {
         this.author_intro = author_intro;
     }
     public String getAuthor_intro() {
         return author_intro;
     }

    public void setSummary(String summary) {
         this.summary = summary;
     }
     public String getSummary() {
         return summary;
     }

    public void setPrice(String price) {
         this.price = price;
     }
     public String getPrice() {
         return price;
     }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.rating, flags);
        dest.writeString(this.subtitle);
        dest.writeStringList(this.author);
        dest.writeString(this.pubdate);
        dest.writeList(this.tags);
        dest.writeString(this.origin_title);
        dest.writeString(this.image);
        dest.writeString(this.binding);
        dest.writeStringList(this.translator);
        dest.writeString(this.catalog);
        dest.writeString(this.pages);
        dest.writeParcelable(this.images, flags);
        dest.writeString(this.alt);
        dest.writeString(this.id);
        dest.writeString(this.publisher);
        dest.writeString(this.isbn10);
        dest.writeString(this.isbn13);
        dest.writeString(this.title);
        dest.writeString(this.url);
        dest.writeString(this.alt_title);
        dest.writeString(this.author_intro);
        dest.writeString(this.summary);
        dest.writeString(this.price);
    }

    public Book2() {
    }

    protected Book2(Parcel in) {
        this.rating = in.readParcelable(Rating.class.getClassLoader());
        this.subtitle = in.readString();
        this.author = in.createStringArrayList();
        this.pubdate = in.readString();
        this.tags = new ArrayList<Tags>();
        in.readList(this.tags, List.class.getClassLoader());
        this.origin_title = in.readString();
        this.image = in.readString();
        this.binding = in.readString();
        this.translator = in.createStringArrayList();
        this.catalog = in.readString();
        this.pages = in.readString();
        this.images = in.readParcelable(Images.class.getClassLoader());
        this.alt = in.readString();
        this.id = in.readString();
        this.publisher = in.readString();
        this.isbn10 = in.readString();
        this.isbn13 = in.readString();
        this.title = in.readString();
        this.url = in.readString();
        this.alt_title = in.readString();
        this.author_intro = in.readString();
        this.summary = in.readString();
        this.price = in.readString();
    }

    public static final Parcelable.Creator<Book2> CREATOR = new Parcelable.Creator<Book2>() {
        public Book2 createFromParcel(Parcel source) {
            return new Book2(source);
        }

        public Book2[] newArray(int size) {
            return new Book2[size];
        }
    };
}