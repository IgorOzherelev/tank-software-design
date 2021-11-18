package ru.mipt.bit.platformer.event;

import ru.mipt.bit.platformer.models.Colliding;

public interface EventGameSubscriber {
    void update(EventGameType eventGameType, Colliding colliding);
}
