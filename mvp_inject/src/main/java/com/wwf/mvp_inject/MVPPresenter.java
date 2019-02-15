package com.wwf.mvp_inject;

import com.wwf.mvp_core.BaseModel;
import com.wwf.mvp_core.BasePresenter;
import com.wwf.mvp_core.BaseView;

/**
 * 让presenter继承自此类，在泛型上标注对应的Model类型，无需手动实例化Model对象。
 * create by wenfeng.wang on 2019/1/7
 */
public abstract class MVPPresenter<M extends BaseModel> extends BasePresenter<M> {

    public MVPPresenter(BaseView view) {
        super(view);
    }

    @Override
    protected M createModel() {
        return WFModelFactory.create(this);
    }
}
