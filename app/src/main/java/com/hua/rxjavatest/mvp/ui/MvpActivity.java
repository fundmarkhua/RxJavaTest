package com.hua.rxjavatest.mvp.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.hua.rxjavatest.R;
import com.hua.rxjavatest.mvp.compoent.DaggerMvpPresenterComponent;
import com.hua.rxjavatest.mvp.compoent.MvpPresenterComponent;
import com.hua.rxjavatest.mvp.model.MvpModel;
import com.hua.rxjavatest.mvp.model.impl.MvpModelImpl;
import com.hua.rxjavatest.mvp.module.MvpPresenterModule;
import com.hua.rxjavatest.mvp.presenter.MvpPresenter;
import com.hua.rxjavatest.mvp.ui.impl.MvpUiImpl;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MvpActivity extends AppCompatActivity implements MvpUiImpl {
    @BindView(R.id.button_one)
    Button buttonGet;
    @BindView(R.id.textView_state)
    TextView textViewState;

    private static final String tag = "RxJava";
    @Inject
    MvpPresenter mvpPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvp);
        ButterKnife.bind(this);
        MvpPresenterComponent component = DaggerMvpPresenterComponent.builder().mvpPresenterModule(new MvpPresenterModule(this)).build();
        component.inject(this);
    }

    @OnClick(R.id.button_one)
    public void ClickMethod() {
        Log.w(tag, "3");
        try{
            mvpPresenter.testDos();
        }
        catch(Exception e){
            Log.w(tag, e.toString());
        }

        Log.w(tag, "4");
    }

    @Override
    public void showTest(String msg) {
        textViewState.setText(msg);
    }
}
