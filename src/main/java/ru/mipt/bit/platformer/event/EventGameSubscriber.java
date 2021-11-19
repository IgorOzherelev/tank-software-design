package ru.mipt.bit.platformer.event;

import ru.mipt.bit.platformer.models.GameObject;

public interface EventGameSubscriber {
    void onEvent(EventGameType eventGameType, GameObject gameObject);
}
