MY_LOCAL_PATH := $(call my-dir)
LOCAL_PATH := $(MY_LOCAL_PATH)

include $(CLEAR_VARS)

LOCAL_MODULE    := nativeutil
LOCAL_SRC_FILES := nativeutil.cpp

include $(BUILD_SHARED_LIBRARY)
LOCAL_PATH := $(MY_LOCAL_PATH)

include $(LOCAL_PATH)/stockfish/Android.mk
LOCAL_PATH := $(MY_LOCAL_PATH)

include $(LOCAL_PATH)/gtb/Android.mk
LOCAL_PATH := $(MY_LOCAL_PATH)

include $(LOCAL_PATH)/rtb/Android.mk

