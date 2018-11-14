package com.deadlast.assets;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.SkinLoader.SkinParameter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class Resources {
	
	public final AssetManager manager = new AssetManager();
	
	public final String skin = "ui/uiskin.json";
	
	public final String playerImage = "entities/player.png";
	public final String enemyImage = "entities/enemy.png";
	
	public void loadSkin() {
		SkinParameter params = new SkinParameter("uiskin.atlas");
		manager.load(skin, Skin.class, params);
	}
	
	public void loadImages() {
		manager.load(playerImage, Texture.class);
		manager.load(enemyImage, Texture.class);
	}
	
	public void loadFonts() {};
	
	public void loadSounds() {};
	
	public void loadMusic() {};
	
	

}
