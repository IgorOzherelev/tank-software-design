package ru.mipt.bit.platformer.event;

import ru.mipt.bit.platformer.graphics.decorators.LibGdxGraphicTankWithHealthDecorator;
import ru.mipt.bit.platformer.graphics.objects.LibGdxGraphicHealthBar;
import ru.mipt.bit.platformer.graphics.objects.LibGdxGraphicTank;
import ru.mipt.bit.platformer.graphics.renderers.LibGdxLevelRenderer;
import ru.mipt.bit.platformer.graphics.renderers.Renderer;
import ru.mipt.bit.platformer.models.GameObject;
import ru.mipt.bit.platformer.models.logic.LogicTank;

public class EventAddTank implements Event {
    @Override
    public void performGameObjectToRenderer(Renderer renderer, GameObject gameObject) {
        LibGdxLevelRenderer libGdxLevelRenderer = (LibGdxLevelRenderer) renderer;
        LibGdxGraphicTank graphicTank = new LibGdxGraphicTank((LogicTank) gameObject);
        LibGdxGraphicHealthBar graphicHealthBar = new LibGdxGraphicHealthBar(libGdxLevelRenderer, graphicTank);

        LibGdxGraphicTankWithHealthDecorator graphicTankWithHealthDecorator =
                new LibGdxGraphicTankWithHealthDecorator(
                        graphicTank, graphicHealthBar, libGdxLevelRenderer
                );

        renderer.getDrawablesMap().put(
                gameObject,
                graphicTankWithHealthDecorator
        );
    }
}
