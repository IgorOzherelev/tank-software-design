package ru.mipt.bit.platformer.services.renderers;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import ru.mipt.bit.platformer.models.objects.GraphicObject;

import java.util.List;

import static ru.mipt.bit.platformer.util.GameGraphicUtils.placeRectangleAtTileCenter;

public class LibGdxGraphicObjectsRendererService implements RendererService {
    private final Batch batch;
    private final List<GraphicObject> graphicObjects;
    private TiledMapTileLayer currentLayer;

    public LibGdxGraphicObjectsRendererService(List<GraphicObject> graphicObjects, Batch batch) {
        this.graphicObjects = graphicObjects;
        this.batch = batch;
    }

    @Override
    public void render() {
        graphicObjects.forEach(graphicObject -> placeRectangleAtTileCenter(currentLayer, graphicObject.getRectangle(),
                graphicObject.getGameObject().getCoordinates()));
        graphicObjects.forEach(graphicObject -> graphicObject.draw(batch));
    }

    @Override
    public void dispose() {
        graphicObjects.forEach(GraphicObject::dispose);
    }

    public void setCurrentLayer(TiledMapTileLayer currentLayer) {
        this.currentLayer = currentLayer;
    }
}
