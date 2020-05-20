package com.kotlin.apt;

import android.app.Service;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;



import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

@Aspect
public class MyAspect {

    @Around("execution(@com.kotlin.apt.MyRoute * *(..))")
    public void aroundJoinPoint(final ProceedingJoinPoint joinPoint) {

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();

        MyRoute myRoute = method.getAnnotation(MyRoute.class);

        String path = myRoute.paths();

        Object object = joinPoint.getThis();

        Context context = null;
        if (object instanceof FragmentActivity) {
            context = (FragmentActivity) object;
        } else if (object instanceof Fragment) {
            context = ((Fragment) object).getContext();
        } else if (object instanceof Service) {
            context = (Service) object;
        }

        try {
            if (!TextUtils.isEmpty(path)) {
                Object result = joinPoint.proceed();  //执行注解的方法

            }else {
//                NavigationUtil.INSTANCE.toActivity(RouterPath.path_kotlin_activity);
            }

        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

}
