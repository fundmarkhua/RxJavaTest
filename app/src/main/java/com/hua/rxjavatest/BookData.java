package com.hua.rxjavatest;

/**
 * Created by Administrator on 2016/12/31.
 * est for dagger2
 */

public class BookData {
    String book_name;

    public BookData(String book_name){
        this.book_name = book_name;
    }
    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }
}
