package ru.mipt.bit.platformer.preferences;

import com.badlogic.gdx.maps.tiled.TiledMap;

public class LibGdxGameTextureTexturePreferences implements TexturePreferences {
    private final TiledMap level;

    public LibGdxGameTextureTexturePreferences(TiledMap level) {
        this.level = level;
    }

    @Override
    public int getMapWidth() {
        return getTiledMapProperty("width", Integer.class);
    }

    @Override
    public int getMapHeight() {
        return getTiledMapProperty("height", Integer.class);
    }

    private <T> T getTiledMapProperty(String propertyName, Class<T> propertyType) {
        return level.getProperties().get(propertyName, propertyType);
    }
}
