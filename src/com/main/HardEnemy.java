package com.main;

import java.awt.*;

public class HardEnemy extends GameObject{

    private Handler handler;

    public HardEnemy(int x, int y, ID id, Handler handler){
        super(x,y,id);
        this.handler = handler;

        velX = 9;
        velY = 9;
    }


    public Rectangle getBounds(){ //create a rectangle surroundings the object. Used to catch collisions with other objects
        return new Rectangle((int)x,(int)y,19,19);
    }

    public void tick(){
        x += velX;
        y += velY;

        if(y <= 0 || y >= Game.HEIGHT - 52) velY *= -1;
        if(x <= 0 || x >= Game.WIDTH - 32) velX *= -1;

        handler.addObject(new Trail((int)x,(int)y, ID.Trail,Color.YELLOW, 19,19, 0.03f, handler));
    }

    public void render(Graphics g){
        g.setColor(Color.YELLOW);
        g.fillRect((int)x,(int)y,19,19);
    }
}
