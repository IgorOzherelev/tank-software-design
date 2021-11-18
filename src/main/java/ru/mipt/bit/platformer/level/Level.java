package ru.mipt.bit.platformer.level;

import ru.mipt.bit.platformer.geometry.Direction;
import ru.mipt.bit.platformer.geometry.Point;
import ru.mipt.bit.platformer.models.Colliding;
import ru.mipt.bit.platformer.models.logic.LogicObstacle;
import ru.mipt.bit.platformer.models.logic.LogicTank;
import ru.mipt.bit.platformer.preferences.TexturePreferences;

import java.util.ArrayList;
import java.util.List;

public class Level {
    private final List<LogicObstacle> trees;
    private final List<LogicTank> botTanks;
    private final LogicTank playerTank;

    private final TexturePreferences texturePreferences;
    private final List<Colliding> collidingList;

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

    public void tick(float deltaTime) {
        botTanks.forEach(botTank -> botTank.tick(deltaTime));
        playerTank.tick(deltaTime);
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

    public boolean isMoveSafe(Direction direction, Colliding colliding) {
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
}
