package io.github.zumikua;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import io.github.zumikua.webploader.common.WebPLoaderFactory;
import io.github.zumikua.webploader.common.WebPLoaderNativeInterface;
import io.github.zumikua.webploader.common.WebPPixmapFactory;
import io.github.zumikua.webploader.common.WebPPixmapLoader;
import io.github.zumikua.webploader.common.WebPTextureFactory;
import io.github.zumikua.webploader.common.WebPTextureLoader;

public class WebPLoaderSample extends ApplicationAdapter {
	private final WebPLoaderFactory mFactory;
	private final WebPPixmapFactory mPixmapFactory;
	private final WebPTextureFactory mTextureFactory;
	private AssetManager mAssetManager;
	SpriteBatch batch;
	Texture img;
	Texture img2;
	private Pixmap mPixmap;
	private Texture img3;
	private Texture img4;

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
		mAssetManager = new AssetManager();
		mAssetManager.setLoader(Texture.class, ".webp", new WebPTextureLoader(mAssetManager.getFileHandleResolver(), mTextureFactory));
		mAssetManager.setLoader(Pixmap.class, ".webp", new WebPPixmapLoader(mAssetManager.getFileHandleResolver(), mPixmapFactory));
		mAssetManager.load("badlogic.webp", Texture.class);
		mAssetManager.load("img.webp", Pixmap.class);
		mAssetManager.finishLoading();
		img3 = mAssetManager.get("badlogic.webp", Texture.class);
		Pixmap pm = mAssetManager.get("img.webp", Pixmap.class);
		img4 = new Texture(pm);

	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 0, 0, 1);
		batch.begin();
		batch.draw(img, 0, 0);
		batch.draw(img2, img2.getWidth(), 0);
		batch.draw(img3, img3.getWidth(), img3.getHeight());
		batch.draw(img4, 0, img4.getHeight());
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
		img2.dispose();
		img4.dispose();
		mPixmap.dispose();
		mAssetManager.clear();
	}
}
