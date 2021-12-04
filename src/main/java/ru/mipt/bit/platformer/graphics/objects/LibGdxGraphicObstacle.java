package ru.mipt.bit.platformer.graphics.objects;

import com.badlogic.gdx.graphics.Texture;
import ru.mipt.bit.platformer.graphics.TexturePathsConst;
import ru.mipt.bit.platformer.models.logic.BaseLogicObject;

public class LibGdxGraphicObstacle extends LibGdxBaseGraphicObject {
    public LibGdxGraphicObstacle(BaseLogicObject baseLogicObject) {
        super(new Texture(TexturePathsConst.treeTexturePath), baseLogicObject);
    }
}
