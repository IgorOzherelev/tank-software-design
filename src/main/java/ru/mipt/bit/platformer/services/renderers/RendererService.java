package ru.mipt.bit.platformer.services.renderers;


public interface RendererService {
    String tankLibGdxTexturePath = "images/tank_blue.png";
    String treeLibGdxTexturePath = "images/greenTree.png";

    void render();
    void dispose();
}
