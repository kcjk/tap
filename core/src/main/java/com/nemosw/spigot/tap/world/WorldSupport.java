package com.nemosw.spigot.tap.world;

import org.bukkit.Chunk;
import org.bukkit.World;

public interface WorldSupport
{

    TapWorld fromWorld(World world);

    TapChunk fromChunk(Chunk chunk);

}
