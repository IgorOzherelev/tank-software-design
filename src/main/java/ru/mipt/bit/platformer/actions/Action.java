package ru.mipt.bit.platformer.actions;

import ru.mipt.bit.platformer.commands.Command;
import ru.mipt.bit.platformer.commands.MoveCommand;
import ru.mipt.bit.platformer.geometry.Direction;
import ru.mipt.bit.platformer.models.Movable;
import ru.mipt.bit.platformer.managers.CollidingManager;

public enum Action {
    MoveNorth() {
        @Override
        public Command createCommand(CollidingManager collidingManager,
                                     Movable movable) {
            return new MoveCommand(collidingManager, movable, Direction.UP);
        }
    },
    MoveEast() {
        @Override
        public Command createCommand(CollidingManager collidingManager,
                                     Movable movable) {
            return new MoveCommand(collidingManager, movable, Direction.RIGHT);
        }
    },
    MoveSouth() {
        @Override
        public Command createCommand(CollidingManager collidingManager,
                                     Movable movable) {
            return new MoveCommand(collidingManager, movable, Direction.DOWN);
        }
    },
    MoveWest() {
        @Override
        public Command createCommand(CollidingManager collidingManager,
                                     Movable movable) {
            return new MoveCommand(collidingManager, movable, Direction.LEFT);
        }
    };

    public abstract Command createCommand(CollidingManager collidingManager, Movable movable);
}
