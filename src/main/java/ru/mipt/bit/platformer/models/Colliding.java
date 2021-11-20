package ru.mipt.bit.platformer.models;

import ru.mipt.bit.platformer.geometry.Point;

public interface Colliding {
    /**
     * Метод занимается регистрацией полученного урона объектом
     * */
    default void registerCollisionDamage(int collisionDamage) {}

    /**
     * Метод занимается регистрацией полученного урона объектом
     * */
    default void registerCollisionDamage() {}

    /**
     * Проверка на наличие коллизии
     * */
    boolean isCollisionPossible(Point point);

    Point getCurrentCoordinates();

    /**
     * Возвращает урон, который нанесет этот объект другому
     * */
    default int getCollisionDamage() {
        return 0;
    }
}
