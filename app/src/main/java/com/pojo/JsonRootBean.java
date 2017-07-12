/**
  * Copyright 2017 bejson.com 
  */
package com.pojo;
import java.util.List;

/**
 * Auto-generated: 2017-06-17 22:7:17
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class JsonRootBean {

    private User User;
    private List<OrderBooks> OrderBooks;
    public void setUser(User User) {
         this.User = User;
     }
     public User getUser() {
         return User;
     }

    public void setOrderBooks(List<OrderBooks> OrderBooks) {
         this.OrderBooks = OrderBooks;
     }
     public List<OrderBooks> getOrderBooks() {
         return OrderBooks;
     }

}