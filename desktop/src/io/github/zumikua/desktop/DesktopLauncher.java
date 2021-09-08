package io.github.zumikua.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import io.github.zumikua.WebPLoaderSample;
import io.github.zumikua.webploader.desktop.DesktopNativeInterface;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new WebPLoaderSample(new DesktopNativeInterface()), config);
	}
}
