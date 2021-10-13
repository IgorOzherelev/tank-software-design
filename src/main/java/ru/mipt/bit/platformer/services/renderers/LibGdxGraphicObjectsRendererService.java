package ru.mipt.bit.platformer.services.renderers;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import ru.mipt.bit.platformer.models.objects.LibGdxGraphicObject;

import java.util.List;

import static ru.mipt.bit.platformer.util.GameGraphicUtils.*;

public class LibGdxGraphicObjectsRendererService implements RendererService {
    private final Batch batch;
    private final List<LibGdxGraphicObject> libGdxGraphicObjects;
    private TiledMapTileLayer currentLayer;

    public LibGdxGraphicObjectsRendererService(List<LibGdxGraphicObject> libGdxGraphicObjects, Batch batch) {
        this.libGdxGraphicObjects = libGdxGraphicObjects;
        this.batch = batch;
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
