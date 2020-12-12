package com.nemosw.spigot.tap.v1_12_R1.packet;

import net.minecraft.server.v1_12_R1.PacketPlayOutCustomPayload;
import net.minecraft.server.v1_12_R1.PlayerConnection;

@FunctionalInterface
public interface NMSPacketLazy extends NMSPacket
{
	
	@Override
	default void send(PlayerConnection conn)
	{
		conn.sendPacket(create());
	}
	
	PacketPlayOutCustomPayload create();

}
