package ru.mipt.bit.platformer.services.generator;

import ru.mipt.bit.platformer.models.storages.GameObjectStorage;

public abstract class AbstractGameObjectGenerator implements MapGenerator {
    protected final GameObjectStorage storage = new GameObjectStorage();
}
