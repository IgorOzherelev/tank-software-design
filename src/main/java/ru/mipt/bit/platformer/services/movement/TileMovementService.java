package ru.mipt.bit.platformer.services.movement;

import ru.mipt.bit.platformer.models.movable.Movable;

public interface TileMovementService {
    void updateMovableGameObjectRectangle(Movable movingGraphicObject);
}
