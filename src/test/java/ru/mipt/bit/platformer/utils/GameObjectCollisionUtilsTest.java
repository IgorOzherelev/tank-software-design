package ru.mipt.bit.platformer.utils;

import org.junit.jupiter.api.*;
import ru.mipt.bit.platformer.logic.geometry.Point;
import ru.mipt.bit.platformer.models.movable.Direction;
import ru.mipt.bit.platformer.models.movable.LibGdxTank;
import ru.mipt.bit.platformer.models.objects.GameObject;
import ru.mipt.bit.platformer.models.storages.GameObjectStorage;

import java.util.ArrayList;
import java.util.List;

import static ru.mipt.bit.platformer.util.GameObjectCollisionUtils.checkIsNonCollidingMove;

public class GameObjectCollisionUtilsTest {
    private final GameObjectStorage storage = new GameObjectStorage();
    private final List<GameObject> tanks = new ArrayList<>();
    GameObject tank = new GameObject(new Point(3, 5), 0f);
    GameObject playerObject = new GameObject(new Point(0, 0), 0f);
    LibGdxTank libGdxTank = new LibGdxTank(tank);

    @BeforeEach
    public void before() {
        storage.setPlayerGameObject(playerObject);
        tanks.add(tank);
        storage.setTanks(tanks);
    }

    @AfterEach
    public void after() {
        storage.getTanks().clear();
        storage.getTrees().clear();
    }

    @Test
    public void test001_checkIsMoveSafe_whenSafe_Right() {
        GameObject tree = new GameObject(
                new Point(1, 5), 0
        );

        List<GameObject> trees = new ArrayList<>();
        trees.add(tree);

        storage.setTrees(trees);
        Assertions.assertTrue(checkIsNonCollidingMove(Direction.RIGHT, storage, libGdxTank));
    }

    @Test
    public void test002_checkIsMoveSafe_whenUnSafe_Right() {
        GameObject tree = new GameObject(
                new Point(4, 5), 0
        );

        List<GameObject> trees = new ArrayList<>();
        trees.add(tree);

        storage.setTrees(trees);
        Assertions.assertFalse(checkIsNonCollidingMove(Direction.RIGHT, storage, libGdxTank));
    }

    @Test
    public void test003_checkIsMoveSafe_whenSafe_Left() {
        GameObject tree = new GameObject(
                new Point(4, 5), 0
        );

        List<GameObject> trees = new ArrayList<>();
        trees.add(tree);

        storage.setTrees(trees);
        Assertions.assertTrue(checkIsNonCollidingMove(Direction.LEFT, storage, libGdxTank));
    }

    @Test
    public void test004_checkIsMoveSafe_whenUnSafe_Left() {
        GameObject tree = new GameObject(
                new Point(2, 5), 0
        );

        List<GameObject> trees = new ArrayList<>();
        trees.add(tree);

        storage.setTrees(trees);
        Assertions.assertFalse(checkIsNonCollidingMove(Direction.LEFT, storage, libGdxTank));
    }

    @Test
    public void test005_checkIsMoveSafe_whenSafe_Up() {
        GameObject tree = new GameObject(
                new Point(3, 4), 0
        );

        List<GameObject> trees = new ArrayList<>();
        trees.add(tree);

        storage.setTrees(trees);
        Assertions.assertTrue(checkIsNonCollidingMove(Direction.UP, storage, libGdxTank));
    }

    @Test
    public void test006_checkIsMoveSafe_whenUnSafe_Up() {
        GameObject tree = new GameObject(
                new Point(3, 6), 0
        );

        List<GameObject> trees = new ArrayList<>();
        trees.add(tree);

        storage.setTrees(trees);
        Assertions.assertFalse(checkIsNonCollidingMove(Direction.UP, storage, libGdxTank));
    }

    @Test
    public void test007_checkIsMoveSafe_whenSafe_Down() {
        GameObject tree = new GameObject(
                new Point(3, 3), 0
        );

        List<GameObject> trees = new ArrayList<>();
        trees.add(tree);

        storage.setTrees(trees);
        Assertions.assertTrue(checkIsNonCollidingMove(Direction.DOWN, storage, libGdxTank));
    }

    @Test
    public void test008_checkIsMoveSafe_whenUnSafe_Down() {
        GameObject tree = new GameObject(
                new Point(3, 4), 0
        );

        List<GameObject> trees = new ArrayList<>();
        trees.add(tree);

        storage.setTrees(trees);
        Assertions.assertFalse(checkIsNonCollidingMove(Direction.DOWN, storage, libGdxTank));
    }
}
