package ru.mipt.bit.platformer.managers;

import ru.mipt.bit.platformer.event.EventSubscriber;
import ru.mipt.bit.platformer.event.Event;
import ru.mipt.bit.platformer.geometry.Direction;
import ru.mipt.bit.platformer.geometry.Point;
import ru.mipt.bit.platformer.level.Level;
import ru.mipt.bit.platformer.models.GameObject;
import ru.mipt.bit.platformer.models.logic.BaseLogicObject;
import ru.mipt.bit.platformer.models.logic.LogicBullet;
import ru.mipt.bit.platformer.preferences.TexturePreferences;

import java.util.ArrayList;
import java.util.List;

public class CollidingLogicManager implements EventSubscriber {
    private final List<BaseLogicObject> logicObjects = new ArrayList<>();
    private final TexturePreferences texturePreferences;

    public CollidingLogicManager(Level level, TexturePreferences texturePreferences) {
        this.texturePreferences = texturePreferences;

        level.subscribeAll(this);
    }

    public boolean isSafeDirection(Direction direction, BaseLogicObject logicObject) {
        Point positionToMove = new Point(logicObject.getCurrentCoordinates()).add(direction.getShift());
        if (isBorderUnsafe(positionToMove)) {
            return false;
        }

        for (BaseLogicObject logicObjectElem : logicObjects) {
            if (logicObjectElem.isCollisionPossible(positionToMove) && logicObjectElem != logicObject) {
                performCollisionDamageRegistration(logicObject, logicObjectElem);

                return false;
            }
        }

        return true;
    }

    private boolean isBorderUnsafe(Point positionToMove) {
        return positionToMove.getX() >= texturePreferences.getMapWidth() || positionToMove.getX() < 0
                || positionToMove.getY() >= texturePreferences.getMapHeight() || positionToMove.getY() < 0;
    }

    private void performCollisionDamageRegistration(BaseLogicObject colliding, BaseLogicObject collidingElem) {
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
        event.performGameObjectList(logicObjects, (BaseLogicObject) gameObject);
    }
}
