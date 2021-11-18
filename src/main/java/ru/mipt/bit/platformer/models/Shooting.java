package ru.mipt.bit.platformer.models;

import ru.mipt.bit.platformer.managers.CollidingManager;

public interface Shooting {
    void shoot(CollidingManager collidingManager);
}
