package ru.mipt.bit.platformer.services.renderers;

import com.badlogic.gdx.graphics.g2d.Batch;
import ru.mipt.bit.platformer.models.Player;
import ru.mipt.bit.platformer.models.movable.AbstractMovableObject;

public class LibGdxPlayerRendererService implements RendererService {
    private final Player player;
    private final Batch batch;
    private final AbstractMovableObject playerObject;

    public LibGdxPlayerRendererService(Player player, Batch batch) {
        this.player = player;
        this.playerObject = player.getPlayerObject();
        this.batch = batch;
    }

    @Override
    public void render() {
        playerObject.draw(batch);
    }

    @Override
    public void dispose() {
        playerObject.dispose();
    }
}
