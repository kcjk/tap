package com.nemosw.spigot.tap.scoreboard;

import org.bukkit.entity.Player;

import java.util.List;
import java.util.Set;

public interface TapScoreboard
{
    TapObjective registerObjective(String name);

    TapObjective unregisterObjective(String name);

    TapObjective getObjective(String name);

    TapObjective[] getObjectives();

    void clearSlot(DisplaySlot slot);

    Set<TapScore> resetScores(String name);

    TapTeam registerTeam(String name);

    TapTeam unregisterTeam(String name);

    TapTeam getTeam(String name);

    TapTeam getEntryTeam(String name);

    TapTeam[] getTeams();

    void registerPlayer(Player player);

    void unregisterPlayer(Player player);

    void unregisterAllPlayers();

    List<Player> getRegisteredPlayers();

    void clear();
}
