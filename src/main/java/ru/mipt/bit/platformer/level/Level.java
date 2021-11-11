package ru.mipt.bit.platformer.level;

import ru.mipt.bit.platformer.models.logic.LogicObstacle;
import ru.mipt.bit.platformer.models.logic.LogicTank;

import java.util.List;

public class Level {
    private final List<LogicObstacle> trees;
    private final List<LogicTank> botTanks;
    private final LogicTank playerTank;

    public Level(List<LogicObstacle> trees, List<LogicTank> botTanks, LogicTank playerTank) {
        this.trees = trees;
        this.botTanks = botTanks;
        this.playerTank = playerTank;
    }

    public void tick(float deltaTime) {
        botTanks.forEach(botTank -> botTank.tick(deltaTime));
        playerTank.tick(deltaTime);
    }

    public List<LogicObstacle> getLogicObstacles() {
        return trees;
    }

    public LogicTank getPlayerLogicTank() {
        return playerTank;
    }

    public List<LogicTank> getBotLogicTanks() {
        return botTanks;
    }
}
