package com.hua.rxjavatest.component;

import com.hua.rxjavatest.BookData;
import com.hua.rxjavatest.module.BookModule;
import com.hua.rxjavatest.module.UserModule;

import dagger.Component;

/**
 * Created by Administrator on 2016/12/31.
 * component for dagger
 */

@Component(modules = {BookModule.class})
public interface BookComponent {
    BookData BookModule();
}
