package com.nemosw.spigot.tap.v1_12_R1.block;

import com.nemosw.spigot.tap.block.TapBlockData;
import net.minecraft.server.v1_12_R1.IBlockData;

public final class NMSBlockData implements TapBlockData
{

    private final IBlockData blockData;

    NMSBlockData(IBlockData blockData)
    {
        this.blockData = blockData;
    }

    public IBlockData getHandle()
    {
        return blockData;
    }

    @Override
    public NMSBlock getBlock()
    {
        return NMSBlockSupport.getInstance().wrapBlock(blockData.getBlock());
    }

    @Override
    public final int toLegacyData()
    {
        return blockData.getBlock().toLegacyData(blockData);
    }

    public IBlockData getBlockData()
    {
        return blockData;
    }

}
