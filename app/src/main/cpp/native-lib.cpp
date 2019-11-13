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


#include "lame/lame.h"

static lame_global_flags *gfp = NULL;

extern "C"
JNIEXPORT void JNICALL
Java_com_ck_driver_lametomp3_LameMp3_lameInit(JNIEnv *env, jclass type, jint inSampleRate,
                                              jint outChannelNum, jint outSampleRate,
                                              jint outBitRate,
                                              jint quality) {
    if (gfp != NULL) {
        lame_close(gfp);
        gfp = NULL;
    }
    //  初始化
    gfp = lame_init();
//    LOGI("初始化lame库完成");
    //配置参数
    lame_set_in_samplerate(gfp, inSampleRate);
    lame_set_num_channels(gfp, outChannelNum);
    lame_set_out_samplerate(gfp, outSampleRate);
    lame_set_brate(gfp, outBitRate);
    lame_set_quality(gfp, quality);
    lame_init_params(gfp);
//    LOGI("配置lame参数完成");
}

extern "C"
JNIEXPORT jint JNICALL
Java_com_ck_driver_lametomp3_LameMp3_lameFlush(JNIEnv *env, jclass type, jbyteArray mp3buf_) {
//    jbyte *mp3buf = (*env)->GetByteArrayElements(env, mp3buf_, NULL);
//    jsize len = (*env)->GetArrayLength(env, mp3buf_);
//    int resut = lame_encode_flush(gfp, mp3buf, len);
//    (*env)->ReleaseByteArrayElements(env, mp3buf_, mp3buf, 0);
//    LOG_I("写入mp3数据到文件，返回结果=%d", resut);
//    return resut;

    jbyte *mp3buf = env->GetByteArrayElements(mp3buf_, NULL);
    jsize len = env->GetArrayLength(mp3buf_);
//    unsigned char *mp3buffer;
    int resut = lame_encode_flush(gfp, reinterpret_cast<unsigned char *>(mp3buf), len);
    env->ReleaseByteArrayElements(mp3buf_, mp3buf, 0);
//    LOG_I("写入mp3数据到文件，返回结果=%d", resut);
    return resut;
}

extern "C"
JNIEXPORT void JNICALL
Java_com_ck_driver_lametomp3_LameMp3_lameClose(JNIEnv *env, jclass type) {
    lame_close(gfp);
    gfp = NULL;
//    LOGI("释放lame资源");lameEncodeByByte
}

extern "C"
JNIEXPORT jint JNICALL
Java_com_ck_driver_lametomp3_LameMp3_lameEncode(JNIEnv *env, jclass type, jshortArray letftBuf_,
                                                jshortArray rightBuf_, jint sampleRate,
                                                jbyteArray mp3Buf_) {
    if (letftBuf_ == NULL || mp3Buf_ == NULL) {
//        LOGI("letftBuf和rightBuf 或mp3Buf_不能为空");
        return -1;
    }
    jshort *letftBuf = NULL;
    jshort *rightBuf = NULL;
    if (letftBuf_ != NULL) {
        letftBuf = env->GetShortArrayElements(letftBuf_, NULL);
    }
    if (rightBuf_ != NULL) {
        rightBuf = env->GetShortArrayElements(rightBuf_, NULL);
    }
    jbyte *mp3Buf = env->GetByteArrayElements(mp3Buf_, NULL);
    jsize readSizes = env->GetArrayLength(mp3Buf_);
    // 编码
    int result = lame_encode_buffer(gfp, letftBuf, rightBuf, sampleRate,
                                    reinterpret_cast<unsigned char *>(mp3Buf), readSizes);

    // 释放资源
    if (letftBuf_ != NULL) {
        env->ReleaseShortArrayElements(letftBuf_, letftBuf, 0);
    }
    if (rightBuf_ != NULL) {
        env->ReleaseShortArrayElements(rightBuf_, rightBuf, 0);
    }
    env->ReleaseByteArrayElements(mp3Buf_, mp3Buf, 0);
//    LOG_I("编码pcm为mp3，数据长度=%d", result);
    return result;
}


#include <Mp3Encoder.cpp>

Mp3Encoder *encoder = NULL;

extern "C"
JNIEXPORT jboolean JNICALL
Java_com_ck_driver_lametomp3_LameMp3_encodeFile(
        JNIEnv *env, jclass clazz, jstring wavFilePath, jstring mp3FilePath) {

    const char *pcmPath = env->GetStringUTFChars(wavFilePath, 0);
    const char *mp3Path = env->GetStringUTFChars(mp3FilePath, 0);

    encoder = new Mp3Encoder();
    encoder->Init(pcmPath, 2, 128, 44100, mp3Path);

    env->ReleaseStringUTFChars(wavFilePath, pcmPath);
    env->ReleaseStringUTFChars(mp3FilePath, mp3Path);

//    lame_t lame = lame_init();
//    lame_set_in_samplerate(lame, 16000);   // 采样率，必须与录制时的相同，并且要转换成MP3的话，必须双通道录制
//    lame_set_VBR(lame, vbr_default);
//    lame_init_params(lame);

//    encoder->Encode();
//
//    encoder->Destory();

}


extern "C"
JNIEXPORT void JNICALL
Java_com_ck_driver_lametomp3_LameMp3_encode(JNIEnv *env, jobject instance) {
    encoder->Encode();
}

extern "C"
JNIEXPORT void JNICALL
Java_com_ck_driver_lametomp3_LameMp3_destroy(JNIEnv *env, jobject instance) {
    encoder->Destory();
}

