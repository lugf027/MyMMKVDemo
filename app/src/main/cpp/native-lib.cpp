#include <jni.h>
#include <string>

using namespace std;

extern "C"
JNIEXPORT void JNICALL
Java_com_luguangfeng_mymmkvdemo_util_MyKVTry_writeKV(JNIEnv *env, jclass thiz,
                                                     jstring key,jstring value) {
    // TODO: implement writeKV()

}
extern "C"
JNIEXPORT jstring JNICALL
Java_com_luguangfeng_mymmkvdemo_util_MyKVTry_stringFromJNI(JNIEnv *env, jclass thiz) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}
extern "C"
JNIEXPORT jstring JNICALL
Java_com_luguangfeng_mymmkvdemo_util_MyKVTry_readK(JNIEnv *env, jclass clazz, jstring key) {
    return env->NewStringUTF("to do");
}
extern "C"
JNIEXPORT jboolean JNICALL
Java_com_luguangfeng_mymmkvdemo_util_MyKVTry_deleteK(JNIEnv *env, jclass clazz, jstring key) {
    return true;
}
extern "C"
JNIEXPORT void JNICALL
Java_com_luguangfeng_mymmkvdemo_util_MyKVTry_initIOFilePath(JNIEnv *env, jclass clazz,
                                                            jstring file_path) {
    if (!file_path) {
        return;
    }
    char *path = const_cast<char *>(env->GetStringUTFChars(file_path, nullptr));
    if (!path) {
        return;
    }
    char *filePathCp = strdup(path);

}