package ru.mipt.bit.platformer.util;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.Map;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.MapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import ru.mipt.bit.platformer.models.graphics.AbstractMovableGameGraphicObject;
import ru.mipt.bit.platformer.models.graphics.Tank;
import ru.mipt.bit.platformer.models.graphics.basic.GameGraphicObject;
import ru.mipt.bit.platformer.models.graphics.Direction;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public final class GdxGameUtils {

    private GdxGameUtils() {
    }

    public static MapRenderer createSingleLayerMapRenderer(TiledMap tiledMap, Batch batch) {
        TiledMapTileLayer tileLayer = getSingleLayer(tiledMap);
        float viewWidth = tileLayer.getWidth() * tileLayer.getTileWidth();
        float viewHeight = tileLayer.getHeight() * tileLayer.getTileHeight();

        OrthogonalTiledMapRenderer mapRenderer = new OrthogonalTiledMapRenderer(tiledMap, batch);
        mapRenderer.getViewBounds().set(0f, 0f, viewWidth, viewHeight);

        return mapRenderer;
    }

    public static <L extends MapLayer> L getSingleLayer(Map map) {
        MapLayers layers = map.getLayers();
        switch (layers.size()) {
            case 0:
                throw new NoSuchElementException("Map has no layers");
            case 1:
                @SuppressWarnings("unchecked")
                L layer = (L) layers.iterator().next();
                return layer;
            default:
                throw new IllegalArgumentException("Map has more than one layer");
        }
    }

    public static Rectangle moveRectangleAtTileCenter(TiledMapTileLayer tileLayer, Rectangle rectangle, GridPoint2 tileCoordinates) {
        Vector2 tileCenter = calculateTileCenter(tileLayer, tileCoordinates);
        return rectangle.setCenter(tileCenter);
    }

    public static void drawGameGraphicObjectsUnscaled(Batch batch, List<GameGraphicObject> graphicObjects, Tank tank) {
        int regionWidth, regionHeight;
        float regionOriginX, regionOriginY;

        List<GameGraphicObject> objects = new ArrayList<>(graphicObjects);
        objects.add(tank);

        for (GameGraphicObject object : objects) {
            TextureRegion textureRegion = object.getTextureRegion();
            regionWidth = textureRegion.getRegionWidth();
            regionHeight = textureRegion.getRegionHeight();

            regionOriginX = regionWidth / 2f;
            regionOriginY = regionHeight / 2f;

            batch.draw(
                    object.getTextureRegion(), object.getRectangle().x, object.getRectangle().y,
                    regionOriginX, regionOriginY, regionWidth, regionHeight, 1f, 1f, object.getRotation()
            );
        }
    }

    public static boolean checkIsMoveSafe(Direction direction, List<GameGraphicObject> graphicObjects,
                                                AbstractMovableGameGraphicObject movableObject) {
        return graphicObjects.stream()
                .noneMatch(gameGraphicObject ->
                        gameGraphicObject.getCoordinates()
                                .equals(new GridPoint2(movableObject.getCoordinates()).add(direction.getShift())));
    }

    public static Rectangle createBoundingRectangle(TextureRegion region) {
        return new Rectangle()
                .setWidth(region.getRegionWidth())
                .setHeight(region.getRegionHeight());
    }

    private static Vector2 calculateTileCenter(TiledMapTileLayer tileLayer, GridPoint2 tileCoordinates) {
        int tileWidth = tileLayer.getTileWidth();
        int tileHeight = tileLayer.getTileHeight();
        int tileBottomLeftCornerX = tileCoordinates.x * tileWidth;
        int tileBottomLeftCornerY = tileCoordinates.y * tileHeight;

        return new Rectangle()
                .setX(tileBottomLeftCornerX)
                .setY(tileBottomLeftCornerY)
                .setWidth(tileWidth)
                .setHeight(tileHeight)
                .getCenter(new Vector2());
    }
}
