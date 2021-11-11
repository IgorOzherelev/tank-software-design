package ru.mipt.bit.platformer.geometry;

public enum Rotation {
    N(90f),
    S(-90f),
    W(-180f),
    E(0f);

    private final float value;

    public float getValue() {
        return value;
    }

    Rotation(float value) {
        this.value = value;
    }
}
