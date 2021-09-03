package com.github.zumikua.webploader.desktop;

import com.github.zumikua.webploader.common.WebPLoaderNativeInterface;

import java.nio.ByteBuffer;

public class DesktopNativeInterface implements WebPLoaderNativeInterface {
    @Override
    public native int getWebPDecoderVersion();

    @Override
    public native int getInfo(byte[] data, int size, int[] output);

    @Override
    public native int writeData(ByteBuffer dst, byte[] data, int size, boolean hasAlpha, int picWidth);

    static {
        new SharedLibraryLoader().load("webploader-native");
    }
}