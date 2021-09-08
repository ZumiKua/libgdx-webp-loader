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

public class WebPLoaderSample extends ApplicationAdapter {
	private final WebPLoaderFactory mFactory;
	private final WebPPixmapFactory mPixmapFactory;
	SpriteBatch batch;
	Texture img;
	private Pixmap mPixmap;

	public WebPLoaderSample(WebPLoaderNativeInterface nativeInterface) {
		mFactory = new WebPLoaderFactory(nativeInterface);
		mPixmapFactory = mFactory.createPixmapFactory();
	}
	
	@Override
	public void create () {
		mPixmap = mPixmapFactory.createPixmap(Gdx.files.internal("badlogic.webp"));
		img = new Texture(mPixmap);
		batch = new SpriteBatch();
	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 0, 0, 1);
		batch.begin();
		batch.draw(img, 0, 0);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
		mPixmap.dispose();
	}
}
