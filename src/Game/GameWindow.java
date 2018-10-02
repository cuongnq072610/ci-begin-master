package Game;

import Base.GameObject;
import Base.KeyEventPress;
import Base.Setting;
import Game.GameCanvas;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameWindow extends JFrame {
    GameCanvas gameCanvas;

    public GameWindow(){
        // setup
        this.setSize(Setting.SCREEN_WIDTH,Setting.SCREEN_HEIGHT); // size
        this.setResizable(false);// resize
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);// close window
        this.setupEventListener();// call event

        // init Game
        this.gameCanvas = new GameCanvas();
        this.add(gameCanvas);

        this.setVisible(true);// show window


    }

    private void setupEventListener() {
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent keyEvent) {
                if(keyEvent.getKeyCode() == KeyEvent.VK_W){
                   KeyEventPress.isUpPress = true;
                }
                if(keyEvent.getKeyCode() == KeyEvent.VK_S){
                    KeyEventPress.isDownPress = true;
                }
                if(keyEvent.getKeyCode() == KeyEvent.VK_D){
                    KeyEventPress.isRightPress = true;
                }
                if(keyEvent.getKeyCode() == KeyEvent.VK_A){
                    KeyEventPress.isLeftPress = true;
                }
                if(keyEvent.getKeyCode() == KeyEvent.VK_SPACE){
                    KeyEventPress.isSpacePress = true;
                }
            }

            @Override
            public void keyReleased(KeyEvent keyEvent) {
                if(keyEvent.getKeyCode() == KeyEvent.VK_W){
                    KeyEventPress.isUpPress = false;
                }
                if(keyEvent.getKeyCode() == KeyEvent.VK_S){
                    KeyEventPress.isDownPress = false;
                }
                if(keyEvent.getKeyCode() == KeyEvent.VK_D){
                    KeyEventPress.isRightPress = false;
                }
                if(keyEvent.getKeyCode() == KeyEvent.VK_A){
                    KeyEventPress.isLeftPress = false;
                }
                if(keyEvent.getKeyCode() == KeyEvent.VK_SPACE){
                    KeyEventPress.isSpacePress = false;
                }
            }
        });
    }

    public void GameLoop(){ // run + render execute 60/s
        //game loop co 2 phan
        // logic()
        // render()

        long delay = 1000/60;
        long lastTime = 0;
        while(true){ // loop
            long currentTime = System.currentTimeMillis();
            if(currentTime - lastTime > delay){
                // logic()
                gameCanvas.run();
                //render()
                this.repaint();
                lastTime = currentTime;
            }
        }
    }
}
