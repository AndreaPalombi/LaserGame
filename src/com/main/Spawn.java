package com.main;

import java.awt.*;
import java.util.Random;

public class Spawn { //this class is a core class. Set the enemies spawn to manage the game difficulty

    Handler handler;
    HUD hud;
    private Game game;
    private Random random = new Random();

    private int scoreKeep = 0;

    public Spawn(Handler handler, HUD hud, Game game) {
        this.handler = handler;
        this.hud = hud;
        this.game = game;
    }


    public void tick() {
        //scoreKeep increases the same the Player score do. To keep track of the level to increase it
        scoreKeep++;


        if (scoreKeep >= 500) {
            scoreKeep = 0;
            hud.setLevel(hud.getLevel() + 1);

            // each time that game reach a certain level a new enemy spawns

            //NORMAL DIFFICULTY
            if (game.diff == 0) {

                if(hud.getLevel() == 2) {
                    handler.addObject(new BasicEnemy(random.nextInt(Game.WIDTH-10), random.nextInt(Game.HEIGHT+20), ID.BasicEnemy, handler));
                } else if (hud.getLevel() == 3) {
                    handler.addObject(new BasicEnemy(random.nextInt(Game.WIDTH-50), random.nextInt(Game.HEIGHT-50), ID.BasicEnemy, handler));
                } else if (hud.getLevel() == 4) {
                    handler.addObject(new BasicEnemy(random.nextInt(Game.WIDTH-25), random.nextInt(Game.HEIGHT-32), ID.BasicEnemy, handler));
                }else if (hud.getLevel() == 5) {
                    handler.addObject(new FastEnemy(random.nextInt(Game.WIDTH - 50), random.nextInt(Game.HEIGHT - 50),
                            ID.FastEnemy, handler));
                } else if (hud.getLevel() == 6) {
                    handler.addObject(new FastEnemy(random.nextInt(Game.WIDTH - 15), random.nextInt(Game.HEIGHT - 20),
                            ID.FastEnemy, handler));
                } else if (hud.getLevel() == 7) {
                    handler.addObject(new SmartEnemy(random.nextInt(Game.WIDTH - 50), random.nextInt(Game.HEIGHT - 50),
                            ID.SmartEnemy, handler));
                } else if (hud.getLevel() == 11) {
                    handler.addObject(new BasicEnemy(random.nextInt(Game.WIDTH)-45, random.nextInt(Game.HEIGHT)-20, ID.BasicEnemy, handler));
                    handler.addObject(new BasicEnemy(random.nextInt(Game.WIDTH), random.nextInt(Game.HEIGHT), ID.BasicEnemy, handler));
                } else if (hud.getLevel() == 15) {
                    handler.addObject(new FastEnemy(random.nextInt(Game.WIDTH - 50), random.nextInt(Game.HEIGHT - 50),
                            ID.FastEnemy, handler));
                }else if (hud.getLevel() == 18){
                    handler.addObject(new FastEnemy(random.nextInt(Game.WIDTH - 50), random.nextInt(Game.HEIGHT - 50),
                            ID.FastEnemy, handler));
                }else if (hud.getLevel() == 20) {
                    handler.clearEnemies();
                    handler.addObject(new BossEnemy((Game.WIDTH / 2) - 48, -70, ID.BossEnemy, handler));
                }
            }


            //HARD DIFFICULTY
            if (game.diff == 1) {
                if (hud.getLevel() == 2) {
                    handler.addObject(new FasterEnemy(random.nextInt(Game.WIDTH), random.nextInt(Game.HEIGHT), ID.FasterEnemy, handler));
                } else if (hud.getLevel() == 3) {
                    handler.addObject(new FasterEnemy(random.nextInt(Game.WIDTH), random.nextInt(Game.HEIGHT), ID.FasterEnemy, handler));
                } else if (hud.getLevel() == 5) {
                    handler.addObject(new HardEnemy(random.nextInt(Game.WIDTH - 50), random.nextInt(Game.HEIGHT - 50),
                            ID.HardEnemy, handler));
                } else if (hud.getLevel() == 8) {
                    handler.addObject(new HardEnemy(random.nextInt(Game.WIDTH - 50), random.nextInt(Game.HEIGHT - 50),
                            ID.HardEnemy, handler));
                } else if (hud.getLevel() == 10) {
                    handler.clearEnemies();
                    handler.addObject(new SmartEnemy(random.nextInt(Game.WIDTH - 50), random.nextInt(Game.HEIGHT - 50),
                            ID.SmartEnemy, handler));
                    handler.addObject(new HardEnemy(random.nextInt(Game.WIDTH - 50), random.nextInt(Game.HEIGHT - 50),
                            ID.HardEnemy, handler));
                } else if (hud.getLevel() == 12) {
                    handler.addObject(new HardEnemy(random.nextInt(Game.WIDTH - 50), random.nextInt(Game.HEIGHT - 50),
                            ID.HardEnemy, handler));
                    handler.clearEnemies();
                } else if (hud.getLevel() == 15) {
                    handler.addObject(new HardEnemy(random.nextInt(Game.WIDTH - 50), random.nextInt(Game.HEIGHT - 50),
                            ID.HardEnemy, handler));
                }else if (hud.getLevel() == 18) {
                    handler.addObject(new HardEnemy(random.nextInt(Game.WIDTH - 50), random.nextInt(Game.HEIGHT - 50),
                            ID.HardEnemy, handler));
                }else if (hud.getLevel() == 20) {
                    handler.clearEnemies();
                    handler.addObject(new BossEnemyHard((Game.WIDTH / 2) - 48, -70, ID.BossEnemy, handler));
                }
            }
        }
    }
}
