package com.nemosw.spigot.tap.block;

import com.nemosw.spigot.tap.Tap;

public interface TapBlock
{

    TapBlock AIR = Tap.BLOCK.getBlock("air");

    String getTextureId();

    int getId();

    TapBlockData getBlockData();

    TapBlockData getBlockData(int data);

}
