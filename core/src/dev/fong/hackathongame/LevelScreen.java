package dev.fong.hackathongame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;

public class LevelScreen extends ScreenAdapter {
    MainGame game;

    Texture lvl1Btn;
    Texture lvl1Hover;
    Texture lvl2Btn;
    Texture lvl2Hover;

    public LevelScreen(MainGame game) {
        this.game = game;

        lvl1Btn = new Texture("lvl1btn.png");
        lvl1Hover = new Texture("lvl1hover.png");
        lvl2Btn = new Texture("lvl2btn.png");
        lvl2Hover = new Texture("lvl2hover.png");
    }


    @Override
    public void show() {
        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean keyDown(int keyCode) {
                if (keyCode == Input.Keys.SPACE) {
                    game.setScreen(new Level1(game));
                }
                return true;
            }
        });
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0.8f, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();

        int x = (Gdx.graphics.getWidth() / 2) - (TitleScreen.BTN_WIDTH / 2);
        int y = Gdx.graphics.getHeight() / 2;
        int playBtnY = y - 100;

        if (Gdx.input.getX() < x + TitleScreen.BTN_WIDTH && Gdx.input.getX() > x && Gdx.input.getY() < y + TitleScreen.BTN_HEIGHT && Gdx.input.getY() > y) {
            game.batch.draw(lvl2Hover, x, playBtnY, TitleScreen.BTN_WIDTH, TitleScreen.BTN_HEIGHT);
        } else {
            game.batch.draw(lvl2Btn, x, playBtnY, TitleScreen.BTN_WIDTH, TitleScreen.BTN_HEIGHT);
        }

        if (Gdx.input.getX() < x + TitleScreen.BTN_WIDTH && Gdx.input.getX() > x && Gdx.input.getY() < playBtnY + TitleScreen.BTN_HEIGHT && Gdx.input.getY() > playBtnY) {
            game.batch.draw(lvl1Hover, x, y, TitleScreen.BTN_WIDTH, TitleScreen.BTN_HEIGHT);
            if(Gdx.input.isTouched()) {
                this.dispose();
                game.setScreen(new Level1(game));
            }
        } else {
            game.batch.draw(lvl1Btn, x, y, TitleScreen.BTN_WIDTH, TitleScreen.BTN_HEIGHT);
        }

        game.batch.end();
    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }

}


