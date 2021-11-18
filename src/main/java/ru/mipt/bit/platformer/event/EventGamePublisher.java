package ru.mipt.bit.platformer.event;

import ru.mipt.bit.platformer.models.Colliding;

public interface EventGamePublisher {
    void subscribe(EventGameType eventGameType, EventGameSubscriber eventGameSubscriber);

    void unsubscribe(EventGameType eventGameType, EventGameSubscriber eventGameSubscriber);

    void notifySubscribers(EventGameType eventGameType, Colliding colliding);
}
