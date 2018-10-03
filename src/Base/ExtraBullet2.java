package Base;

import Base.GameObject;
import Base.Renderer.AnimationRenderer;
import Base.Vector2D;
import tklibs.SpriteUtils;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class ExtraBullet2 extends GameObject{
    public ExtraBullet2(){
        super();
        ArrayList<BufferedImage> images = SpriteUtils.loadImages(
                "assets/images/sphere/0.png",
                "assets/images/sphere/1.png",
                "assets/images/sphere/2.png",
                "assets/images/sphere/3.png"
        );
        this.position = new Vector2D(0,0);
        this.renderer = new AnimationRenderer(images);
    }

    @Override
    public void run() {
        if(this.position.y < 0){
            this.destroy();
            return;
        }
        this.position.addThis(1,-3);
    }
}
