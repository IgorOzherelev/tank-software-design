package ru.mipt.bit.platformer.level;

import ru.mipt.bit.platformer.event.EventGamePublisher;
import ru.mipt.bit.platformer.event.EventGameSubscriber;
import ru.mipt.bit.platformer.event.EventGameType;
import ru.mipt.bit.platformer.models.GameObject;
import ru.mipt.bit.platformer.models.logic.LogicObstacle;
import ru.mipt.bit.platformer.models.logic.LogicTank;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Level implements EventGamePublisher {
    private static int PLAYER_INDEX = 0;
    private final List<LogicObstacle> trees;
    private final List<LogicTank> logicTanks;

    private final Map<EventGameType, List<EventGameSubscriber>> eventToSubscribers = new HashMap<>();

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

    public List<LogicTank> getAllLogicTanks() {
        return logicTanks;
    }

    public void handleTick(float deltaTime) {
        logicTanks.forEach(logicTank -> logicTank.live(deltaTime));
    }

    @Override
    public void subscribe(EventGameType eventGameType, EventGameSubscriber eventGameSubscriber) {
       eventToSubscribers.get(eventGameType).add(eventGameSubscriber);
    }

    @Override
    public void unsubscribe(EventGameType eventGameType, EventGameSubscriber eventGameSubscriber) {
        eventToSubscribers.get(eventGameType).remove(eventGameSubscriber);
    }

    @Override
    public void notifySubscribers(EventGameType eventGameType, GameObject gameObject) {
        for (Map.Entry<EventGameType, List<EventGameSubscriber>> entry : eventToSubscribers.entrySet()) {
            if (entry.getKey() == eventGameType) {
                entry.getValue().forEach(subscriber -> subscriber.onEvent(eventGameType, gameObject));
            }
        }
    }
}
