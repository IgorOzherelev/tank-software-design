package ru.mipt.bit.platformer.movement;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Rectangle;
import ru.mipt.bit.platformer.geometry.Point;

import static ru.mipt.bit.platformer.utils.LibGdxGraphicUtils.placeRectangleAtTileCenter;

public class TileMovement {
    private final TiledMapTileLayer tileLayer;
    private final Interpolation interpolation = Interpolation.smooth;

    public TileMovement(TiledMapTileLayer tileLayer) {
        this.tileLayer = tileLayer;
    }

    public TiledMapTileLayer getTileLayer() {
        return tileLayer;
    }

    public void moveRectangleBetweenTileCenters(Rectangle rectangle, Point fromCoordinates,
                                                Point toCoordinates, float progress) {
        GridPoint2 fromTileGdxCoordinates = new GridPoint2(fromCoordinates.getX(), fromCoordinates.getY());
        GridPoint2 toTIleGdxCoordinates = new GridPoint2(toCoordinates.getX(), toCoordinates.getY());

        placeRectangleAtTileCenter(tileLayer, rectangle, fromTileGdxCoordinates);
        float fromTileBottomLeftX = rectangle.x;
        float fromTileBottomLeftY = rectangle.y;

        placeRectangleAtTileCenter(tileLayer, rectangle, toTIleGdxCoordinates);
        float toTileBottomLeftX = rectangle.x;
        float toTileBottomLeftY = rectangle.y;

        float intermediateBottomLeftX = interpolation.apply(fromTileBottomLeftX, toTileBottomLeftX, progress);
        float intermediateBottomLeftY = interpolation.apply(fromTileBottomLeftY, toTileBottomLeftY, progress);

        rectangle
                .setX(intermediateBottomLeftX)
                .setY(intermediateBottomLeftY);
    }
}
