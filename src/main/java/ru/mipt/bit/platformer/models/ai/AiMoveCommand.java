package ru.mipt.bit.platformer.models.ai;

import ru.mipt.bit.platformer.models.movable.Direction;
import ru.mipt.bit.platformer.models.movable.Movable;
import ru.mipt.bit.platformer.models.storages.GameObjectStorage;
import ru.mipt.bit.platformer.preferences.TexturePreferences;

import static ru.mipt.bit.platformer.util.GameObjectCollisionUtils.checkIsMoveOutOfMapBounds;
import static ru.mipt.bit.platformer.util.GameObjectCollisionUtils.checkIsNonCollidingMove;

public class AiMoveCommand implements AiCommand {
    private final Movable movable;
    private final Direction direction;

    private final GameObjectStorage storage;
    private final TexturePreferences preferences;
    private final float deltaTime;

    public AiMoveCommand(Movable movable, Direction direction,
                         GameObjectStorage storage, TexturePreferences preferences,
                         float deltaTime) {
        this.movable = movable;
        this.direction = direction;
        this.storage = storage;
        this.preferences = preferences;
        this.deltaTime = deltaTime;
    }

    @Override
    public void execute() {
        if (movable.isStopped()) {
            if (direction != null && checkIsNonCollidingMove(direction, storage, movable)
                    && checkIsMoveOutOfMapBounds(direction, movable, preferences)) {
                movable.prepareForMove(direction);
            }
        }

        movable.move(deltaTime);
    }
}
