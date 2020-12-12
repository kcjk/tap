package com.nemosw.spigot.tap.packet;

import com.nemosw.spigot.tap.item.TapItem;

public interface ItemPacket
{

    Packet cooldown(TapItem item, int cooldownTicks);

}
