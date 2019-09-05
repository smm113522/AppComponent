package com.replugin.host;

import android.content.ComponentName;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.qihoo360.i.Factory2;
import com.qihoo360.replugin.RePlugin;

/**
 * 打开插件中的Fragment
 * <p>
 * 作者 coder
 * 创建时间 2017/7/6
 *
 * 没有成功
 */

public class PluginFragmentActivity extends FragmentActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /**
         * 注意：
         *
         * 如果一个插件是内置插件，那么这个插件的名字就是文件的前缀，比如：demo1.jar插件的名字就是demo1(host-gradle插件自动生成)，可以执行诸如RePlugin.fetchClassLoader("demo1")的操作；
         * 如果一个插件是外置插件，通过RePlugin.install("/sdcard/demo1.apk")安装的，则必须动态获取这个插件的名字来使用：
         * PluginInfo pluginInfo = RePlugin.install("/sdcard/demo1.apk");
         * RePlugin.preload(pluginInfo);//耗时
         * String name = pluginInfo != null ? pluginInfo.getName() : null;
         * ClassLoader classLoader = RePlugin.fetchClassLoader(name);
        */

        boolean isBuiltIn = true;
        String pluginName = isBuiltIn ? "replugin" : "com.kotlin.replugin";

        //注册相关Fragment的类
        //注册一个全局Hook用于拦截系统对XX类的寻找定向到Demo1中的XX类主要是用于在xml中可以直接使用插件中的类
//        RePlugin.registerHookingClass("com.kotlin.replugin.DemoFragment",
//                RePlugin.createComponentName(pluginName, "com.kotlin.replugin.DemoFragment"), null);

//        String source = "com.kotlin.replugin.DemoFragment";
//
//        ComponentName componentName = RePlugin.createComponentName(pluginName,"com.kotlin.replugin.DemoFragment");
//
//        String plugin = componentName.getPackageName();
//        String type = componentName.getClassName();
//        Class c = null;
//
//        boolean d = Factory2.registerDynamicClass(source, plugin,type,c);

        setContentView(R.layout.activity_plugin_fragment);

//        View view = RePlugin.fetchViewByLayoutName("com.kotlin.replugin", "main_fragment" , null);
        //代码使用插件Fragment
        ClassLoader d1ClassLoader = RePlugin.fetchClassLoader("replugin");//获取插件的ClassLoader
        try {
            Fragment fragment = d1ClassLoader.loadClass("com.kotlin.replugin.DemoCodeFragment")
                    .asSubclass(Fragment.class).newInstance();//使用插件的Classloader获取指定Fragment实例
            getSupportFragmentManager().beginTransaction().add(R.id.container2, fragment).commit();//添加Fragment到UI
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


//        try {
//            Fragment fragment = d1ClassLoader.loadClass("com.kotlin.replugin.DemoFragment")
//                    .asSubclass(Fragment.class).newInstance();//使用插件的Classloader获取指定Fragment实例
//            getSupportFragmentManager().beginTransaction().add(R.id.container2, fragment).commit();//添加Fragment到UI
//            getSupportFragmentManager().beginTransaction().add(R.id.container2, new PluginFragment()).commit();//添加Fragment到UI
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }

    }
}
