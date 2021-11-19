package ru.mipt.bit.platformer.managers;

import ru.mipt.bit.platformer.geometry.Direction;
import ru.mipt.bit.platformer.geometry.Point;
import ru.mipt.bit.platformer.level.Level;
import ru.mipt.bit.platformer.models.Colliding;
import ru.mipt.bit.platformer.preferences.TexturePreferences;

import java.util.ArrayList;
import java.util.List;

public class CollidingManager {
    private final List<Colliding> collidingList = new ArrayList<>();
    private final Level level;
    private final TexturePreferences texturePreferences;

    public CollidingManager(Level level, TexturePreferences texturePreferences) {
        this.level = level;
        this.texturePreferences = texturePreferences;
    }

    public void init() {
        collidingList.addAll(level.getLogicObstacles());
        collidingList.addAll(level.getAllLogicTanks());
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
}
