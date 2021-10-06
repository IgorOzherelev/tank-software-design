package ru.mipt.bit.platformer.services.movement;

import ru.mipt.bit.platformer.models.movable.AbstractMovableGraphicObject;

public interface TileMovementService {
    void updateMovableGameObjectCoordinates(AbstractMovableGraphicObject movingGraphicObject);
}
