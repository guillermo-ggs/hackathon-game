package Sprites;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.*;
import dev.fong.hackathongame.Level1;
import dev.fong.hackathongame.MainGame;

import java.lang.reflect.Array;

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

    public Akemi(World world, Level1 level){
        super(level.getAtlas().findRegion("Akemi"));//sprite map for akemi
        this.world = world;
        currentState = State.STANDING;
        previousState = State.STANDING;
        stateTimer = 0;
        runningRight = true;

        com.badlogic.gdx.utils.Array<TextureRegion> frames = new com.badlogic.gdx.utils.Array<>();

        for(int i = 1; i < 4; i++)
            frames.add(new TextureRegion(getTexture(), i* 16, 0, 16, 16));
        akemiRun = new Animation<>(0.1f, frames);
        frames.clear();

        for( int i = 4; i < 6; i++)
            frames.add(new TextureRegion(getTexture(), i * 16, 0, 16, 16));
        akemiJump = new Animation <>(0.1f, frames);

        akemiStand = new TextureRegion(getTexture(), 0, 0, 16, 16);

        defineAkemi();
        setBounds(0, 0, 16 / MainGame.PPM, 16 / MainGame.PPM);
        setRegion(akemiStand);
    }

    public void defineAkemi(){
        BodyDef bdef = new BodyDef();
        bdef.position.set(32 / MainGame.PPM, 32 / MainGame.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody (bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(10 / MainGame.PPM);

        fdef.shape = shape;
        b2body.createFixture(fdef);
    }

    public void update(float dt){
        setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight() / 2);
        setRegion(getFrame(dt));
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
