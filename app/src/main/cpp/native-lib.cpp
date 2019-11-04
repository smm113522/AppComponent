#include <jni.h>
#include <string>
#include <tgmath.h>
#include <android/bitmap.h>
#include "lame/lame.h"

/**
 * 静态注册
 */

extern "C" JNIEXPORT jstring JNICALL
Java_com_ck_driver_lamedemo2c_MainActivity_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}


extern "C" JNIEXPORT jstring JNICALL
Java_com_kotlin_ImageActivity_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    std::string hello = "Hello from C++1";
    return env->NewStringUTF(hello.c_str());
}

/**
 * 动态注册
 */

extern "C" {

jint add(JNIEnv *env, jclass clazz, jint a, jint b) {
    return a + b;
}

jint RegisterNatives(JNIEnv *env) {
    jclass clazz = env->FindClass("com/ck/driver/lamedemo2c/MainActivity");
    if (clazz == NULL) {
        //        LOGE("con't find class: com/afei/jnidemo/MainActivity");
        return JNI_ERR;
    }
    JNINativeMethod methods_MainActivity[] = {
            {"add", "(II)I", (void *) add}
    };
    // int len = sizeof(methods_MainActivity) / sizeof(methods_MainActivity[0]);
    return env->RegisterNatives(clazz, methods_MainActivity,
                                sizeof(methods_MainActivity) / sizeof(methods_MainActivity[0]));
}
// 不知道干啥的，，但是很有用。。。
jint JNI_OnLoad(JavaVM *vm, void *reserved) {
    JNIEnv *env = NULL;
    if (vm->GetEnv((void **) &env, JNI_VERSION_1_6) != JNI_OK) {
        return JNI_ERR;
    }
    jint result = RegisterNatives(env);
    //    LOGD("RegisterNatives result: %d", result);
    return JNI_VERSION_1_6;
}

}

/**
 *  图片处理
 */
#define PI 3.14

void gaussBlur1(int *pix, int w, int h, int radius) {
    float sigma = (float) (1.0 * radius / 2.57);
    float deno = (float) (1.0 / (sigma * sqrt(2.0 * PI)));
    float nume = (float) (-1.0 / (2.0 * sigma * sigma));
    float *gaussMatrix = (float *) malloc(sizeof(float) * (radius + radius + 1));
    float gaussSum = 0.0;
    for (int i = 0, x = -radius; x <= radius; ++x, ++i) {
        float g = (float) (deno * exp(1.0 * nume * x * x));
        gaussMatrix[i] = g;
        gaussSum += g;
    }
    int len = radius + radius + 1;
    for (int i = 0; i < len; ++i)
        gaussMatrix[i] /= gaussSum;
    int *rowData = (int *) malloc(w * sizeof(int));
    int *listData = (int *) malloc(h * sizeof(int));
    for (int y = 0; y < h; ++y) {
        memcpy(rowData, pix + y * w, sizeof(int) * w);
        for (int x = 0; x < w; ++x) {
            float r = 0, g = 0, b = 0;
            gaussSum = 0;
            for (int i = -radius; i <= radius; ++i) {
                int k = x + i;
                if (0 <= k && k <= w) {
                    //得到像素点的rgb值
                    int color = rowData[k];
                    int cr = (color & 0x00ff0000) >> 16;
                    int cg = (color & 0x0000ff00) >> 8;
                    int cb = (color & 0x000000ff);
                    r += cr * gaussMatrix[i + radius];
                    g += cg * gaussMatrix[i + radius];
                    b += cb * gaussMatrix[i + radius];
                    gaussSum += gaussMatrix[i + radius];
                }
            }
            int cr = (int) (r / gaussSum);
            int cg = (int) (g / gaussSum);
            int cb = (int) (b / gaussSum);
            pix[y * w + x] = cr << 16 | cg << 8 | cb | 0xff000000;
        }
    }
    for (int x = 0; x < w; ++x) {
        for (int y = 0; y < h; ++y)
            listData[y] = pix[y * w + x];
        for (int y = 0; y < h; ++y) {
            float r = 0, g = 0, b = 0;
            gaussSum = 0;
            for (int j = -radius; j <= radius; ++j) {
                int k = y + j;
                if (0 <= k && k <= h) {
                    int color = listData[k];
                    int cr = (color & 0x00ff0000) >> 16;
                    int cg = (color & 0x0000ff00) >> 8;
                    int cb = (color & 0x000000ff);
                    r += cr * gaussMatrix[j + radius];
                    g += cg * gaussMatrix[j + radius];
                    b += cb * gaussMatrix[j + radius];
                    gaussSum += gaussMatrix[j + radius];
                }
            }
            int cr = (int) (r / gaussSum);
            int cg = (int) (g / gaussSum);
            int cb = (int) (b / gaussSum);
            pix[y * w + x] = cr << 16 | cg << 8 | cb | 0xff000000;
        }
    }
    free(gaussMatrix);
    free(rowData);
    free(listData);
}

extern "C"
JNIEXPORT void JNICALL
Java_com_ck_driver_lamedemo2c_MainActivity_gaussBlur(JNIEnv *env, jobject /* this */, jobject bmp) {
    AndroidBitmapInfo info = {0};//初始化BitmapInfo结构体
    int *data = NULL;//初始化Bitmap图像数据指针
    AndroidBitmap_getInfo(env, bmp, &info);
    AndroidBitmap_lockPixels(env, bmp, (void **) &data);//锁定Bitmap，并且获得指针
    /**********高斯模糊算法作对int数组进行处理***********/
    //调用gaussBlur函数，把图像数据指针、图片长宽和模糊半径传入
    gaussBlur1(data, info.width, info.height, 80);
    /****************************************************/
    AndroidBitmap_unlockPixels(env, bmp);//解锁
}

/**
 * lame 中环境是否搭建好
 */
extern "C"
JNIEXPORT jstring JNICALL
Java_com_ck_driver_lamedemo2c_MainActivity_getVersion(
        JNIEnv *env,
        jobject /* this */) {
    //把 lame版本号返回给java
    return env->NewStringUTF(get_lame_version());
//    return env->NewStringUTF("1111");
}
