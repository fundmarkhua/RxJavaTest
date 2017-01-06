package com.hua.rxjavatest.mvp.compoent;

import com.hua.rxjavatest.PerApp;
import com.hua.rxjavatest.mvp.module.MvpPresenterModule;
import com.hua.rxjavatest.mvp.ui.MvpActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Administrator on 2017/1/5.
 * dagger component
 */
@PerApp
@Singleton
@Component(modules = MvpPresenterModule.class)
public interface MvpPresenterComponent {
       void inject(MvpActivity  activity);
}
