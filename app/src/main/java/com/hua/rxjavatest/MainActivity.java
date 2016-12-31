package com.hua.rxjavatest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.hua.rxjavatest.component.BookComponent;
import com.hua.rxjavatest.component.DaggerBookComponent;
import com.hua.rxjavatest.component.DaggerUserComponent;
import com.hua.rxjavatest.component.UserComponent;
import com.hua.rxjavatest.module.BookModule;
import com.hua.rxjavatest.module.UserModule;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.*;
import rx.schedulers.Schedulers;

import static com.hua.rxjavatest.R.id.button_get;


public class MainActivity extends AppCompatActivity {
    private static final String BASE_URL = "http://apis.baidu.com";
    private static final String API_KEY = "0f2dbbc74c88007d71cca043035d5eba";
    private static final String Phone_number = "18211551346";
    private static final String tag = "RxJava";

    @Inject
    UserData userData;
    @Inject
    BookData bookData;

    @BindView(R.id.button_get)
    Button buttonGet;
    @BindView(R.id.editText_phone)
    EditText textPhone;
    @BindView(R.id.textView_state)
    TextView viewState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        BookComponent bookComponent = DaggerBookComponent.builder().bookModule(new BookModule("浪不得行")).build();
        UserComponent userComponent = DaggerUserComponent.builder().userModule(new UserModule("小胖妞","8岁")).bookComponent(bookComponent).build();
        userComponent.inject(this);
        Log.w(tag, userData.getName() + " " + userData.getAge());
        Log.w(tag, bookData.getBook_name() );

    }

    @OnClick(R.id.button_get)
    public void ClickMethod() {
        String phone_number = textPhone.getText().toString();
        Log.w(tag, "phone_number is " + phone_number);
        if (!TextUtils.isEmpty(phone_number)) {
            queryRxForApi(phone_number);
        }

    }

    private void queryRxForApi(String phone_number) {
        PhoneService service = PhoneApi.getApi().getService();
        service.getPhoneResult(PhoneApi.API_KEY, phone_number)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<PhoneResult>() {
                    @Override
                    public void onCompleted() {
                        Log.w(tag, "加载完毕");
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(PhoneResult phoneResult) {
                        if (phoneResult != null && phoneResult.getErrNum() == 0) {
                            PhoneResult.RetDataEntity entity = phoneResult.getRetData();
                            viewState.setText("手机号码归属地是" + entity.getCity());
                        }
                    }
                });
    }

    private void queryRx() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        PhoneService service = retrofit.create(PhoneService.class);
        service.getPhoneResult(API_KEY, Phone_number)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PhoneResult>() {
                    @Override
                    public void onCompleted() {
                        Log.w(tag, "加载完毕");
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(PhoneResult phoneResult) {
                        if (phoneResult != null && phoneResult.getErrNum() == 0) {
                            PhoneResult.RetDataEntity entity = phoneResult.getRetData();
                            Log.w(tag, "地址是" + entity.getCity());
                        }
                    }
                });
    }

    private void query() {
        //1 创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build();
        //2 创建访问API的请求
        PhoneService service = retrofit.create(PhoneService.class);
        Call<PhoneResult> call = service.getResult(API_KEY, Phone_number);

        //3 发送请求
        call.enqueue(new Callback<PhoneResult>() {
            @Override
            public void onResponse(Call<PhoneResult> call, Response<PhoneResult> response) {
                Log.w(tag, "321");
                //4 处理结果
                if (response.isSuccessful()) {
                    PhoneResult result = response.body();
                    if (result != null) {
                        PhoneResult.RetDataEntity entity = result.getRetData();
                        Log.w(tag, entity.getCity());
                    }
                }
            }

            @Override
            public void onFailure(Call<PhoneResult> call, Throwable t) {

            }
        });

    }
}



