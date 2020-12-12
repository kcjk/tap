package com.nemosw.spigot.tap.scoreboard;

import org.bukkit.ChatColor;

import java.util.Set;

public interface TapTeam
{
    TapScoreboard getScoreboard();

    String getName();

    String getDisplayName();

    void setDisplayName(String displayName);

    String getPrefix();

    void setPrefix(String prefix);

    String getSuffix();

    void setSuffix(String suffix);

    boolean allowFriendlyFire();

    void setAllowFriendlyFire(boolean allowFriendlyFire);

    boolean canSeeFriendlyInvisibles();

    void setCanSeeFriendlyInvisibles(boolean canSeeFriendlyInvisibles);

    NameTagVisibility getNameTagVisibility();

    void setNameTagVisibility(NameTagVisibility visibility);

    CollisionRule getCollisionRule();

    void setCollisionRule(CollisionRule rule);

    void addEntry(String name);

    boolean removeEntry(String name);

    boolean hasEntry(String name);

    Set<String> getEntries();

    int getSize();

    ChatColor getColor();

    void setColor(ChatColor chatColor);

    void unregister();
}
