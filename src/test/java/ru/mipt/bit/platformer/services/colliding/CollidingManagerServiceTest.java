package ru.mipt.bit.platformer.services.colliding;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.mipt.bit.platformer.geometry.Point;
import ru.mipt.bit.platformer.models.colliding.Colliding;
import ru.mipt.bit.platformer.models.movable.Direction;
import ru.mipt.bit.platformer.models.objects.GameObject;

import java.util.*;

public class CollidingManagerServiceTest {
    private final static CollidingManagerService collidingManagerService = new CollidingManagerService(10, 8);
    private final static GameObject collidingObjectToCheck = new GameObject(new Point(5, 5), 0f);

    @BeforeAll
    public static void beforeAll() {
        List<Colliding> collidingList = new ArrayList<>();
        collidingList.add(collidingObjectToCheck);
        collidingList.add(new GameObject(new Point(5, 6), 0f));
        collidingList.add(new GameObject(new Point(6, 5), 0f));
        collidingList.add(new GameObject(new Point(4, 5), 0f));
        collidingList.add(new GameObject(new Point(5, 4), 0f));

        collidingManagerService.addCollidings(collidingList);
    }

    @Test
    public void testMoveDown_negative() {
        Assertions.assertFalse(collidingManagerService.isMoveSafe(Direction.DOWN.getShift().add(collidingObjectToCheck.getCoordinates()), collidingObjectToCheck));
    }

    @Test
    public void testMoveUp_negative() {
        Assertions.assertFalse(collidingManagerService.isMoveSafe(Direction.UP.getShift().add(collidingObjectToCheck.getCoordinates()), collidingObjectToCheck));
    }

    @Test
    public void testMoveLeft_negative() {
        Assertions.assertFalse(collidingManagerService.isMoveSafe(Direction.LEFT.getShift().add(collidingObjectToCheck.getCoordinates()), collidingObjectToCheck));
    }

    @Test
    public void testMoveRight_negative() {
        Assertions.assertFalse(collidingManagerService.isMoveSafe(Direction.RIGHT.getShift().add(collidingObjectToCheck.getCoordinates()), collidingObjectToCheck));
    }

    @Test
    public void test_positive() {
        Assertions.assertTrue(collidingManagerService.isMoveSafe(new Point(0, 0), collidingObjectToCheck));
    }
}
