#include<jni.h>

jstring Java_com_example_ndktest_MainActivity_printHello
(JNIEnv* pEnv, jobject pThis) {
    return (*pEnv) -> NewStringUTF(pEnv, "Hello World!! ndk Success");
}