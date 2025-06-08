package com.example.testgame;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class Ball {
    private Point position;
    private Point size;
    private Point velocity;
    public Event event;
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
        c.drawRect(position.x,position.y,position.x + size.x,position.y + size.y,p);
    }
    public void move(SurfaceHolder holder)
    {
        if((event != null && event.type == Event.Type.WallPaddle) || position.x < 0 || position.x > holder.getSurfaceFrame().width()){
            velocity.x *= -1;
        }
        if((event != null && event.type == Event.Type.TopPaddle) || position.y < 0 || position.y > holder.getSurfaceFrame().height()){
            velocity.y *= -1;
        }
        setPosition(new Point(position.x + velocity.x, position.y + velocity.y));
        event = null;
    }
    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
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
}

