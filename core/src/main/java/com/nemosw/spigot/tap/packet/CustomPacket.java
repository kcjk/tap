package com.nemosw.spigot.tap.packet;

public interface CustomPacket
{

    Packet payload(String channel, byte[] message);

}
