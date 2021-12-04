package ru.mipt.bit.platformer.graphics.objects;

import com.badlogic.gdx.graphics.Texture;
import ru.mipt.bit.platformer.graphics.TexturePathsConst;
import ru.mipt.bit.platformer.models.logic.BaseLogicObject;

public class LibGdxGraphicBullet extends LibGdxBaseGraphicObject {
    public LibGdxGraphicBullet(BaseLogicObject baseLogicObject) {
        super(new Texture(TexturePathsConst.bulletTexturePath), baseLogicObject);
    }
}
