package com.example.testgame;

public class Event
{
    public static enum Type{WallPaddle,TopPaddle};
    public Type type;

    public Event(Type t)
    {
        this.type = t;
    }

}
