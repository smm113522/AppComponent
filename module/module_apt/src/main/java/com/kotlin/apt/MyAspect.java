package com.kotlin.apt;

import android.app.Service;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import com.code.utils.NavigationUtil;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

@Aspect
public class MyAspect {

    @Around("execution(@com.kotlin.apt * *(..))")
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
        NavigationUtil.INSTANCE.toActivity(path);
    }

}
