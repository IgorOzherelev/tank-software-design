package ru.mipt.bit.platformer.event;

import ru.mipt.bit.platformer.graphics.objects.Drawable;
import ru.mipt.bit.platformer.graphics.renderers.Renderer;
import ru.mipt.bit.platformer.models.Colliding;
import ru.mipt.bit.platformer.models.GameObject;

import java.util.List;
import java.util.Map;

public class EventRemove implements Event {
    @Override
    public void performGameObjectToRenderer(Renderer renderer, GameObject gameObject) {
        Drawable drawable = renderer.getDrawablesMap().remove(gameObject);
        if (drawable != null) {
            drawable.dispose();
        }
    }

    @Override
    public <T extends GameObject> void performGameObjectList(List<T> gameObjectList, T gameObject) {
        gameObjectList.remove(gameObject);
    }
}
