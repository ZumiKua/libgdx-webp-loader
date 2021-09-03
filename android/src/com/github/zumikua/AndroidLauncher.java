package com.github.zumikua;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.github.zumikua.WebPLoaderSample;
import com.github.zumikua.android.AndroidNativeInterface;

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(new WebPLoaderSample(new AndroidNativeInterface()), config);
	}
}
