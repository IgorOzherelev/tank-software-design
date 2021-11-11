package ru.mipt.bit.platformer.graphics.renderers;


public interface RendererService {
    String tankLibGdxTexturePath = "images/tank_blue.png";
    String treeLibGdxTexturePath = "images/greenTree.png";

    void render();
    void dispose();
}
