package io.github.zumikua.webploader.common;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.AsynchronousAssetLoader;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.PixmapLoader;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.utils.Array;

public class WebPPixmapLoader extends AsynchronousAssetLoader<Pixmap, PixmapLoader.PixmapParameter> {

    private final WebPPixmapFactory mPixmapFactory;
    Pixmap mPixmap;


    public WebPPixmapLoader(FileHandleResolver resolver, WebPPixmapFactory pixmapFactory) {
        super(resolver);
        mPixmapFactory = pixmapFactory;
    }

    @Override
    public void loadAsync (AssetManager manager, String fileName, FileHandle file, com.badlogic.gdx.assets.loaders.PixmapLoader.PixmapParameter parameter) {
        mPixmap = null;
        mPixmap = mPixmapFactory.createPixmap(file);
    }

    @Override
    public Pixmap loadSync (AssetManager manager, String fileName, FileHandle file, com.badlogic.gdx.assets.loaders.PixmapLoader.PixmapParameter parameter) {
        Pixmap pixmap = this.mPixmap;
        this.mPixmap = null;
        return pixmap;
    }

    @Override
    public Array<AssetDescriptor> getDependencies (String fileName, FileHandle file, com.badlogic.gdx.assets.loaders.PixmapLoader.PixmapParameter parameter) {
        return null;
    }

}
