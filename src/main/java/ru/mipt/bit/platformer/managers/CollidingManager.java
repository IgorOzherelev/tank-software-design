package ru.mipt.bit.platformer.managers;

import ru.mipt.bit.platformer.event.EventSubscriber;
import ru.mipt.bit.platformer.event.Event;
import ru.mipt.bit.platformer.geometry.Direction;
import ru.mipt.bit.platformer.geometry.Point;
import ru.mipt.bit.platformer.level.Level;
import ru.mipt.bit.platformer.models.Colliding;
import ru.mipt.bit.platformer.models.GameObject;
import ru.mipt.bit.platformer.models.logic.LogicBullet;
import ru.mipt.bit.platformer.preferences.TexturePreferences;

import java.util.ArrayList;
import java.util.List;

public class CollidingManager implements EventSubscriber {
    private final List<Colliding> collidingList = new ArrayList<>();
    private final TexturePreferences texturePreferences;

    public CollidingManager(Level level, TexturePreferences texturePreferences) {
        this.texturePreferences = texturePreferences;

        level.subscribeAll(this);
    }

    public boolean isSafeDirection(Direction direction, Colliding colliding) {
        Point positionToMove = new Point(colliding.getCurrentCoordinates()).add(direction.getShift());
        if (positionToMove.getX() >= texturePreferences.getMapWidth() || positionToMove.getX() < 0
                || positionToMove.getY() >= texturePreferences.getMapHeight() || positionToMove.getY() < 0) {
            return false;
        }

        for (Colliding collidingElem : collidingList) {
            if (collidingElem.isCollisionPossible(positionToMove) && collidingElem != colliding) {
                performCollisionDamageRegistration(colliding, collidingElem);

                return false;
            }
        }

        return true;
    }

    private void performCollisionDamageRegistration(Colliding colliding, Colliding collidingElem) {
        // соглашусь, что мб некрасиво, но как переделать?
        if (colliding instanceof LogicBullet) {
            collidingElem.registerCollisionDamage(colliding.getCollisionDamage());
        } else if (collidingElem instanceof LogicBullet) {
            colliding.registerCollisionDamage(collidingElem.getCollisionDamage());
        } else {
            colliding.registerCollisionDamage(collidingElem.getCollisionDamage());
            collidingElem.registerCollisionDamage(colliding.getCollisionDamage());
        }
    }

    @Override
    public void onEvent(Event event, GameObject gameObject) {
        event.performCollidingList(collidingList, gameObject);
    }
}
