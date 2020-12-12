package com.nemosw.spigot.tap.block;

import org.bukkit.World;
import org.bukkit.block.Block;

public interface TapBlockSupport
{

    TapBlock getBlock(int id);

    TapBlock getBlock(String name);

    default TapBlockData getBlockData(Block block)
    {
        return getBlockData(block.getWorld(), block.getX(), block.getY(), block.getZ());
    }

    TapBlockData getBlockData(World world, int x, int y, int z);

}
