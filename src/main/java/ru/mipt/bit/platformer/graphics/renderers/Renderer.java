package ru.mipt.bit.platformer.graphics.renderers;

import ru.mipt.bit.platformer.event.EventSubscriber;

/**
 * Entity
 * */
public interface Renderer extends EventSubscriber {
    void render();
    void dispose();
}
