package ru.mipt.bit.platformer.level;

import ru.mipt.bit.platformer.event.EventGamePublisher;
import ru.mipt.bit.platformer.event.EventGameSubscriber;
import ru.mipt.bit.platformer.event.EventGameType;
import ru.mipt.bit.platformer.geometry.Direction;
import ru.mipt.bit.platformer.geometry.Point;
import ru.mipt.bit.platformer.models.Colliding;
import ru.mipt.bit.platformer.models.logic.LogicObstacle;
import ru.mipt.bit.platformer.models.logic.LogicTank;
import ru.mipt.bit.platformer.preferences.TexturePreferences;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Level implements EventGamePublisher {
    private final List<LogicObstacle> trees;
    private final List<LogicTank> botTanks;
    private final LogicTank playerTank;

    private final TexturePreferences texturePreferences;
    private final List<Colliding> collidingList;
    private final Map<EventGameType, List<EventGameSubscriber>> eventToSubscribers = new HashMap<>();

    public Level(List<LogicObstacle> trees, List<LogicTank> botTanks, LogicTank playerTank,
                 TexturePreferences texturePreferences) {
        this.trees = trees;
        this.botTanks = botTanks;
        this.playerTank = playerTank;
        this.texturePreferences = texturePreferences;

        this.collidingList = initCollidingList(trees, botTanks, playerTank);
    }

    private List<Colliding> initCollidingList(List<LogicObstacle> trees, List<LogicTank> botTanks, LogicTank playerTank) {
        List<Colliding> collidingList = new ArrayList<>();
        collidingList.addAll(trees);
        collidingList.addAll(botTanks);
        collidingList.add(playerTank);
        return collidingList;
    }

    public List<LogicObstacle> getLogicObstacles() {
        return trees;
    }

    public LogicTank getPlayerLogicTank() {
        return playerTank;
    }

    public List<LogicTank> getBotLogicTanks() {
        return botTanks;
    }

    public List<LogicTank> getAllLogicTanks() {
        List<LogicTank> logicTanks = new ArrayList<>(botTanks);
        logicTanks.add(playerTank);
        return logicTanks;
    }

    public void tick(float deltaTime) {
        botTanks.forEach(botTank -> botTank.tick(deltaTime));
        playerTank.tick(deltaTime);
    }

    public boolean isSafeDirection(Direction direction, Colliding colliding) {
        Point positionToMove = new Point(colliding.getCurrentCoordinates()).add(direction.getShift());
        if (positionToMove.getX() >= texturePreferences.getMapWidth() || positionToMove.getX() < 0
                || positionToMove.getY() >= texturePreferences.getMapHeight() || positionToMove.getY() < 0) {
            return false;
        }

        for (Colliding collidingElem : collidingList) {
            if (collidingElem.isCollisionPossible(positionToMove) && collidingElem != colliding) {
                return false;
            }
        }

        return true;
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
    public void notifySubscribers(EventGameType eventGameType, Colliding colliding) {
        for (Map.Entry<EventGameType, List<EventGameSubscriber>> entry : eventToSubscribers.entrySet()) {
            if (entry.getKey() == eventGameType) {
                entry.getValue().forEach(subscriber -> subscriber.update(eventGameType, colliding));
            }
        }
    }
}
