package com.nemosw.spigot.tap.v1_12_R1.block;

import com.nemosw.spigot.tap.block.TapBlockSupport;
import net.minecraft.server.v1_12_R1.Block;
import net.minecraft.server.v1_12_R1.BlockPosition;
import net.minecraft.server.v1_12_R1.IBlockData;
import net.minecraft.server.v1_12_R1.World;
import org.bukkit.craftbukkit.v1_12_R1.CraftWorld;

import java.util.IdentityHashMap;
import java.util.Map;

public final class NMSBlockSupport implements TapBlockSupport
{

    private static NMSBlockSupport instance;

    public static NMSBlockSupport getInstance()
    {
        return instance;
    }

    private final Map<Block, NMSBlock> blocks = new IdentityHashMap<>(512);

    private final Map<IBlockData, NMSBlockData> blockDatas = new IdentityHashMap<>(4096);

    public NMSBlockSupport()
    {
        if (instance != null)
            throw new IllegalStateException();

        instance = this;
    }

    @Override
    public NMSBlock getBlock(int id)
    {
        return wrapBlock(Block.getById(id));
    }

    @Override
    public NMSBlock getBlock(String name)
    {
        return wrapBlock(Block.getByName(name));
    }

    @Override
    public NMSBlockData getBlockData(org.bukkit.World world, int x, int y, int z)
    {
        World nmsWorld = ((CraftWorld) world).getHandle();
        IBlockData blockData = nmsWorld.getType(new BlockPosition(x, y, z));

        return blockData == null ? null : wrapBlockData(blockData);
    }

    public NMSBlock wrapBlock(Block block)
    {
        if (block == null)
            throw new NullPointerException("Block data cannot be null");

        NMSBlock nmsBlock = blocks.get(block);

        if (nmsBlock == null)
            blocks.put(block, nmsBlock = new NMSBlock(block));

        return nmsBlock;
    }

    public NMSBlockData wrapBlockData(IBlockData blockData)
    {
        if (blockData == null)
            throw new NullPointerException("Block data cannot be null");

        NMSBlockData nmsBlockData = blockDatas.get(blockData);

        if (nmsBlockData == null)
            blockDatas.put(blockData, nmsBlockData = new NMSBlockData(blockData));

        return nmsBlockData;
    }

}
