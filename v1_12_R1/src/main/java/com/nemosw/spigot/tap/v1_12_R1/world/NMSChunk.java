package com.nemosw.spigot.tap.v1_12_R1.world;

import com.nemosw.spigot.tap.block.TapBlockData;
import com.nemosw.spigot.tap.v1_12_R1.block.NMSBlockSupport;
import com.nemosw.spigot.tap.world.TapChunk;
import com.nemosw.spigot.tap.world.TapWorld;
import net.minecraft.server.v1_12_R1.BlockPosition;
import net.minecraft.server.v1_12_R1.Chunk;
import net.minecraft.server.v1_12_R1.WorldServer;

import java.lang.ref.WeakReference;

public final class NMSChunk implements TapChunk
{

	private final WorldServer world;

    private TapWorld tapWorld;

	private WeakReference<Chunk> weakChunk;

	private final int x, z;

	NMSChunk(Chunk chunk)
	{
		this.weakChunk = new WeakReference<>(chunk);
		this.world = (WorldServer) chunk.getWorld();
		this.x = chunk.locX;
		this.z = chunk.locZ;
	}

    public Chunk getHandle()
    {
        Chunk chunk = this.weakChunk.get();

        if (chunk == null)
        {
            chunk = this.world.getChunkAt(this.x, this.z);
            this.weakChunk = new WeakReference<>(chunk);
        }

        return chunk;
    }

	@Override
	public TapWorld getWorld()
	{
		TapWorld world = this.tapWorld;

		return world == null ? this.tapWorld = NMSWorldSupport.getInstance().wrapWorld(this.world) : world;
	}

	@Override
	public int getX()
	{
		return this.x;
	}

	@Override
	public int getZ()
	{
		return this.z;
	}

	@Override
	public TapBlockData getBlockData(int x, int y, int z)
	{
		return NMSBlockSupport.getInstance().wrapBlockData(getHandle().getBlockData(new BlockPosition(x, y, z)));
	}

}
