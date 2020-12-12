package com.nemosw.spigot.tap.v1_12_R1.packet;

import com.nemosw.spigot.tap.packet.Packet;
import net.minecraft.server.v1_12_R1.DedicatedPlayerList;
import net.minecraft.server.v1_12_R1.EntityPlayer;
import net.minecraft.server.v1_12_R1.PlayerConnection;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_12_R1.CraftServer;
import org.bukkit.craftbukkit.v1_12_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.util.List;

public interface NMSPacket extends Packet
{
	
	DedicatedPlayerList SERVER = ((CraftServer) Bukkit.getServer()).getHandle();
	
	@Override
	default void sendTo(Player player)
	{
		send(((CraftPlayer) player).getHandle());
	}

	@Override
	default void sendNearBy(World world, double x, double y, double z, double radius)
	{
		int dimension = ((CraftWorld) world).getHandle().dimension;
		double squareRadius = radius * radius;
		List<EntityPlayer> players = SERVER.players;

        for (EntityPlayer player : players)
        {
            if (player.dimension == dimension)
            {
                double dx = x - player.locX;
                double dy = y - player.locY;
                double dz = z - player.locZ;

                if (dx * dx + dy * dy + dz * dz < squareRadius)
                    send(player);
            }
        }
	}

	@Override
	default void sendAll()
	{
		List<EntityPlayer> players = NMSPacketFixed.SERVER.players;

        for (EntityPlayer player : players)
            send(player);
	}
	
	default void send(EntityPlayer player)
	{
		PlayerConnection conn = player.playerConnection;
		
		if (conn != null)
			send(conn);
	}
	
	void send(PlayerConnection conn);
	
}
