package com.wwf.mvp_inject;

import android.support.annotation.NonNull;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 将此注解用在view层中供presenter调用的方法上，声明对应的onData中的action
 * 根据此注解中的class实例化presenter。
 * create by wenfeng.wang on 2018/12/29
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DataAction {

    /** 当前方法对应的onData action */
    @NonNull String value();
}

