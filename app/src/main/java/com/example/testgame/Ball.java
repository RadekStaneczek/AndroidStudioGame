package com.example.testgame;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class Ball implements CollisionListener{
    private Point position;
    private Point previousPosition;
    private Point size;
    private Point velocity;
    public Event event;
    private Bitmap image;
    private Bitmap scaled;
    public Ball(Point position, Point size,Point velocity)
    {
        this.position = position;
        this.size = size;
        this.velocity = velocity;
    }
    public void draw(Canvas c)
    {
        Paint p = new Paint();
        p.setColor(Color.WHITE);
        image = BitmapFactory.decodeResource(MainActivity.getContext().getResources(),R.raw.ball);
        scaled = Bitmap.createScaledBitmap(image,image.getWidth()/6,image.getHeight()/6,false);

        c.drawRect(position.x,position.y,position.x + size.x,position.y + size.y,p);
    }
    public void move(SurfaceHolder holder)
    {
        previousPosition = new Point(position);
        if(position.y < 0 || position.y + size.y > holder.getSurfaceFrame().height())
        {
            velocity.y *= -1;
        }
        if(position.x > holder.getSurfaceFrame().width())
        {
            GamePanel.counterP1++;
            respawn(holder);
            velocity.x = 5;
        }
        else if(position.x < 0)
        {
            GamePanel.counterP2++;
            respawn(holder);
            velocity.x = -5;
        }
        setPosition(new Point(position.x + velocity.x, position.y + velocity.y));
    }
    public Point getPosition() {
        return position;
    }
    private void respawn(SurfaceHolder holder)
    {
        position.x = holder.getSurfaceFrame().width()/2;
        position.y = holder.getSurfaceFrame().height()/2;

    }
    public void setPosition(Point position) {
        this.position = position;
    }

    public Point getPreviousPosition() {
        return previousPosition;
    }

    public Point getSize() {
        return size;
    }

    public void setSize(Point size) {
        this.size = size;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public void setVelocity(Point velocity) {
        this.velocity = velocity;
    }

    @Override
    public void onCollision(Event e) {
        event = e;
        if((event != null && event.type == Event.Type.TopPaddle)){
            velocity.y *= -1;
        }
        if((event != null && event.type == Event.Type.WallPaddle) ){
            velocity.x *= -1;
        }
        velocity.x *= 1.3;
        event = null;
    }
}

