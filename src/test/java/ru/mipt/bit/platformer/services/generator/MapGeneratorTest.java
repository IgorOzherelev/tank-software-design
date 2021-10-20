package ru.mipt.bit.platformer.services.generator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.mipt.bit.platformer.logic.geometry.Point;
import ru.mipt.bit.platformer.models.objects.GameObject;
import ru.mipt.bit.platformer.models.storages.GameObjectStorage;
import ru.mipt.bit.platformer.preferences.TexturePreferences;

import java.util.List;

public class MapGeneratorTest {
    private static TexturePreferences preferences;
    private GameObjectStorage storage;
    private MapGenerator generator;

    @BeforeAll
    public static void beforeAll() {
        preferences = new TexturePreferences() {
            @Override
            public int getMapWidth() {
                return 10;
            }

            @Override
            public int getMapHeight() {
                return 8;
            }
        };
    }

    @Test
    public void test001_gameObjectsFromFileMapGenerator_positive() {
        generator = new GameObjectsFromFileMapGenerator("level.map", preferences);
        List<GameObject> expected = List.of(
                new GameObject(new Point(0, 2), 0),
                new GameObject(new Point(0, 6), 0),
                new GameObject(new Point(1, 4), 0),
                new GameObject(new Point(3, 1), 0),
                new GameObject(new Point(3, 7), 0),
                new GameObject(new Point(4, 4), 0),
                new GameObject(new Point(5, 7), 0)
        );

        storage = generator.generate();
        List<GameObject> gameObjects = storage.getTrees();
        Assertions.assertEquals(7, gameObjects.size());

        Assertions.assertTrue(expected.containsAll(gameObjects));
        Assertions.assertEquals(storage.getPlayerGameObject().getCoordinates(), new Point(5, 2));
    }

    @Test
    public void test002_gameObjectsRandomMapGenerator_positive() {
        generator = new GameObjectsRandomMapGenerator(5, 1, preferences);

        storage = generator.generate();
        Assertions.assertEquals(5, storage.getTrees().size());
        Assertions.assertNotNull(storage.getPlayerGameObject());
    }
}
