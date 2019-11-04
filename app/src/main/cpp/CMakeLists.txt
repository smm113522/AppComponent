
cmake_minimum_required(VERSION 3.4.1)

SET(CMAKE_CXX_FLAGS "-Wno-error=format-security -Wno-error=pointer-sign")

#设置生成的so动态库最后输出的路径
set(CMAKE_LIBRARY_OUTPUT_DIRECTORY ${PROJECT_SOURCE_DIR}/../jniLibs/${ANDROID_ABI})

#include_directories (${PROJECT_SOURCE_DIR}/libmp3lame)
#当前cmakelists.txt所在目录的所有.c .cpp源文件
#AUX_SOURCE_DIRECTORY(. SRC_LIST)

#自定义搜索规则
set(SRC_DIR lame)
include_directories(lame)
aux_source_directory(lame SRC_LIST)

## 注释里面是学习内容
#add_executable(demo demo.cpp) # 生成可执行文件
#add_library(common STATIC util.cpp) # 生成静态库
#add_library(common SHARED util.cpp) # 生成动态库或共享库

#生成so库，直接使用变量代表那些.c文件集合
add_library(native-lib SHARED native-lib.cpp ${SRC_LIST})

find_library(log-lib log)

#target_link_libraries(native-lib ${log-lib})
target_link_libraries(native-lib -ljnigraphics ${log-lib})

