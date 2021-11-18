package ru.mipt.bit.platformer.graphics.renderers;

import ru.mipt.bit.platformer.event.EventGameSubscriber;

public interface Renderer extends EventGameSubscriber {
    String tankTexturePath = "images/tank_blue.png";
    String treeTexturePath = "images/greenTree.png";
    String bulletTexturePath = "images/bullet.png";

    void render();
    void dispose();
}
