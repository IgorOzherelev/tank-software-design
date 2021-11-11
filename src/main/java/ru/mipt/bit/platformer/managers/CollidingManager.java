package ru.mipt.bit.platformer.managers;

import ru.mipt.bit.platformer.geometry.Direction;
import ru.mipt.bit.platformer.geometry.Point;
import ru.mipt.bit.platformer.models.Colliding;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CollidingManager {
    private final int maxX, maxY;
    private final List<Colliding> collidingList = new ArrayList<>();

    public CollidingManager(int maxX, int maxY) {
        this.maxX = maxX;
        this.maxY = maxY;
    }

    public void addColliding(Colliding colliding) {
        collidingList.add(colliding);
    }

    public void addCollidings(Collection<Colliding> collidings) {
        collidingList.addAll(collidings);
    }

    public boolean isMoveSafe(Direction direction, Colliding colliding) {
        Point positionToMove = new Point(colliding.getCurrentCoordinates()).add(direction.getShift());
        if (positionToMove.getX() >= maxX || positionToMove.getX() < 0
                || positionToMove.getY() >= maxY || positionToMove.getY() < 0) {
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
