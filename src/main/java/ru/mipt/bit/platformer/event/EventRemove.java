package ru.mipt.bit.platformer.event;

import ru.mipt.bit.platformer.graphics.objects.Drawable;
import ru.mipt.bit.platformer.models.Colliding;
import ru.mipt.bit.platformer.models.GameObject;

import java.util.List;
import java.util.Map;

public class EventRemove implements Event {
    @Override
    public void performGameObjectToDrawableMap(Map<GameObject, Drawable> gameObjectToDrawableMap, GameObject gameObject) {
        Drawable drawable = gameObjectToDrawableMap.remove(gameObject);
        // хм, почему-то возникает race condition
        // библиотека точно ничего не параллелит?
        // наивная защита
        if (drawable != null) {
            drawable.dispose();
        }
    }

    @Override
    public <T extends GameObject> void performGameObjectList(List<T> gameObjectList, T gameObject) {
        gameObjectList.remove(gameObject);
    }
}
