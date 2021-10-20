package ru.mipt.bit.platformer.services.renderers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import ru.mipt.bit.platformer.models.movable.LibGdxTank;
import ru.mipt.bit.platformer.models.movable.Movable;
import ru.mipt.bit.platformer.models.objects.GameObject;
import ru.mipt.bit.platformer.models.storages.GameObjectStorage;

import java.util.ArrayList;
import java.util.List;

public class LibGdxTanksRendererService implements RendererService {
    private final Batch batch;
    private final List<LibGdxTank> tanks;

    public LibGdxTanksRendererService(GameObjectStorage storage, Batch batch) {
        this.batch = batch;
        this.tanks = createLibGdxTanks(storage.getTanks());
    }

    public List<? extends Movable> getTanks() {
        return this.tanks;
    }

    private List<LibGdxTank> createLibGdxTanks(List<GameObject> gameObjectTanks) {
        List<LibGdxTank> libGdxTanks = new ArrayList<>();

        gameObjectTanks.forEach(gameObject -> libGdxTanks.add(
                new LibGdxTank(new Texture(tankLibGdxTexturePath),
                        gameObject))
        );

        return libGdxTanks;
    }

    @Override
    public void render() {
        tanks.forEach(graphicObject -> graphicObject.draw(batch));
    }

    @Override
    public void dispose() {
        tanks.forEach(LibGdxTank::dispose);
    }
}
