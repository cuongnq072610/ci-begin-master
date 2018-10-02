package Base;

import Base.Renderer.SingleImgRenderer;
import tklibs.SpriteUtils;

import java.awt.image.BufferedImage;

public class BackGround extends GameObject{
    public BackGround() {
        BufferedImage image = SpriteUtils.loadImage("assets/images/background/0.png");
        this.renderer = new SingleImgRenderer(image);
        this.position = new Vector2D(0,Setting.SCREEN_HEIGHT-image.getHeight());
    }

    @Override
    public void run() {
        //System.out.println(this.image.getHeight());
        if(this.position.y >= 0){
            return;
        } else {
            this.position.y += 1;
        }
        //this.y = -2545;
    }
}