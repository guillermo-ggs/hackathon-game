package dev.fong.hackathongame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Level1 extends ScreenAdapter {
    MainGame game;
    Texture texture;
    private TextureAtlas atlas;
    private OrthographicCamera gamecam;
    private Viewport gamePort;

    public Level1(MainGame game) {
        atlas = new TextureAtlas("Akemi.pack");
        this.game = game;
        texture = new Texture("GohanCalvo.jpg");
        gamecam = new OrthographicCamera();
        gamePort = new FitViewport(480, 208, gamecam);
    }

    public TextureAtlas getAtlas(){
        return atlas;
    }

    @Override
    public void show(){

    }

    @Override
    public void render(float delta){
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        game.batch.draw(texture, 0, 0);
        game.batch.end();
    }

    @Override
    public void resize(int width, int height){

    }
}
