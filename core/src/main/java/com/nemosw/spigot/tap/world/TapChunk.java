package com.nemosw.spigot.tap.world;

import com.nemosw.spigot.tap.block.TapBlockData;

public interface TapChunk
{

    TapWorld getWorld();

    int getX();

    int getZ();

    TapBlockData getBlockData(int x, int y, int z);

}
