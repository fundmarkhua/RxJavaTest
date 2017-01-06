package com.hua.rxjavatest.mvp.module;

import com.hua.rxjavatest.PerApp;
import com.hua.rxjavatest.mvp.model.MvpModel;
import com.hua.rxjavatest.mvp.presenter.MvpPresenter;
import com.hua.rxjavatest.mvp.ui.MvpActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/1/5.
 * dagger module
 */
@PerApp
@Module
public class MvpPresenterModule {
    private MvpActivity activity;

    public MvpPresenterModule(MvpActivity activity){
        this.activity = activity;
    }

    @Provides
    MvpActivity  provideActivity(){
        return  activity;
    }

    @Provides
    MvpPresenter  providePresenter(MvpActivity ui, MvpModel  mvpModel){
        return new MvpPresenter(ui,mvpModel);
    }

    @Provides
    MvpModel provideMvpModel(){
        return new MvpModel();
    }
}
