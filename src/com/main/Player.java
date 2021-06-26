package com.main;

import java.awt.*;

public class Player extends GameObject {

    Handler handler;

    public Player(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
    }

    public Rectangle getBounds(){ //create a rectangle surroundings the object. Used to catch collisions with other objects
        return new Rectangle((int)x,(int)y,32,32);
    }

    @Override
    public void tick() {
        x += velX;
        y += velY;

        //fit the windows borders based on the screen resolution and ratio
        x = Game.clamp(x,0, Game.WIDTH - 47);
        y = Game.clamp(y,0, Game.HEIGHT - 70);

        handler.addObject(new Trail((int)x,(int)y,ID.Trail,Color.WHITE, 32,32, 0.05f, handler));

        collision();



    }

    private void collision() { //set collisions between Player object and enemies objects.
        for (int i = 0; i < handler.object.size(); i++) {

            GameObject tempObject = handler.object.get(i);

            if (tempObject.getID() == ID.BasicEnemy || tempObject.getID() == ID.FastEnemy ||
                    tempObject.getID() == ID.SmartEnemy) { //tempObject is now basicEnemy
                if (getBounds().intersects(tempObject.getBounds())) {
                    //collision code. Player object loses its health colliding with enemy objects.
                    HUD.health -= 2;
                }
            }else if (tempObject.getID() == ID.FasterEnemy || tempObject.getID() == ID.HardEnemy) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    //collision code. Player object loses its health colliding with enemy objects.
                    HUD.health -= 5;
                }
            }
        }
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect((int)x,(int)y,32,32);
    }
}
