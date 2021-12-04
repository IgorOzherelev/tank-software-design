package ru.mipt.bit.platformer.utils;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import ru.mipt.bit.platformer.geometry.Point;
import ru.mipt.bit.platformer.geometry.Rotation;

/**
 * Adapter
 */
public final class LibGdxGraphicUtils {
    private LibGdxGraphicUtils() {
    }

    public static Rectangle placeRectangleAtTileCenter(TiledMapTileLayer tileLayer, Rectangle rectangle, GridPoint2 tileCoordinates) {
        Vector2 tileCenter = calculateTileCenter(tileLayer, tileCoordinates);
        return rectangle.setCenter(tileCenter);
    }

    public static Rectangle createBoundingRectangle(TextureRegion region) {
        return new Rectangle()
                .setWidth(region.getRegionWidth())
                .setHeight(region.getRegionHeight());
    }

    public static GridPoint2 convertPointToGridPoint(Point point) {
        return new GridPoint2(point.getX(), point.getY());
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

    public static void drawRegionUnscaled(Batch batch, TextureRegion textureRegion,
                                          Rectangle rectangle, Rotation rotation) {
        int regionWidth, regionHeight;
        float regionOriginX, regionOriginY;

        regionWidth = textureRegion.getRegionWidth();
        regionHeight = textureRegion.getRegionHeight();

        regionOriginX = regionWidth / 2f;
        regionOriginY = regionHeight / 2f;

        batch.draw(
                textureRegion, rectangle.x, rectangle.y,
                regionOriginX, regionOriginY, regionWidth, regionHeight, 1f, 1f, rotation.getValue()
        );
    }
}
