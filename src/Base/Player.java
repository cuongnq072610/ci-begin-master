package Base;

import Base.Counter.FrameCounter;
import Base.Renderer.AnimationRenderer;
import Base.Renderer.SingleImgRenderer;
import Game.GameCanvas;
import tklibs.SpriteUtils;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Player extends GameObject {
    FrameCounter fireCounter;

    public Player (){
        super();
        ArrayList<BufferedImage> images = SpriteUtils.loadImages(
                "assets/images/players/straight/0.png",
                "assets/images/players/straight/1.png",
                "assets/images/players/straight/2.png",
                "assets/images/players/straight/3.png",
                "assets/images/players/straight/4.png",
                "assets/images/players/straight/5.png",
                "assets/images/players/straight/6.png"
        );
        this.renderer = new AnimationRenderer(images);
        this.position = new Vector2D(Setting.START_PLAYER_POSITION_X,Setting.START_PLAYER_POSITION_Y);
//        Setting.newX = this.position.x;
//        Setting.newY = this.position.y;
        this.fireCounter = new FrameCounter(10);
    }

    public void move(int translateX, int translateY){
        this.position.addThis(translateX,translateY);
    }

    @Override
    public void run() {
        if(KeyEventPress.isUpPress){
            this.move(0,-1);

        }
        if(KeyEventPress.isDownPress){

            this.move(0,1);
        }
        if(KeyEventPress.isRightPress){

            this.move(1,0);

        }
        if(KeyEventPress.isLeftPress){
            this.move(-1,0);

        }
        //fire
        boolean fireCounterRun = this.fireCounter.run();
        if (KeyEventPress.isSpacePress && fireCounterRun){
            this.fire();
        }
    }

    public void fire(){
        //PlayerBullet bullet = new PlayerBullet();
        PlayerBullet bullet = GameObject.recycle(PlayerBullet.class);
        ExtraBullet extraBullet = GameObject.recycle(ExtraBullet.class);
        ExtraBullet2 extraBullet2 = GameObject.recycle(ExtraBullet2.class);

        bullet.position.set(this.position.x, this.position.y);
        extraBullet.position.set(this.position.x-1, this.position.y-1);
        extraBullet2.position.set(this.position.x+1, this.position.y-1);

        //GameCanvas.bullets.add(bullet);
        this.fireCounter.reset();
            // count 10 frame set = true
    }
}
