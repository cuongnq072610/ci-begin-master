package base;

import base.physics.Physics;
import base.renderer.Renderer;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
// game Object Pool:
// - An array contains all game objects in game
// - Recycle game objects
public class GameObject {
    public static ArrayList<GameObject> gameObjects = new ArrayList<>();
    public static ArrayList<GameObject> newGameObjs = new ArrayList<>();

    public static <E extends GameObject> /*<- Kieu Class*/ E /*<- Kieu tra ve*/ create(Class<E> childClass){
        try {
            GameObject newGameObj = childClass.newInstance();
            newGameObjs.add(newGameObj);
            return (E) newGameObj;
        } catch(Exception e){
            return null;
        }
    }

    public static <E extends GameObject> E recycle(Class<E> childClass){
        //1. Kiem tra co game object thoa man yeu cau: isActive = false && gameobject dung la class con khoi tao
        // co thi dung lai
        // k co thi tao moi
        //2. Return gameObject
        for (GameObject gameObject: gameObjects) {
            if(!gameObject.isActive && gameObject.getClass().isAssignableFrom(childClass)){
                gameObject.isActive = true;
                return (E) gameObject;
            }
        }
        return create(childClass);
    }

    public static <E extends GameObject> E intersect (Class<E> childClass, Physics physics){
        for (GameObject go :
                gameObjects) {
            if (go.isActive && go.getClass().isAssignableFrom(childClass) && go instanceof Physics){
                Physics physicsGo = (Physics) go;
                boolean intersected = physics.getBoxCollider().intersect(physicsGo, (GameObject) physics);
                if(intersected){
                    return (E) physicsGo;
                }
            }
        }
        return null;
    }

    public static void runAll(){
//        for (GameObject gameObj :
//                gameObjects) {
//            gameObj.run();
//        }
        for(int i = 0; i< gameObjects.size(); i++){
            if(gameObjects.get(i).isActive){
                gameObjects.get(i).run();
            }
        }
    }

    public static void renderAll(Graphics g){
//        for (GameObject gameObj :
//                gameObjects) {
//            if(gameObj.isActive){
//                gameObj.render(g);
//            }
//        }
        for(int i = 0; i< gameObjects.size(); i++){
            if(gameObjects.get(i).isActive){
                gameObjects.get(i).render(g);
            }
        }
        gameObjects.addAll(newGameObjs);
        newGameObjs.clear();
    }

    public Renderer renderer;
    public Vector2D position;
    boolean isActive;

    public GameObject() {
        this.isActive = true;
    }

    public GameObject(BufferedImage image) {
        this.isActive = true;
        this.position = new Vector2D(0,0);
    }

    public void destroy(){
        this.isActive = false;
    }

    //logic()
    public void run(){

    }

    //render()
    public void render(Graphics g){
        if(this.renderer!=null){
            this.renderer.Render(g,this);
        }
    }
}
