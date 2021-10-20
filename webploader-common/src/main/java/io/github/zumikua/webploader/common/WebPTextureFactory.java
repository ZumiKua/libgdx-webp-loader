package io.github.zumikua.webploader.common;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.TextureData;

public class WebPTextureFactory {

    private final WebPPixmapFactory mPixmapFactory;

    WebPTextureFactory(WebPPixmapFactory pixmapFactory) {
        mPixmapFactory = pixmapFactory;
    }

    /**
     * create a managed Texture from the internal file
     * @param internalPath the path to the internal file
     * @return the managed texture created.
     */
    public Texture createTexture(String internalPath) {
        return createTexture(Gdx.files.internal(internalPath));
    }

    /**
     * create a managed Texture from the file
     * @param file webp file
     * @return the managed texture created.
     */
    public Texture createTexture(FileHandle file) {
        return createTexture(file, null, false);
    }

    /**
     * create a managed Texture from the file
     * @param file webp file
     * @param useMipMaps whether to use mipmaps
     * @return the managed texture created.
     */
    public Texture createTexture(FileHandle file, boolean useMipMaps) {
        return createTexture(file, null, useMipMaps);
    }

    /**
     * create a managed Texture from the file
     * @param file webp file
     * @param format texture format
     * @param useMipMaps whether to use mipmaps
     * @return the managed texture created.
     */
    public Texture createTexture(FileHandle file, Pixmap.Format format, boolean useMipMaps) {
        Pixmap pixmap = mPixmapFactory.createPixmap(file);
        WebPFileTextureData data = new WebPFileTextureData(file, pixmap, format, useMipMaps, mPixmapFactory);
        return new Texture(data);
    }

    /**
     * create a TextureData from the webp file
     * @param file webp file
     * @param format texture format
     * @param genMipMaps whether to generate mipmaps
     * @return the TextureData created.
     */
    public TextureData createTextureData(FileHandle file, Pixmap.Format format, boolean genMipMaps) {
        Pixmap pixmap = mPixmapFactory.createPixmap(file);
        return new WebPFileTextureData(file, pixmap, format, genMipMaps, mPixmapFactory);
    }
}
