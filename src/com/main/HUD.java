package com.main;

import java.awt.*;

public class HUD { //this class set the Head-up display elements such as points, health bar, enemy energy etc...

    public static float health = 100;
    private float greenValue = 255f;
    public int bounds = 0; //set the bounds for shop upgrades

    private int score = 0;
    private int level = 1;

    public void tick(){
        health = Game.clamp(health, 0, 100 + (bounds/2)); // bounds/2 because in fillRect health is multiply * 2
        greenValue = health*2;
        //health color fading when player is hit
        greenValue = Game.clamp(greenValue, 0,255);


        score++;
    }

    public void render(Graphics g){
        //fillRect fills the object with color; drawRect draws the object outline
        g.setColor(Color.GRAY);
        g.fillRect(15,15, 200+bounds,32);
        g.setColor(new Color(75,(int)greenValue, 0));
        g.fillRect(15,15, (int)health*2,32);
        g.setColor(Color.WHITE);
        g.drawRect(15,15, 200+bounds,32);

        //graphics hud for Score and Level
        g.drawString("Score: " + score, 14,64);
        g.drawString("Level: " + level, 14,80);
        g.drawString("'Space' for shop", 14,94);
    }

    public void setScore(int score){
        this.score = score;
    }

    public int getScore(){
        return score;
    }

    public void setLevel(int level){
        this.level = level;
    }

    public int getLevel(){
        return level;
    }
}
