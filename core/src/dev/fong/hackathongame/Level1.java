package dev.fong.hackathongame;

import Sprites.Akemi;
import Tools.B2WorldCreator;
import Tools.WorldContactListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import org.graalvm.compiler.word.Word;

public class Level1 extends ScreenAdapter {
    MainGame game;
    Texture texture;
    private TextureAtlas atlas;
    private OrthographicCamera gamecam;
    private Viewport gamePort;

    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private World world;
    private Box2DDebugRenderer b2dr;
    //private AkemiTest player;


    public Level1(MainGame game) {
        //atlas = new TextureAtlas("textures.png");
        this.game = game;
        texture = new Texture("textures.png");
        gamecam = new OrthographicCamera();
        gamePort = new FitViewport(1400, 1000, gamecam);

        mapLoader = new TmxMapLoader();
        map = mapLoader.load("level1.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);

        //gamecam.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() / 2, 0);

       world = new World(new Vector2(0, -10), true);

       b2dr = new Box2DDebugRenderer();

       new B2WorldCreator(world, map);

       //player = new AkemiTest(world, this);

        world.setContactListener(new WorldContactListener());
        gamecam.setToOrtho(false, gamePort.getWorldWidth()/2, gamePort.getWorldHeight()/2);

    }

    public void handleInput(float dt) {
        if (Gdx.input.isTouched());
            //gamecam.position.x += 100 * dt;
    }

    public void update(float dt) {
        handleInput(dt);
        gamecam.update();
        renderer.setView(gamecam);
    }

    public TextureAtlas getAtlas(){
        return atlas;
    }

    @Override
    public void show(){

    }

    @Override
    public void render(float delta){
        update(delta);

        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.render();
    }

    @Override
    public void resize(int width, int height){

    }
}
