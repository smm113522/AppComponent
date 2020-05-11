c编译步骤

1，创建demo 项目，然后运行正常

2，添加jni 文件夹 用于写c++ 和c 代码

3，android studio 配置环境 ndk 环境 jre 路径

4，ndk 插件下载，然后配置extenrnatools 也是环境的一部分

5，External Tools 添加 一个javah-jni 和ndK-build 

1内容：Program $JDKPath$/bin/javah

​	  Arguments -jni -encoding UTF-8 -d $ModuleFileDir$\app\src\main\jni\ $FileClass$

​	  Working directory $ModuleFileDir$\app\src\main\java 

其它默认

2内容：Program D:\android-sdk-windows\ndk\20.0.5594570\ndk-build.cmd

​	  Arguments 空白

​	  Working direcotpory 	$ModuleFileDir$\app\src\main\java

6，在jni 中创建Android.mk

```xml
# 当前路径
LOCAL_PATH := $(call my-dir)

# 清除LOCAL_XXX变量
include $(CLEAR_VARS)

# 原生库名称
LOCAL_MODULE := native-lib

# 原生代码文件
LOCAL_SRC_FILES =: cpp\native-lib.cpp

# 编译动态库
include $(BUILD_SHARED_LIBRARY)
```

和创建Application.mk

```xml
# 原生库名称
APP_MODULES := native-lib

# 指定机器指令集
#APP_ABI := armeabi armeabi-v7a arm64-v8a x86 x86_64 mips mips64
APP_ABI := armeabi-v7a arm64-v8a x86 x86_64
```

7,新建cpp文件

8.mainActvity 中和cpp 方法绑定

​	public native String stringtoC()		

9.项目绑定c++

​	

```
sourceSets {
    main {
        jni {
            srcDirs 'src/main/jni', 'src/main/jni/'
        }
    }
}

externalNativeBuild {
    ndkBuild {
        path 'src/main/jni/Android.mk'
    }
}
```

运行 正常运行

10.打包so文件

项目右键选择External Tools ->ndK-build 

在项目libs 中产生so文件了。