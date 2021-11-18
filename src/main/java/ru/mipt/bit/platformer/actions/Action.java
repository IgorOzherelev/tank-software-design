package ru.mipt.bit.platformer.actions;

import ru.mipt.bit.platformer.commands.Command;
import ru.mipt.bit.platformer.commands.MoveCommand;
import ru.mipt.bit.platformer.commands.ShootCommand;
import ru.mipt.bit.platformer.geometry.Direction;
import ru.mipt.bit.platformer.level.Level;
import ru.mipt.bit.platformer.models.logic.LogicTank;

public enum Action {
    Shoot() {
        @Override
        public Command createCommand(Level level,
                                     LogicTank logicTank) {
            return new ShootCommand(level, logicTank);
        }
    },
    MoveNorth() {
        @Override
        public Command createCommand(Level level,
                                     LogicTank logicTank) {
            return new MoveCommand(level, logicTank, Direction.UP);
        }
    },
    MoveEast() {
        @Override
        public Command createCommand(Level level,
                                     LogicTank logicTank) {
            return new MoveCommand(level, logicTank, Direction.RIGHT);
        }
    },
    MoveSouth() {
        @Override
        public Command createCommand(Level level,
                                     LogicTank logicTank) {
            return new MoveCommand(level, logicTank, Direction.DOWN);
        }
    },
    MoveWest() {
        @Override
        public Command createCommand(Level level,
                                     LogicTank logicTank) {
            return new MoveCommand(level, logicTank, Direction.LEFT);
        }
    };

    public abstract Command createCommand(Level level, LogicTank logicTank);
}
