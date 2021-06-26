package com.main;

//This is a simple game where players have to avoid bouncing lasers
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable {

    public static final int WIDTH = 640, HEIGHT = WIDTH / 12 * 9;

    private Thread thread;
    private boolean running = false;

    public static boolean paused = false;
    public int diff = 0;        //0 = normal
                                //1 = hard

    private Random random;
    private Handler handler;
    private HUD hud;
    private Spawn spawner;
    private Menu menu;
    private Shop shop;

    public enum STATE { //enum set the state of the game
        Menu,
        Select,
        Game,
        Help,
        Shop,
        GameOver
    };

    public static STATE gameState = STATE.Menu;

    public Game() {
        handler = new Handler(); /*!important - keep handler initialization on the top to avoid random
                                    NullPointerException game crash*/

        hud = new HUD(); /*!important - keep HUD menu initialization above Menu to avoid NullPointerException game crash*/
        shop = new Shop(handler,hud);
        menu = new Menu(this, handler, hud);
        this.addKeyListener(new KeyInput(handler, this));
        this.addMouseListener(menu);
        this.addMouseListener(shop);

        //load game music when app starts
        AudioPlayer.load();
        AudioPlayer.getMusic("menu_sound").loop();

        //set main window
        new Window(WIDTH,HEIGHT,"Laser Game",this);

        spawner = new Spawn(handler, hud, this); //!important - keep spawn initialization below HUD to avoid  to get an error
        random = new Random();

        //DEBUG CODE

        if(gameState == STATE.Game){
            handler.addObject(new Player(WIDTH/2-20, HEIGHT/2-32, ID.Player, handler));
            handler.addObject(new BasicEnemy(random.nextInt(WIDTH-50), random.nextInt(HEIGHT-50), ID.BasicEnemy, handler));
        }else{
            for(int i = 0; i < 20; i++){
                handler.addObject(new MenuParticle(random.nextInt(WIDTH), random.nextInt(HEIGHT),ID.MenuParticle, handler));
            }
        }
    }

    public synchronized void start(){   //initialize the thread, re-frame to this instance of the Game class
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public synchronized void stop(){    //stop the thread and catch exception if something wrong
        try{
            thread.join();
            running = false;
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void run(){
        this.requestFocus(); //you don't need to click on the window before play

        //game loop
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while (running){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >= 1){
                tick();
                delta--;
            }
            if(running)
                render();
            frames++;

            if(System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                //System.out.println("FPS: " + frames);
                frames = 0;
            }
        }
        stop();
    }

    private void tick(){

        if(gameState == STATE.Game){
            if(!paused){
                handler.tick();
                hud.tick();
                spawner.tick();

                if(HUD.health <= 0){
                    HUD.health = 100;
                    gameState = STATE.GameOver;
                    handler.clearEnemies();
                    for(int i = 0; i < 20; i++) {
                        handler.addObject(new MenuParticle(random.nextInt(WIDTH), random.nextInt(HEIGHT),
                                ID.MenuParticle, handler));
                    }
                }
            }
        }else if(gameState == STATE.Menu || gameState == STATE.Select || gameState == STATE.GameOver){
            menu.tick();
        }

    }

    private void render(){ //rendered window
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        g.setColor(Color.black);
        g.fillRect(0,0,WIDTH,HEIGHT);

        if(paused){
            g.setColor(Color.white);
            g.drawString("paused".toUpperCase(), Game.WIDTH/2-26,100);
        }
        if(gameState == STATE.Game){
            handler.render(g);
            hud.render(g);
        }else if(gameState == STATE.Shop){
            shop.render(g);
        }else if(gameState == STATE.Menu || gameState == STATE.Help || gameState == STATE.Select
                || gameState == STATE.GameOver){
            handler.render(g);
            menu.render(g);
            handler.tick();
        }

        g.dispose();
        bs.show();
    }

     public static float clamp(float var, float min, float max){ //set limits to objects to
       return Math.max(min, Math.min(max,var));
    }

    public static void main(String[] args) {

        new Game();
    }
}
