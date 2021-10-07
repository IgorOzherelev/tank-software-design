package ru.mipt.bit.platformer.services.movement;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Rectangle;
import ru.mipt.bit.platformer.models.movable.AbstractMovableObject;

import static ru.mipt.bit.platformer.util.GameGraphicUtils.placeRectangleAtTileCenter;

public class LibGdxTileMovementService implements TileMovementService {
    private final TiledMapTileLayer tileLayer;
    private final Interpolation interpolation;

    public LibGdxTileMovementService(TiledMapTileLayer tileLayer, Interpolation interpolation) {
        this.tileLayer = tileLayer;
        this.interpolation = interpolation;
    }

    @Override
    public void updateMovableGameObjectCoordinates(AbstractMovableObject movingGraphicObject) {
        Rectangle rectangle = movingGraphicObject.getGraphicObject().getRectangle();
        float progress = movingGraphicObject.getMovementProgress();

        GridPoint2 fromTileCoordinates = movingGraphicObject.getCurrentCoordinates();
        GridPoint2 toTileCoordinates = movingGraphicObject.getDestinationCoordinates();

        placeRectangleAtTileCenter(tileLayer, rectangle, fromTileCoordinates);
        float fromTileBottomLeftX = rectangle.x;
        float fromTileBottomLeftY = rectangle.y;

        placeRectangleAtTileCenter(tileLayer, rectangle, toTileCoordinates);
        float toTileBottomLeftX = rectangle.x;
        float toTileBottomLeftY = rectangle.y;

        float intermediateBottomLeftX = interpolation.apply(fromTileBottomLeftX, toTileBottomLeftX, progress);
        float intermediateBottomLeftY = interpolation.apply(fromTileBottomLeftY, toTileBottomLeftY, progress);

        rectangle
                .setX(intermediateBottomLeftX)
                .setY(intermediateBottomLeftY);
    }
}
