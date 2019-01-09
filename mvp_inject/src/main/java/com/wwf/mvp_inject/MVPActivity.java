package com.wwf.mvp_inject;

import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.wwf.mvp_core.BasePresenter;
import com.wwf.mvp_core.BaseView;

/**
 * 让activity继承自此类，可使用注解{@link WFView}，声明对应的presenter，无需手动实例化。
 * Created by Administrator on 2018/11/19
 */
public class MVPActivity<D extends ViewDataBinding, P extends BasePresenter> extends AppCompatActivity implements BaseView {
    protected D mDataBinding;
    protected P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);

       init();
    }

    private void init() {
        WFInjectFactory factory = new WFInjectFactory(this);
        mDataBinding = factory.createDataBinding();
        mPresenter = (P)factory.createPresenter();
    }

    @Override
    public void toast(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgress(String text) {

    }

    @Override
    public void closeProgress() {

    }
}
