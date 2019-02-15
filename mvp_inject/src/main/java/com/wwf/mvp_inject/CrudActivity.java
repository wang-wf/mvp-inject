package com.wwf.mvp_inject;

import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.wwf.mvp_core.BasePresenter;
import com.wwf.mvp_core.MVPActivity;

/**
 * 让activity继承自此类，可使用注解{@link WFLayout}，声明对应的resId，无需手动setContentView
 * Created by Administrator on 2018/11/19
 */
public abstract class CrudActivity<D extends ViewDataBinding, P extends BasePresenter> extends MVPActivity<D, P> {
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

    @Override
    public void onData(@NonNull String s, @NonNull Object... object) {
        //因为框架中实现了基于方法注解的事件分发，可以放弃这个接口了，如果需要在框架外实现，由子类重载此方法做具体实现。
    }
}
