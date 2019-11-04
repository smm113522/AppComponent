#include <jni.h>
#include <string>

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

