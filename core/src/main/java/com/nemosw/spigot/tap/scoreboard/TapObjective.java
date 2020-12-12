package com.nemosw.spigot.tap.scoreboard;

public interface TapObjective
{
    TapScoreboard getScoreboard();

    String getName();

    String getDisplayName();

    void setDisplayName(String displayName);

    DisplaySlot getDisplaySlot();

    void setDisplaySlot(DisplaySlot slot);

    TapScore getScore(String name);

    TapScore registerScore(String name);

    TapScore registerScore(String name, int score);

    TapScore unregisterScore(String name);

    void clear();

    void unregister();
}
