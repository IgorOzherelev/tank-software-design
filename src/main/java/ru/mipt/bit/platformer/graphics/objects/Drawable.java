package ru.mipt.bit.platformer.graphics.objects;

import com.badlogic.gdx.graphics.g2d.Batch;
import ru.mipt.bit.platformer.movement.TileMovement;

public interface Drawable {
    void drawTexture(Batch batch);
    void drawMovement(TileMovement tileMovement);
}
