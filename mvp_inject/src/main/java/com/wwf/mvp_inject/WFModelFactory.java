package com.wwf.mvp_inject;

import com.wwf.mvp_core.BaseModel;
import com.wwf.mvp_core.BasePresenter;
import com.wwf.mvp_core.BaseView;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Model层静态工厂，通过反射创建Model层对象
 * create by wenfeng.wang on 2018/12/29
 */
public final class WFModelFactory {

    /**
     * 依据presenter创建Model对象
     * @param presenter 带 WFPresenter 注解的presenter对象
     * @return WFModel注解中声明的Model类
     */
    static <M extends BaseModel> M create(BasePresenter presenter) {
        ParameterizedType superClass;
        Type superType = presenter.getClass().getGenericSuperclass();
        superClass = (superType instanceof ParameterizedType) ? (ParameterizedType) superType : null;
        if(null != superClass) {
            Type[] actualTypeArguments = superClass.getActualTypeArguments();
            if(actualTypeArguments.length < 1) return null;

            try {
                return (M) ((Class)actualTypeArguments[0]).newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
