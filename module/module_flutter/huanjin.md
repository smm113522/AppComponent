### 1.配置windows 下载
配置bin 中位置
以及url 配置

FLUTTER_STORAGE_BASE_URL:   https://storage.flutter-io.cn

PATH:   C:\Users\Aijiar\Desktop\flutter\bin


开始flutter doctor 看是否配置了

卡在 打包中。需要配置 运行
https://www.jianshu.com/p/4cff47818e54

```XML
buildscript {
    ext.kotlin_version = '1.3.50'
    repositories {
       //google()
         //jcenter()
         maven { url 'https://maven.aliyun.com/repository/google' }
         maven { url 'https://maven.aliyun.com/repository/jcenter' }
         maven { url 'http://maven.aliyun.com/nexus/content/groups/public' }
         maven {url 'http://download.flutter.io'}
    }

    dependencies {
        classpath 'com.android.tools.build:gradle:3.5.0'
        classpath "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version"
    }
}

allprojects {
    repositories {
        //google()
         //jcenter()
         maven { url 'https://maven.aliyun.com/repository/google' }
         maven { url 'https://maven.aliyun.com/repository/jcenter' }
         maven { url 'http://maven.aliyun.com/nexus/content/groups/public' }
         maven {url 'http://download.flutter.io'}
    }
}
```

几乎是可以的。。。


新建卡在creating 过程中。。。环境没有配置好
运行，卡在打包过程中。。。俩个难处。。。。 ，，，google 里面下载不下来包导致的。。

环境总的，，，但是还是会有点问题
https://www.cnblogs.com/lovelyYakir/p/7610396.html



，先到pubspec.yaml 中pub get 或者pbu upgrade 更新一下吧。

然后在main.dart 中更新 

### 2快捷键
stful
stles


### 3,第三方demo flutter 运行

须知
项目运行

  ```
	flutter packages get // 项目初始化插件
	flutter run          // 项目运行
  ```

### 4.   其他简单方法收集

    debugShowCheckedModeBanner: false, // 去掉Debug



单例模式
https://juejin.im/post/5c83d5ac5188257de66337a9


widgets




