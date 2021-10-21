package ru.mipt.bit.platformer.services.colliding;

import ru.mipt.bit.platformer.logic.geometry.Point;
import ru.mipt.bit.platformer.models.colliding.Colliding;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CollidingManagerService {
    private final int maxX, maxY;
    private final List<Colliding> collidingList = new ArrayList<>();

    public CollidingManagerService(int maxX, int maxY) {
        this.maxX = maxX;
        this.maxY = maxY;
    }

    public void addColliding(Colliding colliding) {
        collidingList.add(colliding);
    }

    public void addCollidings(Collection<Colliding> collidings) {
        collidingList.addAll(collidings);
    }

    public boolean isMoveSafe(Point positionToMove, Colliding colliding) {
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
