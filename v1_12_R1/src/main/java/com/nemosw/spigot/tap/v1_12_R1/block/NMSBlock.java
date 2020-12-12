package com.nemosw.spigot.tap.v1_12_R1.block;

import com.nemosw.spigot.tap.block.TapBlock;
import com.nemosw.spigot.tap.block.TapBlockData;
import net.minecraft.server.v1_12_R1.Block;

@SuppressWarnings("deprecation")
public final class NMSBlock implements TapBlock
{

	private final Block block;

	NMSBlock(Block block)
	{
		this.block = block;
	}

	public Block getHandle()
    {
        return block;
    }

	@Override
	public String getTextureId()
	{
		return Block.REGISTRY.b(block).toString();
	}

	@Override
	public int getId()
	{
		return Block.getId(block);
	}

	@Override
	public TapBlockData getBlockData()
	{
        return NMSBlockSupport.getInstance().wrapBlockData(block.getBlockData());
	}

	@Override
	public NMSBlockData getBlockData(int data)
	{
        return NMSBlockSupport.getInstance().wrapBlockData(block.fromLegacyData(data));
	}

}
