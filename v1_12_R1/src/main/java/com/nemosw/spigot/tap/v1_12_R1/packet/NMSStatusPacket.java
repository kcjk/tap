package com.nemosw.spigot.tap.v1_12_R1.packet;

import com.nemosw.spigot.tap.packet.StatusPacket;
import net.minecraft.server.v1_12_R1.PacketPlayOutExperience;

public class NMSStatusPacket implements StatusPacket
{
	
	@Override
	public NMSPacket experience(float experienceBar, int level, int totalExperience)
	{
		return new NMSPacketFixed(new PacketPlayOutExperience(experienceBar, totalExperience, level));
	}
	
}
