package com.nemosw.spigot.tap.scoreboard;

import com.google.common.collect.ImmutableList;

import java.util.List;

public enum NameTagVisibility
{
    ALWAYS,
    NEVER,
    HIDE_FOR_OTHER_TEAMS,
    HIDE_FOR_OWN_TEAM;

    private static final List<NameTagVisibility> LIST = ImmutableList.copyOf(values());

    public static List<NameTagVisibility> list()
    {
        return LIST;
    }
}
