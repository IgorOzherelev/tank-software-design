package ru.mipt.bit.platformer.services.generator;

import ru.mipt.bit.platformer.models.storages.GameObjectStorage;

public interface MapGenerator {
    GameObjectStorage generate();
}
