package com.nemosw.spigot.tap.sound;

public abstract class Sound
{

    protected final String name;

    public Sound(String name)
    {
        this.name = name;
    }

    public final String getName()
    {
        return this.name;
    }

}
