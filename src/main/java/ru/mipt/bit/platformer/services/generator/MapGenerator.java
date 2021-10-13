package ru.mipt.bit.platformer.services.generator;

import ru.mipt.bit.platformer.models.objects.GameObjectStorage;

public interface MapGenerator {
    GameObjectStorage generate();
}
