package Tools;

import Sprites.InteractiveObject;
import com.badlogic.gdx.physics.box2d.*;

import java.awt.event.ContainerListener;

public class WorldContactListener implements ContactListener {

    @Override
    public void beginContact(Contact contact) {
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();

        if (fixA.getUserData() == "head" || fixB.getUserData() == "head"){
            Fixture body = fixA.getUserData() == "head" ? fixA : fixB;
            Fixture object = body == fixA ? fixB : fixA;

            if(object.getUserData() != null && InteractiveObject.class.isAssignableFrom(object.getUserData().getClass()))
                ((InteractiveObject) object.getUserData()).onHeadHit();
        }
    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
