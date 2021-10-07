package ru.mipt.bit.platformer.utils;

import com.badlogic.gdx.math.GridPoint2;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.mipt.bit.platformer.models.movable.Direction;
import ru.mipt.bit.platformer.models.movable.Tank;
import ru.mipt.bit.platformer.models.objects.GraphicObject;

import java.util.ArrayList;
import java.util.List;

import static ru.mipt.bit.platformer.util.GameGraphicObjectCollisionUtils.checkIsMoveSafe;

public class GameGraphicObjectCollisionUtilsTest {
    private final List<GraphicObject> graphicObjects = new ArrayList<>();

    @AfterEach
    public void after() {
        graphicObjects.clear();
    }

    @Test
    public void test001_checkIsMoveSafe_whenSafe_Right() {
        Tank tank = new Tank(
                new GridPoint2(3, 5), 0f);

        GraphicObject tree = new GraphicObject(
                new GridPoint2(1, 5), 0
        );

        graphicObjects.add(tree);
        Assertions.assertTrue(checkIsMoveSafe(Direction.RIGHT, graphicObjects, tank));
    }

    @Test
    public void test002_checkIsMoveSafe_whenUnSafe_Right() {
        Tank tank = new Tank(
                new GridPoint2(3, 5), 0f);

        GraphicObject tree = new GraphicObject(
                new GridPoint2(4, 5), 0
        );

        graphicObjects.add(tree);
        Assertions.assertFalse(checkIsMoveSafe(Direction.RIGHT, graphicObjects, tank));
    }

    @Test
    public void test003_checkIsMoveSafe_whenSafe_Left() {
        Tank tank = new Tank(
                new GridPoint2(3, 5), 0f);

        GraphicObject tree = new GraphicObject(
                new GridPoint2(4, 5), 0
        );

        graphicObjects.add(tree);
        Assertions.assertTrue(checkIsMoveSafe(Direction.LEFT, graphicObjects, tank));
    }

    @Test
    public void test004_checkIsMoveSafe_whenUnSafe_Left() {
        Tank tank = new Tank(
                new GridPoint2(3, 5), 0f);

        GraphicObject tree = new GraphicObject(
                new GridPoint2(2, 5), 0
        );

        graphicObjects.add(tree);
        Assertions.assertFalse(checkIsMoveSafe(Direction.LEFT, graphicObjects, tank));
    }

    @Test
    public void test005_checkIsMoveSafe_whenSafe_Up() {
        Tank tank = new Tank(
                new GridPoint2(3, 5), 0f);

        GraphicObject tree = new GraphicObject(
                new GridPoint2(3, 4), 0
        );

        graphicObjects.add(tree);
        Assertions.assertTrue(checkIsMoveSafe(Direction.UP, graphicObjects, tank));
    }

    @Test
    public void test006_checkIsMoveSafe_whenUnSafe_Up() {
        Tank tank = new Tank(
                new GridPoint2(3, 5), 0f);

        GraphicObject tree = new GraphicObject(
                new GridPoint2(3, 6), 0
        );

        graphicObjects.add(tree);
        Assertions.assertFalse(checkIsMoveSafe(Direction.UP, graphicObjects, tank));
    }

    @Test
    public void test007_checkIsMoveSafe_whenSafe_Down() {
        Tank tank = new Tank(
                new GridPoint2(3, 5), 0f);

        GraphicObject tree = new GraphicObject(
                new GridPoint2(3, 3), 0
        );

        graphicObjects.add(tree);
        Assertions.assertTrue(checkIsMoveSafe(Direction.DOWN, graphicObjects, tank));
    }

    @Test
    public void test008_checkIsMoveSafe_whenUnSafe_Down() {
        Tank tank = new Tank(
                new GridPoint2(3, 5), 0f);

        GraphicObject tree = new GraphicObject(
                new GridPoint2(3, 4), 0
        );

        graphicObjects.add(tree);
        Assertions.assertFalse(checkIsMoveSafe(Direction.DOWN, graphicObjects, tank));
    }
}
