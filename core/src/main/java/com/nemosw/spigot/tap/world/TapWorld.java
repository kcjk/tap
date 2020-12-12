package com.nemosw.spigot.tap.world;

import com.nemosw.spigot.tap.block.TapBlockData;
import com.nemosw.spigot.tap.nbt.NBTCompound;

public interface TapWorld
{

    org.bukkit.World getWorld();

    TapChunk getChunk(int x, int z);

    TapBlockData getBlock(int x, int y, int z);

    default boolean setBlock(int x, int y, int z, TapBlockData blockData)
    {
        return setBlock(x, y, z, blockData, true);
    }

    boolean setBlock(int x, int y, int z, TapBlockData block, boolean applyPhysics);

    NBTCompound saveToSchematic(int x, int y, int z, int width, int height, int length);

    void loadFromSchematic(int x, int y, int z, NBTCompound schematic);

}
