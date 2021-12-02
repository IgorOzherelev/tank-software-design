package ru.mipt.bit.platformer.event;

import com.badlogic.gdx.graphics.Texture;
import ru.mipt.bit.platformer.graphics.objects.Drawable;
import ru.mipt.bit.platformer.graphics.objects.LibGdxGraphicObstacle;
import ru.mipt.bit.platformer.models.GameObject;
import ru.mipt.bit.platformer.models.logic.LogicObstacle;

import java.util.Map;

import static ru.mipt.bit.platformer.graphics.TexturePathsConst.treeTexturePath;

/**
 * Entity
 * */
public class EventAddTree implements Event {
    @Override
    public void performGameObjectToDrawableMap(Map<GameObject, Drawable> gameObjectToDrawableMap, GameObject gameObject) {
        gameObjectToDrawableMap.put(
                gameObject,
                new LibGdxGraphicObstacle(new Texture(treeTexturePath), (LogicObstacle) gameObject)
        );
    }
}
