package ru.mipt.bit.platformer.services.renderers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import ru.mipt.bit.platformer.models.Player;
import ru.mipt.bit.platformer.models.movable.AbstractLibGdxMovableObject;
import ru.mipt.bit.platformer.models.movable.LibGdxTank;
import ru.mipt.bit.platformer.models.storages.GameObjectStorage;

public class LibGdxPlayerRendererService implements RendererService {
    private final Batch batch;
    private final AbstractLibGdxMovableObject playerObject;

    public LibGdxPlayerRendererService(Player player, GameObjectStorage storage, Batch batch) {
        this.playerObject = createPlayerObject(player, storage);
        this.batch = batch;
    }

    private AbstractLibGdxMovableObject createPlayerObject(Player player, GameObjectStorage storage) {
        player.setPlayerObject(new LibGdxTank(new Texture(tankLibGdxTexturePath),
                storage.getPlayerGameObject()));

        return (AbstractLibGdxMovableObject) player.getPlayerObject();
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
