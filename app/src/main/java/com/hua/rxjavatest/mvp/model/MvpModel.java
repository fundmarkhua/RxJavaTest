package com.hua.rxjavatest.mvp.model;

import com.hua.rxjavatest.mvp.bean.MvpEntity;
import com.hua.rxjavatest.mvp.model.impl.MvpModelImpl;

/**
 * Created by Administrator on 2017/1/4.
 *
 */

public class MvpModel implements MvpModelImpl {
    @Override
    public MvpEntity test(){
        return  new MvpEntity("数据");
    }
}
