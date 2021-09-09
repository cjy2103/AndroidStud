#include <jni.h>
#include <string>

extern "C" JNIEXPORT jstring JNICALL
Java_com_example_ndktest2_MainActivity_stringFromJNI(
        JNIEnv* env,
        jobject MainActivity) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}