package base.renderer;

import base.counter.FrameCounter;
import base.GameObject;
import tklibs.SpriteUtils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;

public class AnimationRenderer extends Renderer {
    ArrayList<BufferedImage> images;
    int currentImg = 0;
    FrameCounter frameCounter;

    public AnimationRenderer(String... urls){
        ArrayList<BufferedImage> images = SpriteUtils.loadImages(urls);
        this.images = images;
        this. frameCounter = new FrameCounter(5);
    }

    public AnimationRenderer(ArrayList<BufferedImage> images){
        this.frameCounter = new FrameCounter(5);
        this.images = images;
    }

    public AnimationRenderer(ArrayList<BufferedImage> images, int frameCount){
        this.images = images;
        this.frameCounter = new FrameCounter(frameCount);
    }

    @Override
    public void Render(Graphics g, GameObject master) {
        if(images.size()>0){
            BufferedImage image = images.get(currentImg);
            double x = master.position.x - image.getWidth() * master.anchor.x;
            double y = master.position.y - image.getHeight() * master.anchor.y;
            g.drawImage(image, (int)x, (int)y,null);

            if(frameCounter.run()){
                currentImg++;
                if(currentImg >= images.size()-1){
                    currentImg = 0;
                }
                frameCounter.reset();
            }
        }
    }
}
