package base.renderer;

import base.GameObject;
import tklibs.SpriteUtils;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SingleImgRenderer extends Renderer {
    BufferedImage image;

    public SingleImgRenderer(String url){
        this.image = SpriteUtils.loadImage(url);
    }

    public SingleImgRenderer(BufferedImage image){
        this.image = image;
    }

    @Override
    public void Render(Graphics g, GameObject master) {
        double x = master.position.x - image.getWidth() * master.anchor.x;
        double y = master.position.y - image.getHeight() * master.anchor.y;
        g.drawImage(image, (int)x, (int)y,null);
    }
}
