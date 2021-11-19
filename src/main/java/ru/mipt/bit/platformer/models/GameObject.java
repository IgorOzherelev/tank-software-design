package ru.mipt.bit.platformer.models;

public interface GameObject extends Movable {
    boolean isAlive();
    void live(float deltaTime);
}
