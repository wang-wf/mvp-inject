package com.wwf.mvp_inject;

import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.wwf.mvp_core.BasePresenter;
import com.wwf.mvp_core.BaseView;
import com.wwf.mvp_core.MVPActivity;

/**
 * 让activity继承自此类，可使用注解{@link WFView}，声明对应的presenter，无需手动实例化。
 * Created by Administrator on 2018/11/19
 */
public class CrudActivity<D extends ViewDataBinding, P extends BasePresenter> extends MVPActivity<D, P> {
    private WFInjectFactory mInjectFactory;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        mInjectFactory = new WFInjectFactory(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    public int createLayout() {
        return mInjectFactory.createLayout();
    }

    @Nullable
    @Override
    public P createPresenter() {
        return (P)mInjectFactory.createPresenter();
    }
}
