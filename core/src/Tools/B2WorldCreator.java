package Tools;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.*;
import dev.fong.hackathongame.Level1;
import dev.fong.hackathongame.MainGame;

public class B2WorldCreator {
    public B2WorldCreator(Level1 screen) {
        World world = screen.getWorld();
        TiledMap map = screen.getMap();
        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;

       for(MapObject object : map.getLayers().get(5).getObjects().getByType(RectangleMapObject.class)){
           Rectangle rect = ((RectangleMapObject) object).getRectangle();

           bdef.type = BodyDef.BodyType.StaticBody;
           bdef.position.set((rect.getX() + rect.getWidth() / 2),  (rect.getY() + rect.getHeight() / 2));

           body = world.createBody(bdef);

           shape.setAsBox(rect.getWidth() / 2, rect.getHeight() / 2);
           fdef.shape = shape;
           body.createFixture(fdef);
       }
    }
}
