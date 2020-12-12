package com.nemosw.spigot.tap.v1_12_R1.packet;

import com.nemosw.spigot.tap.item.TapItem;
import com.nemosw.spigot.tap.packet.ItemPacket;
import com.nemosw.spigot.tap.v1_12_R1.item.NMSItem;
import net.minecraft.server.v1_12_R1.PacketPlayOutSetCooldown;

public class NMSItemPacket implements ItemPacket
{

	@Override
	public NMSPacket cooldown(TapItem item, int cooldownTicks)
	{
		return new NMSPacketFixed(new PacketPlayOutSetCooldown(((NMSItem) item).getHandle(), cooldownTicks));
	}

}
