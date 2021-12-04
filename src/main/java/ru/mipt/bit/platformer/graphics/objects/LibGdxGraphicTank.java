package ru.mipt.bit.platformer.graphics.objects;

import com.badlogic.gdx.graphics.Texture;
import ru.mipt.bit.platformer.graphics.TexturePathsConst;
import ru.mipt.bit.platformer.models.logic.BaseLogicObject;

/**
 * Adapter
 * */
public class LibGdxGraphicTank extends LibGdxBaseGraphicObject {
    public LibGdxGraphicTank(BaseLogicObject baseLogicObject) {
        super(new Texture(TexturePathsConst.tankTexturePath), baseLogicObject);
    }
}
