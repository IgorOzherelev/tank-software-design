package ru.mipt.bit.platformer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.mipt.bit.platformer.logic.geometry.Point;
import ru.mipt.bit.platformer.models.movable.Direction;
import ru.mipt.bit.platformer.models.movable.LibGdxTank;
import ru.mipt.bit.platformer.models.objects.GameObject;

public class LibGdxTankMoveTest {
    LibGdxTank libGdxTank = new LibGdxTank(
            new GameObject(new Point(3, 5), 0f));

    @Test
    public void test001_TankMoveTest_Right() {
        libGdxTank.prepareForMove(Direction.RIGHT);
        libGdxTank.move(1);
        Assertions.assertEquals(libGdxTank.getCurrentCoordinates(), new Point(4, 5));
        Assertions.assertEquals(libGdxTank.getMovementProgress(), 1f);
        Assertions.assertEquals(libGdxTank.getRotation(), Direction.RIGHT.getRotation());
    }

    @Test
    public void test002_TankMoveTest_Left() {
        libGdxTank.prepareForMove(Direction.LEFT);
        libGdxTank.move(1);
        Assertions.assertEquals(libGdxTank.getCurrentCoordinates(), new Point(2, 5));
        Assertions.assertEquals(libGdxTank.getMovementProgress(), 1f);
        Assertions.assertEquals(libGdxTank.getRotation(), Direction.LEFT.getRotation());
    }

    @Test
    public void test003_TankMoveTest_Up() {
        libGdxTank.prepareForMove(Direction.UP);
        libGdxTank.move(1);
        Assertions.assertEquals(libGdxTank.getCurrentCoordinates(), new Point(3, 6));
        Assertions.assertEquals(libGdxTank.getMovementProgress(), 1f);
        Assertions.assertEquals(libGdxTank.getRotation(), Direction.UP.getRotation());
    }

    @Test
    public void test004_TankMoveTest_Down() {
        libGdxTank.prepareForMove(Direction.DOWN);
        libGdxTank.move(1);
        Assertions.assertEquals(libGdxTank.getCurrentCoordinates(), new Point(3, 4));
        Assertions.assertEquals(libGdxTank.getMovementProgress(), 1f);
        Assertions.assertEquals(libGdxTank.getRotation(), Direction.DOWN.getRotation());
    }
}
