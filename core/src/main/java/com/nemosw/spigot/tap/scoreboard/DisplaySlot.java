package com.nemosw.spigot.tap.scoreboard;

import com.google.common.collect.ImmutableList;

import java.util.List;

public enum DisplaySlot
{
    PLAYER_LIST,
    SIDEBAR,
    BELOW_NAME;

    private static final List<DisplaySlot> list;

    static
    {
        list = ImmutableList.copyOf(values());
    }

    public static List<DisplaySlot> list()
    {
        return list;
    }
}
