package ru.mipt.bit.platformer.models;

import ru.mipt.bit.platformer.geometry.Direction;
import ru.mipt.bit.platformer.managers.CollidingManager;

import static com.badlogic.gdx.math.MathUtils.clamp;

public interface Movable extends Colliding {
    float MAX_PROGRESS = 1f;
    float MIN_PROGRESS = 0f;

    boolean isStopped();
    void move(Direction direction, CollidingManager collidingManager);
    void move(CollidingManager collidingManager);

    default float continueProgress(float previousProgress, float deltaTime, float speed) {
        return clamp(previousProgress + deltaTime / speed, MIN_PROGRESS, MAX_PROGRESS);
    }
}
