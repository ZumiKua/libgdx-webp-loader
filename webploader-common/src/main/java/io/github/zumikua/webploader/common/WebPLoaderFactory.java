package io.github.zumikua.webploader.common;

public class WebPLoaderFactory {

    private final WebPLoaderNativeInterface mNativeInterface;
    private final WebPPixmapFactory mPixmapFactory;
    private final WebPTextureFactory mTextureFactory;

    public WebPLoaderFactory(WebPLoaderNativeInterface nativeInterface) {
        mNativeInterface = nativeInterface;
        mPixmapFactory = new WebPPixmapFactory(mNativeInterface);
        mTextureFactory = new WebPTextureFactory(mPixmapFactory);
    }

    /**
     * return a PixmapFactory.
     * This method will return the same PixmapFactory instance on every call.
     * @return the PixmapFactory created.
     */
    public WebPPixmapFactory getPixmapFactory() {
        return mPixmapFactory;
    }

    /**
     * return a PixmapFactory.
     * This method will return the same PixmapFactory instance on every call.
     * @return the PixmapFactory created.
     */
    public WebPTextureFactory getTextureFactory() {
        return mTextureFactory;
    }
}