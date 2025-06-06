package com.example.testgame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {

    private Paint p = new Paint();
    private Ball b;
    private SurfaceHolder holder;
    private Gameloop gameloop;
    public GamePanel(Context context) {
        super(context);
        holder = getHolder();
        holder.addCallback(this);
        p.setColor(Color.RED);
        gameloop = new Gameloop(this);
        b = new Ball(new Point(50,50),new Point(20,20),new Point(10,10));
    }
    public void render()
    {
        Canvas c = holder.lockCanvas();
        c.drawColor(Color.BLACK);
        b.draw(c);

        holder.unlockCanvasAndPost(c);

        b.move();
    }
    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        gameloop.startGameLoop();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {

    }
}
