package com.main;

import java.awt.*;
import java.util.ArrayList;

public class Handler { //this class maintains all objects render them in the game

    ArrayList<GameObject> object = new ArrayList<GameObject>();

    public int spd = 5; //speed boost from shop

    public void tick(){
        for(int i = 0; i < object.size(); i++){
            GameObject tempObject = object.get(i);

            tempObject.tick();
        }
    }

    public void render(Graphics g){
        for(int i = 0; i < object.size(); i++){
            GameObject tempObject = object.get(i);

            tempObject.render(g);
        }
    }

    public void clearEnemies(){
        for(int i = 0; i < object.size(); i++){
            GameObject tempObject = object.get(i);

            if(tempObject.getID() == ID.Player) {
                object.clear();
                if (Game.gameState != Game.STATE.GameOver)
                    addObject(new Player((int) tempObject.getX(), (int)tempObject.getY(), ID.Player, this));
                    //object.removeIf(x -> x.getID() != ID.Player);
            }
        }
    }

    public void addObject(GameObject object){
        this.object.add(object);
    }

    public void removeObject(GameObject object){
        this.object.remove(object);
    }

}
