package com.main;

import java.awt.*;
import java.util.Random;

public class MenuParticle extends GameObject{

    private Handler handler;
    Random random = new Random();

    private Color color;


    public MenuParticle(int x, int y, ID id, Handler handler){
        super(x,y,id);
        this.handler = handler;

        velX = (random.nextInt(5- -5)+ -5);
        velY = (random.nextInt(5- -5)+ -5);

        //avoid particles vel == 0 and stay still
        if(velX == 0) velX = 1;
        if(velY == 0) velY =1;

        color = new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255));

    }


    public Rectangle getBounds(){ //create a rectangle surroundings the object. Used to catch collisions with other objects
        return new Rectangle((int)x,(int)y,16,16);
    }

    public void tick(){
        x += velX;
        y += velY;

        if(y <= 0 || y >= Game.HEIGHT - 52) velY *= -1;
        if(x <= 0 || x >= Game.WIDTH - 32) velX *= -1;

        handler.addObject(new Trail((int)x,(int)y, ID.Trail,color, 16,16, 0.04f, handler));
    }

    public void render(Graphics g){
        g.setColor(color);
        g.fillRect((int)x,(int)y,16,16);
    }
}
