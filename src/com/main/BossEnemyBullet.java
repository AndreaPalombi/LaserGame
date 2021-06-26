package com.main;

import java.awt.*;
import java.util.Random;

public class BossEnemyBullet extends GameObject{

    private Handler handler;
    Random random = new Random();

    public BossEnemyBullet(int x, int y, ID id, Handler handler){
        super(x,y,id);
        this.handler = handler;

        velX = (random.nextInt(5 - -5) + -5);
        velY = 5;
    }


    public Rectangle getBounds(){ //create a rectangle surroundings the object. Used to catch collisions with other objects
        return new Rectangle((int)x,(int)y,16,16);
    }

    public void tick(){
        x += velX;
        y += velY;

        if(y >= Game.HEIGHT) handler.removeObject(this);

        //if(y <= 0 || y >= Game.HEIGHT - 52) velY *= -1;
        //if(x <= 0 || x >= Game.WIDTH - 32) velX *= -1;

        handler.addObject(new Trail((int)x,(int)y, ID.Trail,Color.PINK, 16,16, 0.02f, handler));
    }

    public void render(Graphics g){
        g.setColor(Color.PINK);
        g.fillRect((int)x,(int)y,16,16);
    }
}
