package ru.mipt.bit.platformer.event;

import ru.mipt.bit.platformer.graphics.renderers.Renderer;
import ru.mipt.bit.platformer.models.GameObject;

import java.util.List;

/**
 * Entity
 * */
public interface Event {
    default void performGameObjectToRenderer(Renderer renderer, GameObject gameObject) {}

    default <T extends GameObject> void performGameObjectList(List<T> gameObjectList, T gameObject) {
        gameObjectList.add(gameObject);
    }
}
