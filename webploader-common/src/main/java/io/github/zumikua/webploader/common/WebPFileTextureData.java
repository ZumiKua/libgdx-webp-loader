package io.github.zumikua.webploader.common;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.PixmapIO;
import com.badlogic.gdx.graphics.TextureData;
import com.badlogic.gdx.utils.GdxRuntimeException;

public class WebPFileTextureData implements TextureData {

    final FileHandle mFile;
    int mWidth = 0;
    int mHeight = 0;
    Pixmap.Format mFormat;
    Pixmap mPixmap;
    boolean mUseMipMaps;
    boolean mIsPrepared = false;
    private final WebPPixmapFactory mPixmapFactory;

    public WebPFileTextureData (FileHandle file, Pixmap preloadedPixmap, Pixmap.Format format, boolean useMipMaps, WebPPixmapFactory pixmapFactory) {
        mPixmapFactory = pixmapFactory;
        mFile = file;
        mPixmap = preloadedPixmap;
        mFormat = format;
        mUseMipMaps = useMipMaps;
        if (mPixmap != null) {
            mWidth = mPixmap.getWidth();
            mHeight = mPixmap.getHeight();
            if (format == null) this.mFormat = mPixmap.getFormat();
        }
    }

    @Override
    public boolean isPrepared () {
        return mIsPrepared;
    }

    @Override
    public void prepare () {
        if (mIsPrepared) throw new GdxRuntimeException("Already prepared");
        if (mPixmap == null) {
            mPixmap = mPixmapFactory.createPixmap(mFile);
            mWidth = mPixmap.getWidth();
            mHeight = mPixmap.getHeight();
            if (mFormat == null) mFormat = mPixmap.getFormat();
        }
        mIsPrepared = true;
    }

    @Override
    public Pixmap consumePixmap () {
        if (!mIsPrepared) throw new GdxRuntimeException("Call prepare() before calling getPixmap()");
        mIsPrepared = false;
        Pixmap pixmap = this.mPixmap;
        this.mPixmap = null;
        return pixmap;
    }

    @Override
    public boolean disposePixmap () {
        return true;
    }

    @Override
    public int getWidth () {
        return mWidth;
    }

    @Override
    public int getHeight () {
        return mHeight;
    }

    @Override
    public Pixmap.Format getFormat () {
        return mFormat;
    }

    @Override
    public boolean useMipMaps () {
        return mUseMipMaps;
    }

    @Override
    public boolean isManaged () {
        return true;
    }

    public FileHandle getFileHandle () {
        return mFile;
    }

    @Override
    public TextureDataType getType () {
        return TextureDataType.Pixmap;
    }

    @Override
    public void consumeCustomData (int target) {
        throw new GdxRuntimeException("This TextureData implementation does not upload data itself");
    }

    public String toString () {
        return mFile.toString();
    }
}
