package ru.mipt.bit.platformer.services.ai;

import ru.mipt.bit.platformer.models.commands.Command;
import ru.mipt.bit.platformer.models.commands.MoveCommand;
import ru.mipt.bit.platformer.models.movable.Direction;
import ru.mipt.bit.platformer.models.movable.Movable;
import ru.mipt.bit.platformer.services.colliding.CollidingManagerService;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CustomRandomAiService implements CustomAiService {
    private final Random random = new Random();

    private final List<? extends Movable> movables;
    private final CollidingManagerService collidingManagerService;
    private float deltaTime;

    public CustomRandomAiService(List<? extends Movable> movables,
                                 CollidingManagerService collidingManagerService) {
        this.movables = movables;
        this.collidingManagerService = collidingManagerService;
    }

    @Override
    public void setDeltaTime(float deltaTime) {
        this.deltaTime = deltaTime;
    }

    @Override
    public List<? extends Command> recommend() {
        return recommendMoveCommands();
    }

    public List<? extends Command> recommendMoveCommands() {
        Direction randomDirection = chooseRandomDirection();
        List<MoveCommand> moveCommands = new ArrayList<>();

        for (Movable movable : movables) {
            moveCommands.add(new MoveCommand(collidingManagerService, movable, randomDirection, deltaTime));
        }

        return moveCommands;
    }

    public Direction chooseRandomDirection() {
        Direction[] directions = Direction.values();
        int directionIndex = random.nextInt(Direction.values().length);

        return  directions[directionIndex];
    }
}
