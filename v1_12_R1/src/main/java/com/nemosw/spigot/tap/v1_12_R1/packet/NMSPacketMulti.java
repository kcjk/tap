package com.nemosw.spigot.tap.v1_12_R1.packet;

import net.minecraft.server.v1_12_R1.Packet;
import net.minecraft.server.v1_12_R1.PlayerConnection;

public class NMSPacketMulti implements NMSPacket
{

	public final Packet<?>[] packets;

	public NMSPacketMulti(Packet<?>... packets)
	{
		this.packets = packets;
	}

	@Override
	public void send(PlayerConnection conn)
	{
		for (Packet<?> packet : this.packets)
			conn.sendPacket(packet);
	}

}