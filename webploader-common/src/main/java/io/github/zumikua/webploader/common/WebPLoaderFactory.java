package io.github.zumikua.webploader.common;

public class WebPLoaderFactory {

    private final WebPLoaderNativeInterface mNativeInterface;

    public WebPLoaderFactory(WebPLoaderNativeInterface nativeInterface) {
        mNativeInterface = nativeInterface;
    }

    /**
     * create a PixmapFactory.
     * This method will create a new PixmapFactory on every call.
     * @return the PixmapFactory created.
     */
    public WebPPixmapFactory createPixmapFactory() {
        return new WebPPixmapFactory(mNativeInterface);
    }
}