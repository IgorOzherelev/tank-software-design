package ru.mipt.bit.platformer.event;

import ru.mipt.bit.platformer.graphics.objects.LibGdxGraphicBullet;
import ru.mipt.bit.platformer.graphics.renderers.Renderer;
import ru.mipt.bit.platformer.models.GameObject;
import ru.mipt.bit.platformer.models.logic.LogicBullet;

/**
 * Application
 * Use case
 * */
public class EventAddBullet implements Event {
    @Override
    public void performGameObjectToRenderer(Renderer renderer, GameObject gameObject) {
        renderer.getDrawablesMap().put(
                gameObject,
                new LibGdxGraphicBullet((LogicBullet) gameObject)
        );
    }
}
