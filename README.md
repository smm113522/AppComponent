# KotlinApp]
jenkins 打包配置，组件化+模块化 demo一波
[]()

## kotlin 为创客行用户端3.0 做准备 用kotlin 语言开发

https://mp.weixin.qq.com/s/DUlyzvIOeVunELRsy0zBYg
https://juejin.im/post/5d15bbb86fb9a07f03574e56

build 配置

添加apache 里面的包

useLibrary 'org.apache.http.legacy'

maven {
    url 'https://maven.google.com/'
    name 'Google'
}
    
为空判断    
https://blog.csdn.net/cheng9981/article/details/53229310
    

Shadow是一个腾讯自主研发的Android插件框架，经过线上亿级用户量检验
和360 的插件框架    
https://github.com/Qihoo360/RePlugin
https://github.com/ManbangGroup/Phantom

open_nsfw_android-master

## 计划

1. 首页其他功能查看 类似 腾讯ui 一样。

2。图片 glide 处理

3。检黄


4，bugle 更新。bugle 热更新

5，基础 面试，所有尝试

6。mvp

7。网络获取

8。Rxjava

9。集成微信支付、支付宝支付功能

10。阿里百川即时通讯  网易云信IM实现

11。混合开发，原生与JS互相交互调用

12使用网易云信实现直播功能


13 Frameworks



新增布局：
PercentRelativeLayout（百分比相对布局）
可以通过百分比控制控件的大小。
PercentFrameLayout（百分比帧布局）
可以通过百分比控制控件的大小。

1
mvvm

https://blog.csdn.net/xuexiangjys/article/details/79735123

图片显示


<ImageView
    android:layout_width="280dp"
    android:layout_height="140dp"
    android:src="@mipmap/ic_launcher"
    binding:url="@{viewModel.entity.img}"
    binding:placeholderRes="@{R.mipmap.ic_launcher_round}"
    />
                
                
public final class ViewAdapter {
    @BindingAdapter(value = {"url", "placeholderRes"}, requireAll = false)
    public static void setImageUri(ImageView imageView, String url, int placeholderRes) {
        if (!TextUtils.isEmpty(url)) {
            //使用Glide框架加载图片
            Glide.with(imageView.getContext())
                    .load(url)
                    .apply(new RequestOptions().placeholder(placeholderRes))
                    .into(imageView);
        }
    }
}



demo
https://github.com/googlesamples/android-architecture-components


## bug 热修复 和热更新
https://github.com/BuglyDevTeam/Bugly-Android-Demo
tinker-support 打包

适配
https://www.jianshu.com/p/1302ad5a4b04
https://www.wanandroid.com/blog/show/2343(重要）

Flutter 案例
https://juejin.im/post/5d6cb579f265da03da24aeb9






