package ru.mipt.bit.platformer.models;

public interface GameObject extends Movable {
    default boolean isAlive() {
        return true;
    }

    default void live(float deltaTime) {}
}
