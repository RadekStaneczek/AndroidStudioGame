package com.example.testgame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {

    private Paint p = new Paint();
    public Ball b;

    public Paddle p1,p2;
    private TextView pointsGUI;
    public SurfaceHolder holder;
    private Gameloop gameloop;

    public static int counterP1,counterP2;
    public GamePanel(Context context, TextView points) {
        super(context);
        holder = getHolder();
        holder.addCallback(this);
        p.setColor(Color.RED);
        gameloop = new Gameloop(this);
        pointsGUI = points;
    }
    public void render()
    {
        p1.checkCollision(b,holder);
        p2.checkCollision(b,holder);

        b.move(holder);

        p2.AImove(b);
        p1.move();
        ((MainActivity)MainActivity.getContext()).runOnUiThread(() -> {
            pointsGUI.setText(counterP1 + ":" + counterP2);
        });
        Canvas c = holder.lockCanvas();
        if (c != null) {
            c.drawColor(Color.BLACK);
            b.draw(c);
            p1.draw(c);
            p2.draw(c);
            holder.unlockCanvasAndPost(c);
        }
    }
    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        b = new Ball(new Point(holder.getSurfaceFrame().width()/2,holder.getSurfaceFrame().height()/2+200),new Point(20,20),new Point(-5,5));
        p1 = new Paddle(new Point(50,holder.getSurfaceFrame().height()/2 - Paddle.size.y),new Point(20,20));
        p2 = new Paddle(new Point(holder.getSurfaceFrame().width()-50-Paddle.size.x,holder.getSurfaceFrame().height()/2 - Paddle.size.y),new Point(20,20));
        gameloop.startGameLoop();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {

    }
}
