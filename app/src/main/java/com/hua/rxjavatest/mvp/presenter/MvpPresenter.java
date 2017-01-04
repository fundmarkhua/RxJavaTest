package com.hua.rxjavatest.mvp.presenter;

import com.hua.rxjavatest.mvp.bean.MvpEntity;
import com.hua.rxjavatest.mvp.model.MvpModel;
import com.hua.rxjavatest.mvp.ui.impl.MvpUiImpl;

/**
 * Created by Administrator on 2017/1/4.
 * MVP
 */

public class MvpPresenter {
    MvpUiImpl ui;
    MvpModel model;

    public MvpPresenter(MvpUiImpl ui, MvpModel model) {
        this.ui = ui;
        this.model = model;
    }

    public void testDos(){
        MvpEntity entity = model.test();
        ui.showTest(entity.getMsg());
    }
}
