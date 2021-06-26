package com.main;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Shop extends MouseAdapter {

    Handler handler;
    HUD hud;

    private int b1 = 3000;
    private int b2 = 7000;


    public Shop(Handler handler,HUD hud){
        this.handler = handler;
        this.hud = hud;
    }


    public void render(Graphics g){
        g.setColor(Color.WHITE);
        g.setFont(new Font("arial",0,48));
        g.drawString("shop".toUpperCase(),250,50);
        g.setFont(new Font("arial",0,12));



        //upgrade speed box
        g.setColor(Color.GREEN);
        g.setFont(new Font("Arial", 1, 19));
        g.drawString("Upgrade Speed".toUpperCase(),88,150);
        g.setColor(Color.RED);
        g.setFont(new Font("Arial", 1, 16));
        g.drawString("Cost: " + b2 + " points", 105,200);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", 1, 15));
        g.drawString("( Its cost increases",97,250);
        g.drawString("every time it is used )",97,270);
        g.setColor(Color.YELLOW);
        g.setFont(new Font("Arial", 1, 15));
        g.drawString("Buy".toUpperCase(),155,325);
        g.drawRect(130, 305, 80, 30);
        g.setColor(Color.WHITE);
        g.drawRect(80, 120, 180, 250);



        //refill health box
        g.setColor(Color.GREEN);
        g.setFont(new Font("Arial", 1, 20));
        g.drawString("Refill Health".toUpperCase(),384,150);
        g.setColor(Color.RED);
        g.setFont(new Font("Arial", 1, 16));
        g.drawString("Cost: " + b1 + " points"  , 395,200);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", 1, 15));
        g.drawString("( Its cost increases",392,250);
        g.drawString("every time it is used )",387,270);
        g.setColor(Color.YELLOW);
        g.setFont(new Font("Arial", 1, 15));
        g.drawString("Buy".toUpperCase(),445,325);
        g.drawRect(420, 305, 80, 30);
        g.setColor(Color.WHITE);
        g.drawRect(370, 120, 180, 250);


        g.drawString("(YOUR SCORE: " + hud.getScore() + ")", 255,70);
        g.drawString("PRESS 'SPACE' TO GO BACK",215,420);
    }

    public void mousePressed(MouseEvent e){
        int mx = e.getX();
        int my = e.getY();

        //Upgrade Health 1
        //This shop upgrade allows user to increase its max health

       /* if(mouseOver(mx,my, Game.WIDTH / 2 - 110, 100, 120, 64)) {
            if(hud.getScore() >= b1){
                hud.setScore(hud.getScore()-b1);
                b1+= 1000;
                hud.bounds += 20;
                hud.health = (100 + (hud.bounds/2)); //100 is starting value
            }
        }*/


        //Upgrade Speed LVL 1
        if(mouseOver(mx,my, 80, 120, 180, 250)) {
            if(hud.getScore() >=b2) {
                hud.setScore(hud.getScore() - b2);
                b2 += b2;
                handler.spd++;
            }
        }

        //Refill Health
        if(mouseOver(mx,my,370, 120, 180, 250)){
            if(hud.getScore() >=b1) {
                hud.setScore(hud.getScore() - b1);
                hud.health = (100 + (hud.bounds/2)); //100 is starting value
                b1+=b1;
            }
        }
    }

    private boolean mouseOver(int mx, int my, int x, int y, int width, int height){ //check if mouse is over a selected target
        if(mx > x && mx < x + width){
            if(my > y && my < y + height){
                return true;
            }else return false;
        }else return false;
    }

}
