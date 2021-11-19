package ru.mipt.bit.platformer.event;

import com.badlogic.gdx.graphics.Texture;
import ru.mipt.bit.platformer.graphics.objects.Drawable;
import ru.mipt.bit.platformer.graphics.objects.LibGdxGraphicBullet;
import ru.mipt.bit.platformer.models.GameObject;
import ru.mipt.bit.platformer.models.logic.LogicBullet;

import java.util.Map;

import static ru.mipt.bit.platformer.graphics.TexturePathsConst.bulletTexturePath;

public class EventAddBullet implements Event {
    @Override
    public void performGameObjectToDrawableMap(Map<GameObject, Drawable> gameObjectToDrawableMap, GameObject gameObject) {
        gameObjectToDrawableMap.put(
                gameObject,
                new LibGdxGraphicBullet(new Texture(bulletTexturePath), (LogicBullet) gameObject)
        );
    }
}
