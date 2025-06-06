package com.example.testgame;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
public class Ball {
    private Point position;
    private Point size;
    private Point velocity;
    public Ball(Point position, Point size,Point velocity) {
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
    public void move()
    {
        if(position.x > 1080 || position.x < 0)
        {
            velocity.x *= -1;
        }
        if(position.y > 1920 || position.y < 0)
        {
            velocity.y *= -1;
        }
        setPosition(new Point(position.x + velocity.x,position.y + velocity.y));
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

