package Base;

import Base.Renderer.AnimationRenderer;
import tklibs.SpriteUtils;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class ExtraBullet extends GameObject{
    public ExtraBullet(){
        ArrayList<BufferedImage> images = SpriteUtils.loadImages(
                "assets/images/sphere-bullets/0.png",
                "assets/images/sphere-bullets/1.png",
                "assets/images/sphere-bullets/2.png",
                "assets/images/sphere-bullets/3.png");
        this.position = new Vector2D(0,0);
        this.renderer = new AnimationRenderer(images);
    }

    @Override
    public void run() {
        this.position.addThis(-1,-3);
    }
}
