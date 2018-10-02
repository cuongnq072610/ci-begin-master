package Base.Renderer;

import Base.Counter.FrameCounter;
import Base.GameObject;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class AnimationRenderer extends Renderer {
    ArrayList<BufferedImage> images;
    int currentImg = 0;
    FrameCounter frameCounter;

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
            g.drawImage(images.get(currentImg),(int)master.position.x,(int)master.position.y,null);

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
