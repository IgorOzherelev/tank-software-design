package ru.mipt.bit.platformer.models;

import static com.badlogic.gdx.math.MathUtils.clamp;

/**
 * Entity
 * */
public interface GameObject extends Movable {
    float MAX_PROGRESS = 1f;
    float MIN_PROGRESS = 0f;

    default void live(float deltaTime) {}

    default float continueProgress(float previousProgress, float deltaTime, float speed) {
        return clamp(previousProgress + deltaTime * speed, MIN_PROGRESS, MAX_PROGRESS);
    }
}
