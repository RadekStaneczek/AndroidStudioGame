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
    private Map<String,Object> collisionState;
    public Ball(Point position, Point size,Point velocity) {
        this.position = position;
        this.size = size;
        this.velocity = velocity;
        this.collisionState = new HashMap();
        collisionState.put("x",false);
        collisionState.put("y",false);
    }
    public void draw(Canvas c)
    {
        Paint p = new Paint();
        p.setColor(Color.WHITE);
        c.drawRect(position.x,position.y,position.x + size.x,position.y + size.y,p);
    }
    public void move(SurfaceHolder holder,ArrayList<Paddle> paddles)
    {
        checkCollision(holder,paddles);
        if(collisionState.get("x").equals(true))
        {
            velocity.x *= -1;
        }
        if(collisionState.get("y").equals(true))
        {
            velocity.y *= -1;
        }
        setPosition(new Point(position.x + velocity.x,position.y + velocity.y));
        collisionState.replace("x",false);
        collisionState.replace("y",false);
    }
    private void checkCollision(SurfaceHolder holder,ArrayList<Paddle> paddles)
    {
        for (Paddle paddle:paddles) {
            Point paddlePos = paddle.getPosition();
            Point paddleSize = paddle.getSize();
            if(position.x + size.x >= paddlePos.x && position.x <= paddlePos.x)
            {
                collisionState.replace("x",true);
            }
            if(position.y >= paddlePos.y && position.y + size.y <= paddlePos.y + paddleSize.y && position.x + size.x >= paddlePos.x)
            {
                collisionState.replace("y",true);
            }

        }
        if(position.x + size.x >= holder.getSurfaceFrame().width() || position.x <= 0)
        {
            collisionState.replace("x",true);
        }
        if(position.y + size.y >= holder.getSurfaceFrame().height() || position.y <= 0)
        {
            collisionState.replace("y",true);
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

