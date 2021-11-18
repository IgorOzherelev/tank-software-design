package ru.mipt.bit.platformer.actions;

import ru.mipt.bit.platformer.commands.Command;
import ru.mipt.bit.platformer.commands.MoveCommand;
import ru.mipt.bit.platformer.commands.ShootCommand;
import ru.mipt.bit.platformer.geometry.Direction;
import ru.mipt.bit.platformer.models.Movable;
import ru.mipt.bit.platformer.managers.CollidingManager;
import ru.mipt.bit.platformer.models.logic.LogicTank;

public enum Action {
    Shoot() {
        @Override
        public Command createCommand(CollidingManager collidingManager,
                                     LogicTank logicTank) {
            return new ShootCommand(collidingManager, logicTank);
        }
    },
    MoveNorth() {
        @Override
        public Command createCommand(CollidingManager collidingManager,
                                     LogicTank logicTank) {
            return new MoveCommand(collidingManager, logicTank, Direction.UP);
        }
    },
    MoveEast() {
        @Override
        public Command createCommand(CollidingManager collidingManager,
                                     LogicTank logicTank) {
            return new MoveCommand(collidingManager, logicTank, Direction.RIGHT);
        }
    },
    MoveSouth() {
        @Override
        public Command createCommand(CollidingManager collidingManager,
                                     LogicTank logicTank) {
            return new MoveCommand(collidingManager, logicTank, Direction.DOWN);
        }
    },
    MoveWest() {
        @Override
        public Command createCommand(CollidingManager collidingManager,
                                     LogicTank logicTank) {
            return new MoveCommand(collidingManager, logicTank, Direction.LEFT);
        }
    };

    public abstract Command createCommand(CollidingManager collidingManager, LogicTank logicTank);
}
