package com.nemosw.spigot.tap.packet;

public interface StatusPacket
{

    Packet experience(float experienceBar, int level, int totalExperience);

}
