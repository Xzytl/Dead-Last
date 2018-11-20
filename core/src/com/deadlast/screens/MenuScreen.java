package com.deadlast.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.deadlast.game.MainGame;

public class MenuScreen extends DefaultScreen {

	protected Stage stage;

	public MenuScreen(MainGame game) {
		super(game);
		System.out.println("Loaded MenuScreen");
		// Create a new stage, and set it as the input processor
		stage = new Stage(new ScreenViewport());
		Gdx.input.setInputProcessor(stage);
	}
	
	@Override
	public void show() {
		System.out.println("Showing...");
		Table mainTable = new Table();
		mainTable.setFillParent(true);
		mainTable.top();
		
		// TODO: Replace with an asset manager
		Skin skin = new Skin(Gdx.files.internal("ui/uiskin.json"));
		
		TextButton playButton = new TextButton("Play", skin);
		playButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				game.changeScreen(MainGame.GAME);
			}
		});
		
		TextButton scoreButton = new TextButton("Scoreboard", skin);
		scoreButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				game.changeScreen(MainGame.SCOREBOARD);
			}
		});
		
		TextButton helpButton = new TextButton("How to Play", skin);
		helpButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				game.changeScreen(MainGame.HELP);
			}
		});
		
		TextButton exitButton = new TextButton("Exit", skin);
		exitButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Gdx.app.exit();
			}
		});
		
		mainTable.add(playButton).fillX().uniformX();
		mainTable.row().pad(10, 0, 10, 0);
		mainTable.add(scoreButton).fillX().uniformX();
		mainTable.row();
		mainTable.add(helpButton).fillX().uniformX();
		mainTable.row().pad(10, 0, 10, 0);
		mainTable.add(exitButton).fillX().uniformX();
		
		stage.addActor(mainTable);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0f, 0f, 0f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1/30f));
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(width, height, true);
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		Gdx.app.debug("DeadLast", "dispose main menu");
		stage.dispose();
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

}