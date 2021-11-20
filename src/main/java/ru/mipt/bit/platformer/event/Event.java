package ru.mipt.bit.platformer.event;

import ru.mipt.bit.platformer.graphics.objects.Drawable;
import ru.mipt.bit.platformer.models.GameObject;

import java.util.List;
import java.util.Map;

public interface Event {
    void performGameObjectToDrawableMap(
            Map<GameObject, Drawable> gameObjectToDrawableMap, GameObject gameObject);

    default <T extends GameObject> void performGameObjectList(List<T> gameObjectList, T gameObject) {
        gameObjectList.add(gameObject);
    }
}
