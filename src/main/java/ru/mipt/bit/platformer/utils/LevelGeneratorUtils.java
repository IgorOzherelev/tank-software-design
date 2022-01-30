package ru.mipt.bit.platformer.utils;

import ru.mipt.bit.platformer.preferences.TexturePreferences;

/**
 * Application
 */
public class LevelGeneratorUtils {
    public static void checkTreesAndTanksQuantity(int treesQuantity, int tanksQuantity) {
        if (tanksQuantity <= 0 || treesQuantity < 0) {
            throw new IllegalArgumentException("tanksQuantity <=0 or treesQuantity < 0" +
                    " tanksQuantity: " + tanksQuantity + " treesQuantity: " + treesQuantity);
        }
    }

    public static void checkTotalObjectsQuantity(TexturePreferences preferences, int tanksQuantity, int obstacleQuantity) {
        int halfOfMapSquare = preferences.getMapWidth() * preferences.getMapHeight() / 2;
        if (halfOfMapSquare <= tanksQuantity * obstacleQuantity) {
            throw new IllegalArgumentException("Wrong total tanks and trees quantity >= map square / 2: "
                    + " trees: " + obstacleQuantity + " tanks: " + tanksQuantity + "map square / 2: " + halfOfMapSquare);
        }
    }
}
