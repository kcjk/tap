package com.nemosw.spigot.tap.item;

import com.nemosw.spigot.tap.nbt.NBTCompound;
import org.bukkit.inventory.ItemStack;

public interface TapItemSupport
{

    TapItem getItem(int id);

    TapItem getItem(String name);

    Iterable<? extends TapItem> getItems();

    TapItemStack newItemStack(TapItem item, int amount, int data);

    default TapItemStack newItemStack(int id, int amount, int data)
    {
        return newItemStack(getItem(id), amount, data);
    }

    default TapItemStack newItemStack(String name, int amount, int data)
    {
        return newItemStack(getItem(name), amount, data);
    }

    TapItemStack fromItemStack(ItemStack itemStack);

    TapItemStack loadItemStack(NBTCompound compound);

}
