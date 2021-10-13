package ru.mipt.bit.platformer.utils;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.mipt.bit.platformer.logic.geometry.Point;
import ru.mipt.bit.platformer.models.movable.Direction;
import ru.mipt.bit.platformer.models.movable.Tank;
import ru.mipt.bit.platformer.models.objects.LibGdxGraphicObject;

import java.util.ArrayList;
import java.util.List;

import static ru.mipt.bit.platformer.util.GameGraphicObjectCollisionUtils.checkIsMoveSafe;

public class GameLibGdxGraphicObjectCollisionUtilsTest {
    private final List<LibGdxGraphicObject> libGdxGraphicObjects = new ArrayList<>();

    @AfterEach
    public void after() {
        libGdxGraphicObjects.clear();
    }

    @Test
    public void test001_checkIsMoveSafe_whenSafe_Right() {
        Tank tank = new Tank(
                new Point(3, 5), 0f);

        LibGdxGraphicObject tree = new LibGdxGraphicObject(
                new Point(1, 5), 0
        );

        libGdxGraphicObjects.add(tree);
        Assertions.assertTrue(checkIsMoveSafe(Direction.RIGHT, libGdxGraphicObjects, tank));
    }

    @Test
    public void test002_checkIsMoveSafe_whenUnSafe_Right() {
        Tank tank = new Tank(
                new Point(3, 5), 0f);

        LibGdxGraphicObject tree = new LibGdxGraphicObject(
                new Point(4, 5), 0
        );

        libGdxGraphicObjects.add(tree);
        Assertions.assertFalse(checkIsMoveSafe(Direction.RIGHT, libGdxGraphicObjects, tank));
    }

    @Test
    public void test003_checkIsMoveSafe_whenSafe_Left() {
        Tank tank = new Tank(
                new Point(3, 5), 0f);

        LibGdxGraphicObject tree = new LibGdxGraphicObject(
                new Point(4, 5), 0
        );

        libGdxGraphicObjects.add(tree);
        Assertions.assertTrue(checkIsMoveSafe(Direction.LEFT, libGdxGraphicObjects, tank));
    }

    @Test
    public void test004_checkIsMoveSafe_whenUnSafe_Left() {
        Tank tank = new Tank(
                new Point(3, 5), 0f);

        LibGdxGraphicObject tree = new LibGdxGraphicObject(
                new Point(2, 5), 0
        );

        libGdxGraphicObjects.add(tree);
        Assertions.assertFalse(checkIsMoveSafe(Direction.LEFT, libGdxGraphicObjects, tank));
    }

    @Test
    public void test005_checkIsMoveSafe_whenSafe_Up() {
        Tank tank = new Tank(
                new Point(3, 5), 0f);

        LibGdxGraphicObject tree = new LibGdxGraphicObject(
                new Point(3, 4), 0
        );

        libGdxGraphicObjects.add(tree);
        Assertions.assertTrue(checkIsMoveSafe(Direction.UP, libGdxGraphicObjects, tank));
    }

    @Test
    public void test006_checkIsMoveSafe_whenUnSafe_Up() {
        Tank tank = new Tank(
                new Point(3, 5), 0f);

        LibGdxGraphicObject tree = new LibGdxGraphicObject(
                new Point(3, 6), 0
        );

        libGdxGraphicObjects.add(tree);
        Assertions.assertFalse(checkIsMoveSafe(Direction.UP, libGdxGraphicObjects, tank));
    }

    @Test
    public void test007_checkIsMoveSafe_whenSafe_Down() {
        Tank tank = new Tank(
                new Point(3, 5), 0f);

        LibGdxGraphicObject tree = new LibGdxGraphicObject(
                new Point(3, 3), 0
        );

        libGdxGraphicObjects.add(tree);
        Assertions.assertTrue(checkIsMoveSafe(Direction.DOWN, libGdxGraphicObjects, tank));
    }

    @Test
    public void test008_checkIsMoveSafe_whenUnSafe_Down() {
        Tank tank = new Tank(
                new Point(3, 5), 0f);

        LibGdxGraphicObject tree = new LibGdxGraphicObject(
                new Point(3, 4), 0
        );

        libGdxGraphicObjects.add(tree);
        Assertions.assertFalse(checkIsMoveSafe(Direction.DOWN, libGdxGraphicObjects, tank));
    }
}
