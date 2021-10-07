package ru.mipt.bit.platformer;

import com.badlogic.gdx.math.GridPoint2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.mipt.bit.platformer.models.movable.Direction;
import ru.mipt.bit.platformer.models.movable.Tank;

public class TankMoveTest {
    Tank tank = new Tank(
            new GridPoint2(3, 5), 0f);

    @Test
    public void test001_TankMoveTest_Right() {
        tank.prepareForMove(Direction.RIGHT);
        tank.move(1);
        Assertions.assertEquals(tank.getCurrentCoordinates(), new GridPoint2(4, 5));
        Assertions.assertEquals(tank.getMovementProgress(), 1f);
        Assertions.assertEquals(tank.getRotation(), Direction.RIGHT.getRotation());
    }

    @Test
    public void test002_TankMoveTest_Left() {
        tank.prepareForMove(Direction.LEFT);
        tank.move(1);
        Assertions.assertEquals(tank.getCurrentCoordinates(), new GridPoint2(2, 5));
        Assertions.assertEquals(tank.getMovementProgress(), 1f);
        Assertions.assertEquals(tank.getRotation(), Direction.LEFT.getRotation());
    }

    @Test
    public void test003_TankMoveTest_Up() {
        tank.prepareForMove(Direction.UP);
        tank.move(1);
        Assertions.assertEquals(tank.getCurrentCoordinates(), new GridPoint2(3, 6));
        Assertions.assertEquals(tank.getMovementProgress(), 1f);
        Assertions.assertEquals(tank.getRotation(), Direction.UP.getRotation());
    }

    @Test
    public void test004_TankMoveTest_Down() {
        tank.prepareForMove(Direction.DOWN);
        tank.move(1);
        Assertions.assertEquals(tank.getCurrentCoordinates(), new GridPoint2(3, 4));
        Assertions.assertEquals(tank.getMovementProgress(), 1f);
        Assertions.assertEquals(tank.getRotation(), Direction.DOWN.getRotation());
    }
}
