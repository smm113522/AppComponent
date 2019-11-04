
cmake_minimum_required(VERSION 3.4.1)

SET(CMAKE_CXX_FLAGS "-Wno-error=format-security -Wno-error=pointer-sign")

#设置生成的so动态库最后输出的路径
set(CMAKE_LIBRARY_OUTPUT_DIRECTORY ${PROJECT_SOURCE_DIR}/../jniLibs/${ANDROID_ABI})

#include_directories (${PROJECT_SOURCE_DIR}/libmp3lame)

#当前cmakelists.txt所在目录的所有.c .cpp源文件
AUX_SOURCE_DIRECTORY(. SRC_LIST)

#增加其他目录的源文件到集合变量中
#list(APPEND SRC_LIST
#        libmp3lame/lame.c
#        )

#生成so库，直接使用变量代表那些.c文件集合
add_library(native-lib SHARED ${SRC_LIST})

## 注释里面是学习内容
#add_executable(demo demo.cpp) # 生成可执行文件
#add_library(common STATIC util.cpp) # 生成静态库
#add_library(common SHARED util.cpp) # 生成动态库或共享库

#add_library(native-lib SHARED native-lib.cpp)

find_library(log-lib log)

target_link_libraries(native-lib ${log-lib})