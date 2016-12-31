package com.hua.rxjavatest.module;

import com.hua.rxjavatest.BookData;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2016/12/31.
 * module for dagger2
 */
@Module
public class BookModule {
    private String bookName;

    public BookModule(String bookNameIn) {
        this.bookName = bookNameIn;
    }
    @Provides
    BookData provideBook(){
        return new BookData(bookName);
    }

}
