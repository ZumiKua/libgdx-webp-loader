package io.github.zumikua.webploader.common;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.AssetLoader;
import com.badlogic.gdx.assets.loaders.AsynchronousAssetLoader;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.TextureLoader;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.TextureData;
import com.badlogic.gdx.utils.Array;

public class WebPTextureLoader extends AsynchronousAssetLoader<Texture, TextureLoader.TextureParameter> {
    private final WebPTextureFactory mTextureFactory;
    private final TextureLoaderInfo mInfo = new TextureLoaderInfo();

    static public class TextureLoaderInfo {
        String filename;
        TextureData data;
        Texture texture;
    };


    public WebPTextureLoader(FileHandleResolver resolver, WebPTextureFactory textureFactory) {
        super(resolver);
        mTextureFactory = textureFactory;
    }

    @Override
    public void loadAsync(AssetManager manager, String fileName, FileHandle file, TextureLoader.TextureParameter parameter) {
        mInfo.filename = fileName;
        if (parameter == null || parameter.textureData == null) {
            Pixmap.Format format = null;
            boolean genMipMaps = false;
            mInfo.texture = null;

            if (parameter != null) {
                format = parameter.format;
                genMipMaps = parameter.genMipMaps;
                mInfo.texture = parameter.texture;
            }

            mInfo.data = mTextureFactory.createTextureData(file, format, genMipMaps);
        } else {
            mInfo.data = parameter.textureData;
            mInfo.texture = parameter.texture;
        }
        if (!mInfo.data.isPrepared()) mInfo.data.prepare();
    }

    @Override
    public Texture loadSync(AssetManager manager, String fileName, FileHandle file, TextureLoader.TextureParameter parameter) {
        Texture texture = mInfo.texture;
        if (texture != null) {
            texture.load(mInfo.data);
        } else {
            texture = new Texture(mInfo.data);
        }
        if (parameter != null) {
            texture.setFilter(parameter.minFilter, parameter.magFilter);
            texture.setWrap(parameter.wrapU, parameter.wrapV);
        }
        return texture;
    }

    @Override
    public Array<AssetDescriptor> getDependencies(String fileName, FileHandle file, TextureLoader.TextureParameter parameter) {
        return null;
    }
}
