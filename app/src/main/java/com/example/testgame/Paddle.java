package com.example.testgame;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

import java.util.ArrayList;

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
    public void checkCollision(Ball b)
    {
        ArrayList<CollisionRule> rules = new ArrayList<>();
        Rect paddleTop = new Rect(
                position.x,
                position.y,
                position.x + size.x,
                position.y
        );
        Rect paddleBottom = new Rect(
                position.x + size.x,
                position.y + size.y,
                position.x + size.x,
                position.y + size.y
        );
        Rect paddleWall = new Rect(
                position.x,
                position.y,
                position.x + size.x,
                position.y + size.y
        );
        Rect ball = new Rect(
                b.getPosition().x,
                b.getPosition().y,
                b.getPosition().x + b.getSize().x,
                b.getPosition().y + b.getSize().y
        );
        rules.add(new CollisionRule() {
            @Override
            public boolean check(){
                if(paddleTop.intersect(ball) || paddleBottom.intersect(ball))
                {
                    return true;
                }
                return false;
            }

            @Override
            public void onCollision(){
                Event event = new Event(Event.Type.TopPaddle);
                b.setEvent(event);
            }
        });
        rules.add(new CollisionRule() {
            @Override
            public boolean check(){
                if(paddleWall.intersect(ball))
                {
                    return true;
                }
                return false;
            }

            @Override
            public void onCollision(){
                Event event = new Event(Event.Type.WallPaddle);
                b.setEvent(event);
            }
        });
        rules.forEach(rule -> {
            if(rule.check())
            {
                rule.onCollision();
            }
        });
    }
    public void move(int mode)
    {
        if(mode == 1)
        {
            setPosition(new Point(position.x ,position.y - velocity.y));
        }
        else if(mode == 2)
        {
            setPosition(new Point(position.x ,position.y + velocity.y));
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


