package ru.mipt.bit.platformer.graphics.objects;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.Disposable;
import ru.mipt.bit.platformer.movement.TileMovement;

/**
 * Entity
 * */
public interface Drawable extends Disposable {
    void drawTexture(Batch batch);
    void drawMovement(TileMovement tileMovement);
}
