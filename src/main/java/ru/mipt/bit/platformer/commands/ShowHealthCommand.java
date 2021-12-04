package ru.mipt.bit.platformer.commands;

import ru.mipt.bit.platformer.graphics.renderers.Renderer;

/**
 * Application
 * Use case
 * */
public class ShowHealthCommand implements Command {
    private final Renderer renderer;

    public ShowHealthCommand(Renderer renderer) {
        this.renderer = renderer;
    }

    @Override
    public void execute() {
        renderer.toggleHealth();
    }
}
