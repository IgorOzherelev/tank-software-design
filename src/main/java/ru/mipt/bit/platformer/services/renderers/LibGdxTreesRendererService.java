package ru.mipt.bit.platformer.services.renderers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import ru.mipt.bit.platformer.models.objects.GameObject;
import ru.mipt.bit.platformer.models.objects.LibGdxGraphicObject;
import ru.mipt.bit.platformer.models.storages.GameObjectStorage;

import java.util.ArrayList;
import java.util.List;

import static ru.mipt.bit.platformer.util.GameGraphicUtils.*;

public class LibGdxTreesRendererService implements RendererService {
    private final Batch batch;
    private final List<LibGdxGraphicObject> libGdxGraphicObjects;
    private TiledMapTileLayer currentLayer;

    public LibGdxTreesRendererService(GameObjectStorage storage, Batch batch) {
        this.libGdxGraphicObjects = createLibGdxTrees(storage.getTrees());
        this.batch = batch;
    }

    private List<LibGdxGraphicObject> createLibGdxTrees(List<GameObject> trees) {
        List<LibGdxGraphicObject> libGdxTrees = new ArrayList<>();
        trees.forEach(gameObject -> libGdxTrees.add(
                new LibGdxGraphicObject(
                        new Texture(treeLibGdxTexturePath),
                        gameObject.getCoordinates()))
        );

        return libGdxTrees;
    }

    @Override
    public void render() {
        libGdxGraphicObjects.forEach(graphicObject -> placeRectangleAtTileCenter(currentLayer, graphicObject.getRectangle(),
                convertPointToGridPoint(graphicObject.getGameObject().getCoordinates())));
        libGdxGraphicObjects.forEach(graphicObject -> graphicObject.draw(batch));
    }

    @Override
    public void dispose() {
        libGdxGraphicObjects.forEach(LibGdxGraphicObject::dispose);
    }

    public void setCurrentLayer(TiledMapTileLayer currentLayer) {
        this.currentLayer = currentLayer;
    }
}
