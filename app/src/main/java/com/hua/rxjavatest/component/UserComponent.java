package com.hua.rxjavatest.component;

import com.hua.rxjavatest.MainActivity;
import com.hua.rxjavatest.PerApp;
import com.hua.rxjavatest.module.UserModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Administrator on 2016/12/31.
 * component for dagger
 */
@PerApp
@Singleton
@Component(modules = UserModule.class,dependencies = BookComponent.class)
public interface UserComponent {
    void inject(MainActivity mainActivity);
}
