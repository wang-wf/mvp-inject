package com.wwf.mvp_inject;

import com.wwf.mvp_core.BaseModel;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 将此注解用在presenter上，会在通过反射工厂实例化时，取到此注解声明的对应model，
 * 调用其无参构造器实例化，并注入到presenter中。
 * create by wenfeng.wang on 2018/12/29
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface WFPresenter {

    /** 当前activity对应的presenter的字节码 */
    Class<? extends BaseModel> modelType();
}

