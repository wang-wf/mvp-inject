package com.wwf.mvp_inject;

import com.wwf.mvp_core.MVPActivity;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 将此注解用在activity上，会在实例化时在父类{@link MVPActivity}的onCreate中获取，
 * 根据此注解中的class实例化presenter。
 * create by wenfeng.wang on 2018/12/29
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface WFLayout {

    /** 当前activity对应的presenter的字节码 */
    int id() default 0;
}

