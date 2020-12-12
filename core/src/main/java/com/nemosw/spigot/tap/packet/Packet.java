package com.nemosw.spigot.tap.packet;

import com.nemosw.spigot.tap.LibraryLoader;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

public interface Packet
{

    CustomPacket CUSTOM = LibraryLoader.load(CustomPacket.class);

    EffectPacket EFFECT = LibraryLoader.load(EffectPacket.class);

    EntityPacket ENTITY = LibraryLoader.load(EntityPacket.class);

    InfoPacket INFO = LibraryLoader.load(InfoPacket.class);

    ItemPacket ITEM = LibraryLoader.load(ItemPacket.class);

    ScoreboardPacket SCOREBOARD = LibraryLoader.load(ScoreboardPacket.class);

    StatusPacket STATUS = LibraryLoader.load(StatusPacket.class);

    TitlePacket TITLE = LibraryLoader.load(TitlePacket.class);

    @Deprecated
    default void send(Player player)
    {
        sendTo(player);
    }

    void sendTo(Player player);

    default void sendTo(Iterable<? extends Player> players)
    {
        for (Player player : players)
            sendTo(player);
    }

    void sendAll();

    void sendNearBy(World world, double x, double y, double z, double radius);

    default void sendNearBy(Location loc, double radius)
    {
        sendNearBy(loc.getWorld(), loc.getX(), loc.getY(), loc.getZ(), radius);
    }

}
