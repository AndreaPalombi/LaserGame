package com.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter { //this class set the keyboard keys to play the game

    private Handler handler;
    private boolean[] keyDown = new boolean[4];

    Game game;

    public KeyInput(Handler handler, Game game){
        this.handler = handler;
        this.game = game;

        keyDown[0] = false; //w
        keyDown[1] = false; //s
        keyDown[2] = false; //a
        keyDown[3] = false; //d
    }

    public void keyPressed(KeyEvent e){     /*Letter binding. When players press a key on the keyboard it is going
                                            to display a number value corresponding with the pressed key*/
        int key = e.getKeyCode();

        for(int i = 0; i < handler.object.size(); i++){
            GameObject tempObject = handler.object.get(i);

            if(tempObject.getID() == ID.Player){        //copy-paste this if statement changing the ID to add new players
                //Key event for player 1
                if(key == KeyEvent.VK_W) { tempObject.setVelY(-handler.spd); keyDown[0] = true; }
                if(key == KeyEvent.VK_S) { tempObject.setVelY(handler.spd); keyDown[1] = true; }
                if(key == KeyEvent.VK_A) { tempObject.setVelX(-handler.spd); keyDown[2] = true; }
                if(key == KeyEvent.VK_D) { tempObject.setVelX(handler.spd); keyDown[3] = true; }
            }
        }
        //key command to pause the game
        if(key == KeyEvent.VK_P){
            if(game.gameState == Game.STATE.Game) {
                if (Game.paused) Game.paused = false;
                else Game.paused = true;
            }
        }
        if(key == KeyEvent.VK_ESCAPE) System.exit(1); //close the game pressing "ESC". Emergency escape.

        if(key == KeyEvent.VK_SPACE){
            if (Game.gameState == Game.STATE.Game) Game.gameState = Game.STATE.Shop;
            else if (Game.gameState == Game.STATE.Shop) Game.gameState = Game.STATE.Game;
        }
    }

    public void keyReleased(KeyEvent e){    /*Letter binding. When players release a key on the keyboard it is going
                                            to display a number value corresponding with the released key*/

        int key = e.getKeyCode();

        for(int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);

            if (tempObject.getID() == ID.Player) {
                //Key event for player 1
                if (key == KeyEvent.VK_W) keyDown[0] = false;
                if (key == KeyEvent.VK_S) keyDown[1] = false;
                if (key == KeyEvent.VK_A) keyDown[2] = false;
                if (key == KeyEvent.VK_D) keyDown[3] = false;

                //vertical movement
                if(!keyDown[0] && !keyDown[1]) tempObject.setVelY(0);
                //horizontal movement
                if(!keyDown[2] && !keyDown[3]) tempObject.setVelX(0);
            }
        }
    }

}
