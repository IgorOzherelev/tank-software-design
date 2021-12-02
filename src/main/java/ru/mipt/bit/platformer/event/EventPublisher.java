package ru.mipt.bit.platformer.event;

import ru.mipt.bit.platformer.models.GameObject;

/**
 * Entity
 * */
public interface EventPublisher {
    void subscribe(Event event, EventSubscriber eventSubscriber);

    void subscribeAll(EventSubscriber eventSubscriber);

    void unsubscribe(Event event, EventSubscriber eventSubscriber);

    void notifySubscribers(Event event, GameObject gameObject);

    void registerEvent(Event event, GameObject gameObject);
}
