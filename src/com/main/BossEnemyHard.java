package com.main;

import java.awt.*;
import java.util.Random;

public class BossEnemyHard extends GameObject{

    private Handler handler;
    Random random = new Random();

    private int timer = 60;
    private int timer2 = 50;

    public BossEnemyHard(int x, int y, ID id, Handler handler){
        super(x,y,id);
        this.handler = handler;

        velX = 4;
        velY = 3;
    }


    public Rectangle getBounds(){ //create a rectangle surroundings the object. Used to catch collisions with other objects
        return new Rectangle((int)x,(int)y,96,96);
    }

    public void tick(){
        x += velX;
        y += velY;

        //BossEnemy reach a certain height (timer 1) and then starts moving horizontally (timer2)
        if(timer <= 0) velY = 0;
        else timer--;

        if(timer <= 0) timer2--;
        if(timer2 <= 0){

            //BossEnemy speed up
            if(velX == 0) velX = 2;
            if(velX > 0)
            velX += 0.01f;
            else if(velX<0)
                velX -= 0.005f;


            velX = Game.clamp(velX, -10,10);

            int spawn = random.nextInt(10);
            if(spawn == 0) handler.addObject(new BossEnemyBullet((int)x+48, (int)y+48, ID.BasicEnemy, handler));
        }

        if(x <= 0 || x >= Game.WIDTH - 100) velX *= -1;
        if(y <= 100 || y >= Game.WIDTH) velY *= -1;

    }

    public void render(Graphics g){
        g.setColor(Color.RED);
        g.fillRect((int)x,(int)y,96,96);

            g.drawString("FINAL BOSS", 110, 64);
    }

}
