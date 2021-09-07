#include <jni.h>
#include <string>
#include <sstream>
#include <decode.h>
#include <cstdint>
#ifndef JAVA_CLASS_NAME
#error JAVA_CLASS_NAME must be defined.
#endif

#define Concat(klass, name) Java_##klass##_##name
#define FuncNameInternal(klass, name) Concat(klass, name)
#define FuncName(name) FuncNameInternal(JAVA_CLASS_NAME, name)

extern "C" JNIEXPORT jint JNICALL
FuncName(getWebPDecoderVersion)(
        JNIEnv* env,
        jobject /* this */) {
    int code = WebPGetDecoderVersion();
    return code;
}

extern "C"
JNIEXPORT jint JNICALL
FuncName(getInfo)(JNIEnv *env, jobject thisObj, jbyteArray data, jint size,
                                           jintArray output) {
    jboolean copy;

    auto ptr = reinterpret_cast<uint8_t*>(env->GetPrimitiveArrayCritical(data, &copy));
    if(ptr == nullptr) {
        return -1;
    }
    WebPBitstreamFeatures features;
    auto ret = WebPGetFeatures(ptr, static_cast<size_t>(size), &features);
    env->ReleasePrimitiveArrayCritical(data, ptr, JNI_ABORT);
    jint tmp[] = {0, 0, 0};
    if(ret == VP8_STATUS_OK) {
        tmp[0] = static_cast<jint>(features.width);
        tmp[1] = static_cast<jint>(features.height);
        tmp[2] = features.has_alpha ? 1 : 0;
        env->SetIntArrayRegion(output, 0, 3, tmp);
    }

    return static_cast<jint>(ret);
}
extern "C"
JNIEXPORT jint JNICALL
FuncName(writeData)(JNIEnv *env, jobject thisObj, jobject dst,
                                             jbyteArray data, jint size, jboolean hasAlpha, jint picWidth) {

    auto dstPtr = reinterpret_cast<uint8_t*>(env->GetDirectBufferAddress(dst));
    auto dstSize = static_cast<size_t>(env->GetDirectBufferCapacity(dst));
    auto srcPtr = reinterpret_cast<uint8_t*>(env->GetPrimitiveArrayCritical(data, nullptr));
    if(srcPtr == nullptr) {
        return -1;
    }
    uint8_t* ret;
    if(hasAlpha) {
        ret = WebPDecodeRGBAInto(srcPtr, size, dstPtr, dstSize, picWidth * 4);
    }
    else {
        ret = WebPDecodeRGBInto(srcPtr, size, dstPtr, dstSize, picWidth * 3);
    }


    env->ReleasePrimitiveArrayCritical(data, srcPtr, JNI_ABORT);
    if(ret == nullptr) {
        return -2;
    }
    else {
        return 0;
    }

}