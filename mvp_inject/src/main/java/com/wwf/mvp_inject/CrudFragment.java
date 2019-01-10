package com.wwf.mvp_inject;

import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.wwf.mvp_core.BasePresenter;
import com.wwf.mvp_core.MVPFragment;

/**
 * create by wenfeng.wang on 2019/1/10
 */
public class CrudFragment<D extends ViewDataBinding, P extends BasePresenter> extends MVPFragment<D, P> {
    private WFInjectFactory mInjectFactory;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        mInjectFactory = new WFInjectFactory(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    public int createLayout() {
        return 0;
    }

    @Nullable
    @Override
    public P createPresenter() {
        return null;
    }
}
