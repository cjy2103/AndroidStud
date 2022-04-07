#include <jni.h>

JNIEXPORT jstring JNICALL
Java_com_example_ndk_MainActivity_getNdk(JNIEnv *env, jobject instance){
    return (*env) ->NewStringUTF(env, "C파일 에서 작성한 문자 입니다.");
}