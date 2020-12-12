package com.nemosw.spigot.tap.v1_12_R1.packet;

import com.nemosw.spigot.tap.packet.CustomPacket;
import io.netty.buffer.Unpooled;
import net.minecraft.server.v1_12_R1.PacketDataSerializer;
import net.minecraft.server.v1_12_R1.PacketPlayOutCustomPayload;

public final class NMSCustomPacket implements CustomPacket
{

	@Override
	public NMSPacket payload(String channel, byte[] message)
	{
		return (NMSPacketLazy) () -> new PacketPlayOutCustomPayload(channel, new PacketDataSerializer(Unpooled.wrappedBuffer(message)));
	}

}
