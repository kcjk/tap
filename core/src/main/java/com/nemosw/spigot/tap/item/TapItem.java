package com.nemosw.spigot.tap.item;

public interface TapItem
{

    int getId();

    String getName();

    String getUnlocalizedName();

    String getTextureId();

    int getMaxStackSize();

    int getMaxDurability();

    boolean isBlock();

}
