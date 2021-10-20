package ru.mipt.bit.platformer.models.movable;

import ru.mipt.bit.platformer.logic.geometry.Point;

public interface Movable {
    float continueProgress(float previousProgress, float deltaTime, float speed);
    void prepareForMove(Direction direction);
    void move(float deltaTime);
    boolean isStopped();
    Point getCurrentCoordinates();
    Point getDestinationCoordinates();
    float getMovementProgress();
}
