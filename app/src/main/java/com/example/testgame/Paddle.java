package com.example.testgame;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
public class Paddle {
    private Point position;
    public static Point size = new Point(40,120);
    private Point velocity;
    public Paddle(Point position,Point velocity) {
        this.position = position;
        this.velocity = velocity;
    }
    public void draw(Canvas c)
    {
        Paint p = new Paint();
        p.setColor(Color.WHITE);
        c.drawRect(position.x,position.y,position.x + size.x,position.y + size.y,p);
    }
    public void move(int mode)
    {
        if(mode == 1)
        {
            setPosition(new Point(position.x ,position.y + velocity.y));
        }
        else if(mode == 2)
        {
            setPosition(new Point(position.x ,position.y - velocity.y));
        }
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
}


