#include <jni.h>
#include <string>

extern "C"
JNIEXPORT void JNICALL
Java_com_luguangfeng_mymmkvdemo_util_MyKVTry_writeKV(JNIEnv *env, jclass thiz, jstring key,
                                                     jstring value) {
    // TODO: implement writeKV()
}
extern "C"
JNIEXPORT jstring JNICALL
Java_com_luguangfeng_mymmkvdemo_util_MyKVTry_stringFromJNI(JNIEnv *env, jclass thiz) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}