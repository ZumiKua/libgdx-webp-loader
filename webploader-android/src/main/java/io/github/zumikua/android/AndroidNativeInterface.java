package io.github.zumikua.android;

import io.github.zumikua.webploader.common.WebPLoaderNativeInterface;

import java.nio.ByteBuffer;

public class AndroidNativeInterface implements WebPLoaderNativeInterface {
    @Override
    public native int getWebPDecoderVersion();

    @Override
    public native int getInfo(byte[] data, int size, int[] output);

    @Override
    public native int writeData(ByteBuffer dst, byte[] data, int size, boolean hasAlpha, int picWidth);

    static {
        System.loadLibrary("webploader-native");
    }
}
