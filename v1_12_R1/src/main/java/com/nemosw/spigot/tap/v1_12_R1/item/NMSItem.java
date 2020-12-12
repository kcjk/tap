package com.nemosw.spigot.tap.v1_12_R1.item;

import com.nemosw.spigot.tap.item.TapItem;
import net.minecraft.server.v1_12_R1.Item;
import net.minecraft.server.v1_12_R1.ItemBlock;

public final class NMSItem implements TapItem
{

    private final Item item;

    NMSItem(Item item)
    {
        this.item = item;
    }

    @Override
    public int getId()
    {
        return Item.getId(item);
    }

    @Override
    public String getName()
    {
        return item.getName();
    }

    @Override
    public String getUnlocalizedName()
    {
        return getName() + ".name";
    }

    @Override
    public String getTextureId()
    {
        return Item.REGISTRY.b(item).toString();
    }

    @Override
    public int getMaxStackSize()
    {
        return item.getMaxStackSize();
    }

    @Override
    public int getMaxDurability()
    {
        return item.getMaxDurability();
    }

    @Override
    public boolean isBlock()
    {
        return item instanceof ItemBlock;
    }

    public Item getHandle()
    {
        return item;
    }

}
