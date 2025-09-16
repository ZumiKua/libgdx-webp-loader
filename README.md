# WebPLoader for libGDX

A webp images loader for libGDX.

currently, only Andorid, Windows and Linux are supported.

## How to use

### add dependencies

In your top-level build.gradle, add the following dependencies:

````

project(":desktop") {

    ...

    dependencies {

        ...

        implementation "io.github.zumikua:webploader-desktop:0.2.0"
    }
}

project(":android") {

    dependencies {

        ...

        implementation "io.github.zumikua:webploader-android:0.2.0"
    }
}

project(":core") {

    ...

    dependencies {

        ...

        implementation "io.github.zumikua:webploader-common:0.2.0"
    }
}

````

### create factories

In your game class, add a new constructor parameter `WebPLoaderNativeInterface` and use it to create factories.

````
public class MyGdxGame extends ApplicationAdapter {
	private final WebPPixmapFactory pixmapFactory;
	private final WebPLoaderFactory webpFactory;
	private final WebPTextureFactory textureFactory;

	public MyGdxGame(WebPLoaderNativeInterface nativeInterface) {
		webpFactory = new WebPLoaderFactory(nativeInterface);
		pixmapFactory = webpFactory.getPixmapFactory();
        textureFactory = webpFactory.getTextureFactory();
	}

	...

}
````

then, pass the right `WebPLoaderNativeInterface` instance to the Game based on the platform.

in your `AndroidLauncher`

````
        initialize(new MyGdxGame(new AndroidNativeInterface()), config);
````

in your `DesktopLauncher`
````
        new LwjglApplication(new MyGdxGame(new DesktopNativeInterface()), config);
````

### use these factories to load webp images


````
		Pixmap pixmap = pixmapFactory.createPixmap(Gdx.files.internal("badlogic.webp"));
		Texture img = textureFactory.createTexture("badlogic.webp");
````

`WebPTextureLoader` and `WebPPixmapLoader` for AssetManager are also provided.

for more usages, please refer to: [WebPLoaderSample](https://github.com/ZumiKua/libgdx-webp-loader/blob/main/core/src/io/github/zumikua/WebPLoaderSample.java)

## TODO

- [ ] MacOS support
- [ ] iOS support