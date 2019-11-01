LOCAL_PATH := $(call my-dir)
include $(CLEAR_VARS)
LOCAL_MODULE := lameDemo2C
LOCAL_SRC_FILES := native-lib.cpp
include $(BUILD_SHARED_LIBRARY)