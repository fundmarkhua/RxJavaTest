package com.hua.rxjavatest.mvp.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.hua.rxjavatest.R;
import com.hua.rxjavatest.mvp.presenter.MvpPresenter;
import com.hua.rxjavatest.mvp.ui.impl.MvpUiImpl;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MvpActivity extends AppCompatActivity implements MvpUiImpl {
    @BindView(R.id.button_one)
    Button buttonGet;
    @BindView(R.id.textView_state)
    TextView textViewState;

    MvpPresenter mvpPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvp);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.button_one)
    public void ClickMethod() {
      mvpPresenter.testDos();
    }

    @Override
    public void showTest(String msg) {
        textViewState.setText(msg);
    }
}
