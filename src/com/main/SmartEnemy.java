package com.main;

import java.awt.*;

public class SmartEnemy extends GameObject{

    private Handler handler;
    private GameObject player;

    public SmartEnemy(int x, int y, ID id, Handler handler){
        super(x,y,id);
        this.handler = handler;

        for(int i = 0; i < handler.object.size();i++){
            if(handler.object.get(i).getID() == ID.Player) player = handler.object.get(i);
        }
    }


    public Rectangle getBounds(){ //create a rectangle surroundings the object. Used to catch collisions with other objects
        return new Rectangle((int)x,(int)y,16,16);
    }

    public void tick(){

        x += velX;
        y += velY;

        //make sure that SmartEnemy follows Player
        float diffX = x - player.getX() - 26;
        float diffY = y - player.getY() - 26;
        float distance = (float) Math.sqrt((x-player.getX())*(x-player.getX()) + (y-player.getY())*(y-player.getY()));

        velX =  ((-1/ distance) * diffX);
        velY =  ((-1/ distance) * diffY);

        handler.addObject(new Trail((int)x,(int)y, ID.Trail,Color.ORANGE, 16,16, 0.02f, handler));
    }

    public void render(Graphics g){
        g.setColor(Color.ORANGE);
        g.fillRect((int)x,(int)y,16,16);
    }
}
