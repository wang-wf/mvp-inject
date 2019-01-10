package com.wwf.mvp_inject;

import com.wwf.mvp_core.BaseModel;
import com.wwf.mvp_core.BasePresenter;
import com.wwf.mvp_core.BaseView;

import java.lang.reflect.Constructor;

/**
 * 适用于mvp_core框架的注入工厂，
 * create by wenfeng.wang on 2018/12/29
 */
public final class WFInjectFactory {
    private BaseView activity;

    public WFInjectFactory(BaseView activity) {
        this.activity = activity;
    }

//    /** 从注解中取到layoutId，并使用DataBinding框架初始化，返回ViewDataBinding */
//    public <T extends ViewDataBinding> T createDataBinding() {
//        WFLayout layoutRes = activity.getClass().getAnnotation(WFLayout.class);
//        return null == layoutRes ? null : (T) DataBindingUtil.setContentView(activity, layoutRes.id());
//    }

    /** 从注解中取到layoutId，并使用DataBinding框架初始化，返回ViewDataBinding */
    public int createLayout() {
        WFLayout layoutRes = activity.getClass().getAnnotation(WFLayout.class);
        return null == layoutRes ? 0 : layoutRes.id();
    }

    /**
     * 从注解中取到对应的Presenter实现类型，并利用反射调用其默认构造器，传入activity对象，返回其实例化对象
     * 注意，使用此框架必须保证有且只有一个参数的构造器
     */
    public <M extends BaseModel, V extends BaseView, T extends BasePresenter<M, V>> T createPresenter() {
        WFView wfView = activity.getClass().getAnnotation(WFView.class);
        if(null == wfView) return null;

        try {
            Constructor<?>[] constructors = wfView.presenterType().getConstructors();
            for(Constructor item : constructors) {
                if(item.getParameterTypes().length == 1)
                    return (T)item.newInstance(activity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
