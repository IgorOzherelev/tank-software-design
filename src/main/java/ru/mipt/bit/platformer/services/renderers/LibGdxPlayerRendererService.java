package ru.mipt.bit.platformer.services.renderers;

import com.badlogic.gdx.graphics.g2d.Batch;
import ru.mipt.bit.platformer.models.objects.GraphicObject;

public class LibGdxPlayerRendererService implements RendererService {
    private final GraphicObject playerObject;
    private final Batch batch;

    public LibGdxPlayerRendererService(GraphicObject playerObject, Batch batch) {
        this.playerObject = playerObject;
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
