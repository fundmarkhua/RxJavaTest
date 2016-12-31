package com.hua.rxjavatest.module;

import android.icu.text.UnicodeSet;

import com.hua.rxjavatest.BookData;
import com.hua.rxjavatest.UserData;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2016/12/31.
 * module for dagger2
 */
@Module
public class UserModule {
    private String userName;
    private String userAge;
    public UserModule(String userName,String userAge){
        this.userName = userName;
        this.userAge = userAge;
    }

    @Provides
    UserData provideUser() {
        UserData userData = new UserData();
        userData.setName(userName);
        userData.setAge(userAge);
        return userData;
    }
}
