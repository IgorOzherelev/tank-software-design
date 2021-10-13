package ru.mipt.bit.platformer.models.movable;

public interface Movable {
    float continueProgress(float previousProgress, float deltaTime, float speed);
    void prepareForMove(Direction direction);
    void move(float deltaTime);
    boolean isStopped();
}
