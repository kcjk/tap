package com.nemosw.spigot.tap.v1_12_R1.world;

import com.google.common.base.Suppliers;
import com.google.common.collect.MapMaker;
import com.nemosw.spigot.tap.Tap;
import com.nemosw.spigot.tap.world.WorldSupport;
import net.minecraft.server.v1_12_R1.Chunk;
import net.minecraft.server.v1_12_R1.World;
import net.minecraft.server.v1_12_R1.WorldServer;
import org.bukkit.craftbukkit.v1_12_R1.CraftChunk;
import org.bukkit.craftbukkit.v1_12_R1.CraftWorld;

import java.util.Map;
import java.util.function.Supplier;

public final class NMSWorldSupport implements WorldSupport
{

	private static final Supplier<NMSWorldSupport> instance = Suppliers.memoize(() -> (NMSWorldSupport) Tap.WORLD);

    public static NMSWorldSupport getInstance()
    {
        return instance.get();
    }

    private final Map<World, NMSWorld> worlds = new MapMaker().weakKeys().weakValues().makeMap();

	private final Map<Chunk, NMSChunk> chunks = new MapMaker().weakKeys().weakValues().makeMap();

	@Override
	public NMSWorld fromWorld(org.bukkit.World world)
	{
		return wrapWorld(((CraftWorld) world).getHandle());
	}

	public NMSWorld wrapWorld(WorldServer world)
	{
		NMSWorld nmsWorld = worlds.get(world);

		if (nmsWorld == null)
			worlds.put(world, nmsWorld = new NMSWorld(world));

		return nmsWorld;
	}

	@Override
	public NMSChunk fromChunk(org.bukkit.Chunk chunk)
	{
		return wrapChunk(((CraftChunk) chunk).getHandle());
	}

	public NMSChunk wrapChunk(Chunk chunk)
	{
		NMSChunk nmsChunk = chunks.get(chunk);

		if (nmsChunk == null)
		{
			nmsChunk = new NMSChunk(chunk);
			chunks.put(chunk, nmsChunk);
		}

		return nmsChunk;
	}

}
