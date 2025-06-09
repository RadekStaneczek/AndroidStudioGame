package com.example.testgame;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.SurfaceHolder;

import java.util.ArrayList;

public class Paddle {
    private Point position;
    public static Point size = new Point(40,120);
    private Point velocity;
    private Boolean UP = false;
    private Boolean DOWN = false;
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
    public void AImove(Ball b)
    {
        position.y = b.getPosition().y;
    }
    public void move()
    {
        if(UP == true)
        {
            position.y -= velocity.y;
        }
        else if (DOWN == true)
        {
            position.y += velocity.y;
        }

    }
    public void checkCollision(Ball b, SurfaceHolder holder)
    {

        Rect paddleRect = new Rect(position.x, position.y, position.x + size.x, position.y + size.y);
        Rect ballRect = new Rect(b.getPosition().x, b.getPosition().y, b.getPosition().x + b.getSize().x, b.getPosition().y + b.getSize().y);

        if (Rect.intersects(paddleRect, ballRect)) {
            Point prevPos = b.getPreviousPosition();

            boolean cameFromLeft = prevPos.x + b.getSize().x <= position.x;
            boolean cameFromRight = prevPos.x >= position.x + size.x;
            boolean cameFromTop = prevPos.y + b.getSize().y <= position.y;
            boolean cameFromBottom = prevPos.y >= position.y + size.y;

            if (cameFromLeft || cameFromRight) {
                b.onCollision(new Event(Event.Type.WallPaddle));
            } else if (cameFromTop || cameFromBottom) {
                b.onCollision(new Event(Event.Type.TopPaddle));
            }
        }
        if(position.y < 0)
        {
            position.y = 0;
        }
        else if (position.y + size.y > holder.getSurfaceFrame().height()) {
            position.y = holder.getSurfaceFrame().height() - size.y;
        }
    }
    public void moveUp()
    {
        UP = true;
    }
    public void moveDown()
    {
        DOWN = true;
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

    public void stopMoving() {
        UP = false;
        DOWN = false;
    }
}


