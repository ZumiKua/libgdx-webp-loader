package com.github.zumikua.webploader.common;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.utils.GdxRuntimeException;

import java.nio.ByteBuffer;

public class WebPPixmapFactory {

    private final WebPLoaderNativeInterface mNativeInterface;

    WebPPixmapFactory(WebPLoaderNativeInterface nativeInterface) {
        mNativeInterface = nativeInterface;
    }

    /**
     * Create a Pixmap from file handle.
     * @param file webp file.
     * @return the Pixmap created from the given file.
     */
    public Pixmap createPixmap(FileHandle file) {
        return createPixmapInternal(file.readBytes(), file.name());
    }

    /**
     * Create a Pixmap from encoded webp data.
     * @param bytes encoded webp data.
     * @return the Pixmap created from the given data.
     */
    public Pixmap createPixmap(byte[] bytes) {
        return createPixmapInternal(bytes, "");
    }

    private Pixmap createPixmapInternal(byte[] bytes, String source) {
        int[] out = new int[3];
        int result = mNativeInterface.getInfo(bytes, bytes.length, out);
        if(result != 0) {
            throw new GdxRuntimeException("error occurred while fetching webp picture info " + source + " errcode: " + result);
        }
        Pixmap pixmap = new Pixmap(out[0], out[1], out[2] == 0 ? Pixmap.Format.RGB888 : Pixmap.Format.RGBA8888);

        ByteBuffer pixels = pixmap.getPixels();
        if(pixels.isDirect()){
            int ret = mNativeInterface.writeData(pixels, bytes, bytes.length, out[2] == 1, out[0]);
            switch (ret) {
                case -1:
                    throw new GdxRuntimeException("GetPrimitiveArrayCritical returns null, OOM?");
                case -2:
                    throw new GdxRuntimeException("Insufficient buffer in Pixmap.");
                case 0:
                    break;
                default:
                    throw new GdxRuntimeException("unknown error " + ret);
            }
            return pixmap;
        }
        else {
            throw new GdxRuntimeException("expect a direct buffer inside Gdx2DPixmap.");
        }
    }
}
