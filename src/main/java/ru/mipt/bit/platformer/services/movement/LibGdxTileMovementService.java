package ru.mipt.bit.platformer.services.movement;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Rectangle;
import ru.mipt.bit.platformer.geometry.Point;
import ru.mipt.bit.platformer.models.movable.AbstractLibGdxMovableObject;
import ru.mipt.bit.platformer.models.movable.Movable;

import static ru.mipt.bit.platformer.util.GameGraphicUtils.placeRectangleAtTileCenter;

public class LibGdxTileMovementService implements TileMovementService {
    private final TiledMapTileLayer tileLayer;
    private final Interpolation interpolation;

    public LibGdxTileMovementService(TiledMapTileLayer tileLayer, Interpolation interpolation) {
        this.tileLayer = tileLayer;
        this.interpolation = interpolation;
    }

    @Override
    public void updateMovableGameObjectRectangle(Movable movingObject) {
        AbstractLibGdxMovableObject movingGraphicObject = (AbstractLibGdxMovableObject) movingObject;
        Rectangle rectangle = movingGraphicObject.getGraphicObject().getRectangle();
        float progress = movingGraphicObject.getMovementProgress();

        Point fromTileCoordinates = movingGraphicObject.getCurrentCoordinates();
        Point toTileCoordinates = movingGraphicObject.getDestinationCoordinates();

        GridPoint2 fromTileGdxCoordinates = new GridPoint2(fromTileCoordinates.getX(), fromTileCoordinates.getY());
        GridPoint2 toTIleGdxCoordinates = new GridPoint2(toTileCoordinates.getX(), toTileCoordinates.getY());

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
