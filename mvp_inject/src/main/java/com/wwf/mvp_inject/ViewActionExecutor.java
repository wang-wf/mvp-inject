package com.wwf.mvp_inject;

import com.wwf.mvp_core.BaseView;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

/**
 * 供presenter使用的view执行器，封装了匹配和调用。
 * create by wenfeng.wang on 2019/2/15
 */
public class ViewActionExecutor {
    private BaseView mView;
    private Set<Method> matchingMethods;

    private ViewActionExecutor(BaseView view, Set<Method> methods){
        this.mView = view;
        this.matchingMethods = methods;
    }

    /**
     * 从view层实例中拿到匹配的方法集合，返回包装对象。
     * @param view BaseView实例
     * @param action 事件标识
     * @return Executor类实例
     */
    public static ViewActionExecutor matching(BaseView view, String action) {
        HashSet<Method> methods = new HashSet<>();
        //拿到所有方法，遍历找到符合条件的方法
        Method[] declaredMethods = view.getClass().getDeclaredMethods();
        for (Method method : declaredMethods) {
            if (isActionMatching(method, action)) {
                methods.add(method);
            }
        }
        return new ViewActionExecutor(view, methods);
    }

    /**
     * 实际开始执行view层匹配的方法。
     * @param data 事件数据参数，可变长度。
     */
    public void execute(Object... data) {
        for(Method method : matchingMethods) {
            if(isParamMatching(method, data)) {
                method.setAccessible(true);
                try {
                    method.invoke(mView, data);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //从一个方法上看是否有这个注解，如果有，判断action是否符合
    private static boolean isActionMatching(Method method, String action) {
        Annotation[] annotations = method.getDeclaredAnnotations();
        DataAction dataAction = null;
        for (Annotation annotation : annotations) {
            if (annotation instanceof DataAction) {
                dataAction = (DataAction) annotation;
                break;
            }
        }
        return null != dataAction && dataAction.value().equals(action);
    }

    //判断参数数量和类型是否符合
    private static boolean isParamMatching(Method method, Object... data) {
        Class<?>[] parameterTypes = method.getParameterTypes();
        if (parameterTypes.length == data.length) {
            for (int i = 0; i < parameterTypes.length; i++) {
                if (!parameterTypes[i].isInstance(data[i])) return false;
            }
            return true;
        }
        return false;
    }
}
