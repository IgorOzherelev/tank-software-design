package ru.mipt.bit.platformer.graphics.renderers;

import ru.mipt.bit.platformer.event.EventSubscriber;
import ru.mipt.bit.platformer.graphics.objects.Drawable;
import ru.mipt.bit.platformer.models.GameObject;

import java.util.Map;

/**
 * Entity
 * */
public interface Renderer extends EventSubscriber {
    void render();
    void dispose();
    Map<GameObject, Drawable> getDrawablesMap();

    void toggleHealth();
}
