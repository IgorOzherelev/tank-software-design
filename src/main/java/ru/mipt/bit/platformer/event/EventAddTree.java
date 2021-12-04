package ru.mipt.bit.platformer.event;

import ru.mipt.bit.platformer.graphics.objects.LibGdxGraphicObstacle;
import ru.mipt.bit.platformer.graphics.renderers.Renderer;
import ru.mipt.bit.platformer.models.GameObject;
import ru.mipt.bit.platformer.models.logic.LogicObstacle;

/**
 * Application
 * Use case
 * */
public class EventAddTree implements Event {
    @Override
    public void performGameObjectToRenderer(Renderer renderer, GameObject gameObject) {
        renderer.getDrawablesMap().put(
                gameObject,
                new LibGdxGraphicObstacle((LogicObstacle) gameObject)
        );
    }
}
