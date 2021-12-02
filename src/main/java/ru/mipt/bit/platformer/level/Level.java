package ru.mipt.bit.platformer.level;

import ru.mipt.bit.platformer.event.*;
import ru.mipt.bit.platformer.models.GameObject;
import ru.mipt.bit.platformer.models.logic.LogicBullet;
import ru.mipt.bit.platformer.models.logic.LogicObstacle;
import ru.mipt.bit.platformer.models.logic.LogicTank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Entity
 * */
public class Level implements EventPublisher {
    private static final int PLAYER_INDEX = 0;

    private final List<LogicObstacle> trees;
    private final List<LogicTank> logicTanks;
    private final List<LogicBullet> logicBullets = new ArrayList<>();

    private final Map<Class<? extends Event>, List<EventSubscriber>> eventToSubscribers = new HashMap<>();

    public Level(List<LogicObstacle> trees, List<LogicTank> logicTanks) {
        this.trees = trees;
        this.logicTanks = logicTanks;
    }

    public List<LogicObstacle> getLogicObstacles() {
        return trees;
    }

    public LogicTank getPlayerLogicTank() {
        return logicTanks.get(PLAYER_INDEX);
    }

    public List<LogicTank> getBotLogicTanks() {
        return logicTanks.subList(PLAYER_INDEX + 1, logicTanks.size());
    }

    public void handleTick(float deltaTime) {
        // примитивная защита от fail-fast
        List<LogicTank> tmpLogicTanks = new ArrayList<>(logicTanks);
        List<LogicBullet> tmpLogicBullets = new ArrayList<>(logicBullets);

        tmpLogicTanks.forEach(logicTank -> logicTank.live(deltaTime));
        tmpLogicBullets.forEach(logicBullet -> logicBullet.live(deltaTime));
    }

    public void init() {
        logicTanks.forEach(logicTank -> notifySubscribers(new EventAddTank(), logicTank));
        trees.forEach(tree -> notifySubscribers(new EventAddTree(), tree));
    }

    @Override
    public void subscribe(Event event, EventSubscriber eventSubscriber) {
        if (eventToSubscribers.get(event.getClass()) == null) {
            List<EventSubscriber> subscribers = new ArrayList<>();
            subscribers.add(eventSubscriber);

            eventToSubscribers.put(event.getClass(), subscribers);
        } else {
            eventToSubscribers.get(event.getClass()).add(eventSubscriber);
        }
    }

    @Override
    public void subscribeAll(EventSubscriber eventSubscriber) {
        subscribe(new EventAddTank(), eventSubscriber);
        subscribe(new EventAddBullet(), eventSubscriber);
        subscribe(new EventAddTree(), eventSubscriber);
        subscribe(new EventRemove(), eventSubscriber);
    }

    @Override
    public void unsubscribe(Event event, EventSubscriber eventSubscriber) {
        if (eventToSubscribers.get(event.getClass()) != null) {
            eventToSubscribers.get(event.getClass()).remove(eventSubscriber);
        }
    }

    @Override
    public void notifySubscribers(Event event, GameObject gameObject) {
        eventToSubscribers.get(event.getClass()).forEach(subscriber -> subscriber.onEvent(event, gameObject));
    }

    @Override
    public void registerEvent(Event event, GameObject gameObject) {
        // выглядит некрасиво, но можно ли по-другому?
        if (LogicBullet.class.equals(gameObject.getClass())) {
            event.performGameObjectList(logicBullets, (LogicBullet) gameObject);
        } else if (LogicTank.class.equals(gameObject.getClass())) {
            event.performGameObjectList(logicTanks, (LogicTank) gameObject);
        }
        notifySubscribers(event, gameObject);
    }
}
