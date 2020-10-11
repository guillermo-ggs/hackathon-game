package Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import dev.fong.hackathongame.Level1;
import dev.fong.hackathongame.MainGame;

public class Akemi extends Sprite {
    public enum State { FALLING, JUMPING, STANDING, RUNNING}
    public State currentState;
    public State previousState;
    public World world;
    public Body b2body;
    private TextureRegion akemiStand;
    private Animation<TextureRegion> akemiRun;
    private Animation<TextureRegion> akemiJump;
    private float stateTimer;
    private boolean runningRight;
    private Level1 level;

    public Akemi(World world, Level1 level){
        super (new Texture("akemistand.png"));
        //super(level.getAtlas().findRegion("akemi"));//sprite map for akemi
        this.level = level;
        this.world = world;
        currentState = State.STANDING;
        previousState = State.STANDING;
        stateTimer = 0;
        runningRight = true;

        Array<TextureRegion> frames = new Array<TextureRegion>();

        for(int i = 1; i < 4; i++)
            frames.add(new TextureRegion(getTexture(), i* 16, 0, 16, 16));
        akemiRun = new Animation<TextureRegion>(0.1f, frames);
        frames.clear();

        for( int i = 4; i < 6; i++)
            frames.add(new TextureRegion(getTexture(), i * 16, 0, 16, 16));
        akemiJump = new Animation <TextureRegion>(0.1f, frames);

        akemiStand = new TextureRegion(getTexture(), 0, 0, 32, 64);

        defineAkemi();
        setBounds(0, 0, 32, 64);
        setRegion(akemiStand);
    }

    public void update(float dt){
        setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight() / 2);
    }

    public void defineAkemi(){
        BodyDef bdef = new BodyDef();
        bdef.position.set(350, 370);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody (bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(5);

        fdef.shape = shape;
        b2body.createFixture(fdef);

        EdgeShape head = new EdgeShape();
        head.set (new Vector2(-2, 10), new Vector2(2, 10));
        fdef.shape = head;
        fdef.isSensor = true;

        b2body.createFixture(fdef).setUserData("head");

    }

    public TextureRegion getFrame(float dt){
        currentState = getState();

        TextureRegion region;
        switch (currentState){
            case JUMPING:
                region = (TextureRegion) akemiJump.getKeyFrame(stateTimer);
                break;
            case RUNNING:
                region = (TextureRegion) akemiRun.getKeyFrame(stateTimer);
                break;
            case FALLING:
            case STANDING:
            default:
                region = akemiStand;
                break;
        }
        if ((b2body.getLinearVelocity().x < 0 || !runningRight) && !region.isFlipX()){
            region.flip(true, false);
            runningRight = false;
        }
        else if ((b2body.getLinearVelocity().x > 0 || runningRight) && region.isFlipX()){
            region.flip(true, false);
            runningRight = true;
        }
        stateTimer = currentState == previousState ? stateTimer + dt : 0;
        previousState = currentState;
        return region;
    }
    public State getState(){
        if (b2body.getLinearVelocity().y > 0 || (b2body.getLinearVelocity().y < 0 && previousState == State.JUMPING))
            return State.JUMPING;
        if (b2body.getLinearVelocity().y < 0)
            return State.FALLING;
        else if (b2body.getLinearVelocity().x != 0)
            return State.RUNNING;
        else
            return State.STANDING;
    }
}
