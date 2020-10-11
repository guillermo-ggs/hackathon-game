package dev.fong.hackathongame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class MainGame extends Game {
	SpriteBatch batch;
	ShapeRenderer shapeRenderer;
	BitmapFont font;
	public static final int V_Width = 1600;
	public static final int V_Height = 900;
	public static final int PPM = 10;
	public static final int screenWidth = 1600;
	public static final int screenHeight = 900;
	public static Music menuMusic;

	@Override
	public void create () {
		batch = new SpriteBatch();
		shapeRenderer = new ShapeRenderer();
		font = new BitmapFont();
		menuMusic = Gdx.audio.newMusic(Gdx.files.internal("mainMenumusic.mp3"));
		menuMusic.setLooping(true);
		menuMusic.setVolume(0.1f);
		menuMusic.play();
		setScreen(new TitleScreen(this));
	}

	@Override
	public void dispose () {
		batch.dispose();
		shapeRenderer.dispose();
		font.dispose();
		menuMusic.dispose();
	}
}
