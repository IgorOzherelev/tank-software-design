package ru.mipt.bit.platformer.event;

import ru.mipt.bit.platformer.models.GameObject;

public interface EventSubscriber {
    void onEvent(Event event, GameObject gameObject);
}
