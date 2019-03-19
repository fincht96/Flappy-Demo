package com.finch.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.finch.game.FlappyDemo;

import static com.finch.game.FlappyDemo.*;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = FlappyDemo.sWIDTH;
		config.height = FlappyDemo.sHEIGHT;
		config.title = FlappyDemo.sSTRING_TITLE;
		new LwjglApplication(new FlappyDemo(), config);
	}
}
