package com.main;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class Menu extends MouseAdapter {

    private Game game;
    private Handler handler;
    private Random random = new Random();
    private HUD hud;


    public Menu(Game game, Handler handler, HUD hud){
        this.game = game;
        this.handler = handler;
        this.hud = hud;
    }


    public void mousePressed(MouseEvent e){ //when player pressed storing X and Y position in mx and my variables
        int mx = e.getX();
        int my = e.getY();


        if(game.gameState == Game.STATE.Menu){

            //play button
            if(mouseOver(mx,my, Game.WIDTH/2-115,100,200,64)) {

                Game.gameState = Game.STATE.Select;

                AudioPlayer.getMusic("game_music").loop();

                return;

            }

            //help button
            if(mouseOver(mx,my, Game.WIDTH/2-115,210,200,64)){
                game.gameState = Game.STATE.Help;

                AudioPlayer.getMusic("menu_sound").play();
            }

            //quit button
            if(mouseOver(mx,my, Game.WIDTH/2-115,320,200,64)){
                System.exit(1);
            }
        }

        if(game.gameState == Game.STATE.Select) {

            // normal button
            if (mouseOver(mx, my, Game.WIDTH / 2 - 115, 100, 200, 64)) {
                game.gameState = Game.STATE.Game;
                handler.addObject(new Player(Game.WIDTH/2-20, Game.HEIGHT/2-32, ID.Player, handler));
                handler.clearEnemies();
                handler.addObject(new BasicEnemy(random.nextInt(Game.WIDTH), random.nextInt(Game.HEIGHT),
                        ID.BasicEnemy, handler));

                game.diff = 0;

                AudioPlayer.getMusic("game_music").loop();

            }

            //hard button
            if(mouseOver(mx,my,Game.WIDTH / 2 - 110, 210, 200, 64)) {
                game.gameState = Game.STATE.Game;
                handler.addObject(new Player(Game.WIDTH/2-20, Game.HEIGHT/2-32, ID.Player, handler));
                handler.clearEnemies();
                handler.addObject(new HardEnemy(random.nextInt(Game.WIDTH), random.nextInt(Game.HEIGHT),
                        ID.HardEnemy, handler));

                game.diff = 1;

                AudioPlayer.getMusic("menu_sound").loop();
            }

            //back button
            if (mouseOver(mx,my,Game.WIDTH / 2 - 115, 320, 200, 64)) {
                game.gameState = Game.STATE.Menu;
                AudioPlayer.getMusic("menu_sound").play();
                return;
            }
        }

        //back button for help
        if(game.gameState == Game.STATE.Help) {
            if (mouseOver(mx,my,Game.WIDTH / 2 - 115, 320, 200, 64)) {
                game.gameState = Game.STATE.Menu;
                AudioPlayer.getMusic("menu_sound").play();
                return;
            }
        }

        //retry button
        if(game.gameState == Game.STATE.GameOver) {
            if (mouseOver(mx,my,Game.WIDTH / 2 - 115, 320, 200, 64)) {
                game.gameState = Game.STATE.Menu;
                hud.setLevel(1);
                hud.setScore(0);

                AudioPlayer.getMusic("menu_sound").play();
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

    public void tick(){

    }

    public void render(Graphics g) {

        //set fonts
        Font fnt = new Font("arial", 1, 40);
        Font fnt2 = new Font("arial", 1, 30);
        Font fnt3 = new Font("arial", 1, 25);
        Font fnt4 = new Font("arial", 1, 41);

        if (game.gameState == Game.STATE.Menu) {

            g.setFont(fnt4);
            g.setColor(Color.RED);
            g.drawString("Laser Game".toUpperCase(), 169,50);
            g.setFont(fnt);
            g.setColor(Color.WHITE);
            g.drawString("Laser Game".toUpperCase(), 170, 50);

            g.setFont(fnt2);
            g.drawRect(Game.WIDTH / 2 - 115, 100, 200, 64);
            g.drawString("Play", 275, 140);


            g.setColor(Color.WHITE);
            g.drawRect(Game.WIDTH / 2 - 115, 210, 200, 64);
            g.drawString("Help", 275, 250);

            g.setColor(Color.WHITE);
            g.drawRect(Game.WIDTH / 2 - 115, 320, 200, 64);
            g.drawString("Quit", 275, 360);
        }else if(game.gameState == Game.STATE.Help) {

            g.setFont(fnt);
            g.setColor(Color.WHITE);
            g.drawString("Help".toUpperCase(), 260, 50);

            g.setFont(fnt3);
            g.drawString("Use WASD keys to move and dodge enemies.", 50, 180);
            g.drawString("If the health bar gets empty is game over.",70,220);
            g.drawString("Press 'P' to pause/resume the game.",100,260);
            g.drawRect(Game.WIDTH / 2 - 115, 320, 200, 64);
            g.drawString("Back", 280, 360);
        }else if(game.gameState == Game.STATE.GameOver) {

            g.setFont(fnt);
            g.setColor(Color.WHITE);
            g.drawString("Game Over".toUpperCase(), 180, 50);

            g.setFont(fnt3);
            g.drawString("You lost with a score of " + hud.getScore(), 135, 180);
            g.drawRect(Game.WIDTH / 2 - 115, 320, 200, 64);
            g.drawString("Retry", 280, 360);
        }else  if (game.gameState == Game.STATE.Select) {
            g.setFont(fnt);
            g.setColor(Color.WHITE);
            g.drawString("DIFFICULTY".toUpperCase(), 200, 50);

            g.setFont(fnt2);
            g.drawRect(Game.WIDTH / 2 - 110, 100, 200, 64);
            g.drawString("Normal", 260, 140);


            g.setColor(Color.WHITE);
            g.drawRect(Game.WIDTH / 2 - 110, 210, 200, 64);
            g.drawString("Hard", 275, 250);

            g.setColor(Color.WHITE);
            g.drawRect(Game.WIDTH / 2 - 110, 320, 200, 64);
            g.drawString("Back", 270, 360);
        }
    }
}
