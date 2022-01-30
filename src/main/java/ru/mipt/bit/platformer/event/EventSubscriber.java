package ru.mipt.bit.platformer.event;

import ru.mipt.bit.platformer.models.GameObject;

/**
 * Application
 * Use case
 * */
public interface EventSubscriber {
    void onEvent(Event event, GameObject gameObject);
}
