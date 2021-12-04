package ru.mipt.bit.platformer.models;

import ru.mipt.bit.platformer.geometry.Direction;

/**
 * Entity
 * Domain
 * */
public interface Movable extends Colliding {
    default boolean isStopped() {
        return false;
    }

    default void move(Direction direction) {}
}
