package com.example.testgame;

public class Gameloop implements Runnable{
    private Thread gameThread;
    private GamePanel panel;
    public Gameloop(GamePanel panel){
        gameThread = new Thread(this);
        this.panel = panel;
    }
    @Override
    public void run()
    {
        while(true)
        {
            panel.render();
        }
    }

    public void startGameLoop() {
        gameThread.start();
    }
}
