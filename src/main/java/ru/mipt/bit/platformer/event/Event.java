package ru.mipt.bit.platformer.event;

import ru.mipt.bit.platformer.graphics.objects.Drawable;
import ru.mipt.bit.platformer.models.Colliding;
import ru.mipt.bit.platformer.models.GameObject;

import java.util.List;
import java.util.Map;

public interface Event {
    void performGameObjectToDrawableMap(
            Map<GameObject, Drawable> gameObjectToDrawableMap, GameObject gameObject);

    default void performCollidingList(List<Colliding> collidingList, Colliding colliding) {
        collidingList.add(colliding);
    }
}
