package ru.mipt.bit.platformer.services.movement;

import ru.mipt.bit.platformer.models.movable.AbstractMovableObject;

public interface TileMovementService {
    void updateMovableGameObjectRectangle(AbstractMovableObject movingGraphicObject);
}
