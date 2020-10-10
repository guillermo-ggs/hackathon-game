package dev.fong.hackathongame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class MainGame extends Game {
	SpriteBatch batch;
	ShapeRenderer shapeRenderer;
	BitmapFont font;

	public static final int PPM = 100;

	@Override
	public void create () {
		batch = new SpriteBatch();
		shapeRenderer = new ShapeRenderer();
		font = new BitmapFont();
		setScreen(new TitleScreen(this));
	}

	@Override
	public void dispose () {
		batch.dispose();
		shapeRenderer.dispose();
		font.dispose();
	}
}
