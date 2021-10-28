package ru.mipt.bit.platformer.services.gamestate;


public interface AiGameStateService<T> {
    void init();
    void update();
    T getGameState();
}
