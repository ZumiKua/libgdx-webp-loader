package io.github.zumikua;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import io.github.zumikua.webploader.common.WebPLoaderFactory;
import io.github.zumikua.webploader.common.WebPLoaderNativeInterface;
import io.github.zumikua.webploader.common.WebPPixmapFactory;
import io.github.zumikua.webploader.common.WebPTextureFactory;

public class WebPLoaderSample extends ApplicationAdapter {
	private final WebPLoaderFactory mFactory;
	private final WebPPixmapFactory mPixmapFactory;
	private final WebPTextureFactory mTextureFactory;
	SpriteBatch batch;
	Texture img;
	Texture img2;
	private Pixmap mPixmap;

	public WebPLoaderSample(WebPLoaderNativeInterface nativeInterface) {
		mFactory = new WebPLoaderFactory(nativeInterface);
		mPixmapFactory = mFactory.getPixmapFactory();
		mTextureFactory = mFactory.getTextureFactory();
	}
	
	@Override
	public void create () {
		mPixmap = mPixmapFactory.createPixmap(Gdx.files.internal("badlogic.webp"));
		img = new Texture(mPixmap);
		img2 = mTextureFactory.createTexture("badlogic.webp");
		batch = new SpriteBatch();
	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 0, 0, 1);
		batch.begin();
		batch.draw(img, 0, 0);
		batch.draw(img2, img2.getWidth(), 0);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
		img2.dispose();
		mPixmap.dispose();
	}
}
