package ru.mipt.bit.platformer.event;

import com.badlogic.gdx.graphics.Texture;
import ru.mipt.bit.platformer.graphics.objects.Drawable;
import ru.mipt.bit.platformer.graphics.objects.LibGdxGraphicTank;
import ru.mipt.bit.platformer.models.GameObject;
import ru.mipt.bit.platformer.models.logic.LogicTank;

import java.util.Map;

import static ru.mipt.bit.platformer.graphics.TexturePathsConst.tankTexturePath;

public class EventAddTank implements Event {
    @Override
    public void performGameObjectToDrawableMap(Map<GameObject, Drawable> gameObjectToDrawableMap, GameObject gameObject) {
        gameObjectToDrawableMap.put(
                gameObject,
                new LibGdxGraphicTank(new Texture(tankTexturePath), (LogicTank) gameObject)
        );
    }
}
