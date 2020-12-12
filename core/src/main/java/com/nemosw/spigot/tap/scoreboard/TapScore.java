package com.nemosw.spigot.tap.scoreboard;

public interface TapScore
{
    TapObjective getObjective();

    String getName();

    int getScore();

    void setScore(int score);

    void unregister();
}
