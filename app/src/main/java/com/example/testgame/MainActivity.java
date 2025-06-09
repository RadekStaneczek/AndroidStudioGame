package com.example.testgame;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


public class MainActivity extends AppCompatActivity {
    FrameLayout screen;
    Button up,down;
    Thread buttons;
    public TextView pointGUI;
    static Context gameContext;
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        pointGUI = findViewById(R.id.textView);

        gameContext = this;
        GamePanel gamePanel = new GamePanel(gameContext,pointGUI);

        screen = findViewById(R.id.Frame);
        up = findViewById(R.id.button);
        down = findViewById(R.id.button2);

        screen.addView(gamePanel);
        up.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    gamePanel.p1.moveUp();
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    gamePanel.p1.stopMoving();
                }
                return true;
            }
        });
        down.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    gamePanel.p1.moveDown();
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    gamePanel.p1.stopMoving();
                }
                return true;
            }
        });

    }
    public static Context getContext()
    {
        return gameContext;
    }
}