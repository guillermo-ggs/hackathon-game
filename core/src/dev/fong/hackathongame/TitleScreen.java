package dev.fong.hackathongame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;


public class TitleScreen extends ScreenAdapter {
    public static final int BTN_WIDTH = 200;
    public static final int BTN_HEIGHT = 100;

    MainGame game;

    Texture playBtn;
    Texture playBtnHover;
    Texture exitBtn;
    Texture exitBtnHover;

    public TitleScreen(MainGame game) {
        this.game = game;

        playBtn = new Texture("playbtn.png");
        playBtnHover = new Texture("playbtnhover.png");
        exitBtn = new Texture("exitBtn.png");
        exitBtnHover = new Texture("exitBtnHover.png");
    }


    @Override
    public void show(){
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
        Gdx.gl.glClearColor(0, 0.8f, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();

        int x = (Gdx.graphics.getWidth() / 2) - (BTN_WIDTH / 2);
        int y = Gdx.graphics.getHeight() / 2;
        int playBtnY = y - 100;

        if(Gdx.input.getX() < x + BTN_WIDTH && Gdx.input.getX() > x && Gdx.input.getY() < y + BTN_HEIGHT && Gdx.input.getY() > y){
            game.batch.draw(exitBtnHover, x, playBtnY, BTN_WIDTH, BTN_HEIGHT);
            if(Gdx.input.isTouched()) {
                Gdx.app.exit();
            }
        }
        else {
            game.batch.draw(exitBtn, x, playBtnY, BTN_WIDTH, BTN_HEIGHT);
        }

        if(Gdx.input.getX() < x + BTN_WIDTH && Gdx.input.getX() > x && Gdx.input.getY() < playBtnY + BTN_HEIGHT && Gdx.input.getY() > playBtnY){
            game.batch.draw(playBtnHover, x, y, BTN_WIDTH, BTN_HEIGHT);
            if(Gdx.input.isTouched()) {
                this.dispose();
                game.setScreen(new Level1(game));
            }
        }
        else {
            game.batch.draw(playBtn,x, y, BTN_WIDTH, BTN_HEIGHT);
        }

        //game.font.draw(game.batch, "GAME", Gdx.graphics.getWidth() * .25f, Gdx.graphics.getHeight() * .75f);
        //game.font.draw(game.batch, "Click the circle to win.", Gdx.graphics.getWidth() * .25f, Gdx.graphics.getHeight() * .5f);
        //game.font.draw(game.batch, "Press space to play.", Gdx.graphics.getWidth() * .25f, Gdx.graphics.getHeight() * .25f);
        game.batch.end();
    }

    @Override
    public void hide(){
        Gdx.input.setInputProcessor(null);
    }
}
