package com.example.testgame;

import android.view.SurfaceHolder;

public interface CollisionRule
{
    boolean check();

    void onCollision();
}
