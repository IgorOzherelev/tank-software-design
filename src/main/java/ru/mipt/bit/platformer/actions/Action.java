package ru.mipt.bit.platformer.actions;

import ru.mipt.bit.platformer.commands.Command;
import ru.mipt.bit.platformer.commands.MoveCommand;
import ru.mipt.bit.platformer.commands.ShootCommand;
import ru.mipt.bit.platformer.geometry.Direction;
import ru.mipt.bit.platformer.models.logic.LogicTank;

public enum Action {
    Shoot() {
        @Override
        public Command createCommand(LogicTank logicTank) {
            return new ShootCommand(logicTank);
        }
    },
    MoveNorth() {
        @Override
        public Command createCommand(LogicTank logicTank) {
            return new MoveCommand(logicTank, Direction.UP);
        }
    },
    MoveEast() {
        @Override
        public Command createCommand(LogicTank logicTank) {
            return new MoveCommand(logicTank, Direction.RIGHT);
        }
    },
    MoveSouth() {
        @Override
        public Command createCommand(LogicTank logicTank) {
            return new MoveCommand(logicTank, Direction.DOWN);
        }
    },
    MoveWest() {
        @Override
        public Command createCommand(LogicTank logicTank) {
            return new MoveCommand(logicTank, Direction.LEFT);
        }
    };

    public abstract Command createCommand(LogicTank logicTank);
}
