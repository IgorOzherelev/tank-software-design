package ru.mipt.bit.platformer.services.generator;

public interface MapGenerator {
    void generateFromFile(String filePath);
    void generateRandomly(int treesQuantity, int tanksQuantity);
}
