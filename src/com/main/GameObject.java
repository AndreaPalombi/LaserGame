package com.main;

import java.awt.*;

public abstract class GameObject { //creating all the objects in the game

    protected float x,y;
    protected ID id;
    protected float velX, velY; //control the speed and the instruction in the speed and wide direction

    public GameObject(float x, float y, ID id){

        this.x = x;
        this.y = y;
        this.id = id;
    }

    public abstract void tick();
    public abstract void render(Graphics g);
    public abstract Rectangle getBounds(); //Rectangle surroundings objects to catch collisions

    public void setX(int x){
        this.x = x;
    }

    public void setY(int y){
        this.y = y;
    }

    public float getX(){
        return x;
    }

    public float getY(){
        return y;
    }

    public void setID(ID id){
        this.id = id;
    }

    public ID getID(){
        return id;
    }

    public void setVelX(int velX){
        this.velX = velX;
    }

    public void setVelY(int velY){
        this.velY = velY;
    }

    public float getVelX(){
        return velX;
    }

    public float getVelY() {
        return velY;
    }
}
