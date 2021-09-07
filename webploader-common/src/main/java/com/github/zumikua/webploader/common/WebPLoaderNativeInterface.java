package com.github.zumikua.webploader.common;

import java.nio.ByteBuffer;

public interface WebPLoaderNativeInterface {

    int getWebPDecoderVersion();

    int getInfo(byte[] data, int size, int[] output);

    int writeData(ByteBuffer dst, byte[] data, int size, boolean hasAlpha, int picWidth);
}
