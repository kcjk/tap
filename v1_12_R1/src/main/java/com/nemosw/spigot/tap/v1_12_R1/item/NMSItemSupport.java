package com.nemosw.spigot.tap.v1_12_R1.item;


import com.google.common.collect.Iterables;
import com.nemosw.spigot.tap.item.TapItem;
import com.nemosw.spigot.tap.item.TapItemStack;
import com.nemosw.spigot.tap.item.TapItemSupport;
import com.nemosw.spigot.tap.nbt.NBTCompound;
import com.nemosw.spigot.tap.v1_12_R1.nbt.NMSNBTCompound;
import net.minecraft.server.v1_12_R1.Item;
import net.minecraft.server.v1_12_R1.ItemStack;
import net.minecraft.server.v1_12_R1.MinecraftKey;
import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;

import java.util.IdentityHashMap;
import java.util.Map;

public final class NMSItemSupport implements TapItemSupport
{

    private static NMSItemSupport instance;

    public static NMSItemSupport getInstance()
    {
        return instance;
    }

    private final Map<Item, NMSItem> items = new IdentityHashMap<>(32768);

    public NMSItemSupport()
    {
        if (instance != null)
            throw new IllegalStateException();

        instance = this;
    }

    public NMSItem wrapItem(Item item)
    {
        if (item == null)
            return null;

        NMSItem nmsItem = items.get(item);

        if (nmsItem == null)
            items.put(item, nmsItem = new NMSItem(item));

        return nmsItem;
    }

    @Override
    public NMSItem getItem(int id)
    {
        return wrapItem(Item.getById(id));
    }

    @Override
    public NMSItem getItem(String name)
    {
        return wrapItem(Item.REGISTRY.get(new MinecraftKey(name)));
    }

    @Override
    public Iterable<NMSItem> getItems()
    {
        return Iterables.transform(Item.REGISTRY, this::wrapItem);
    }

    @Override
    public NMSItemStack newItemStack(TapItem item, int amount, int data)
    {
        return new NMSItemStack(new ItemStack(((NMSItem) item).getHandle(), amount, data));
    }

    @Override
    public NMSItemStack fromItemStack(org.bukkit.inventory.ItemStack bukkitItemStack)
    {
        return wrapItemStack(CraftItemStack.asNMSCopy(bukkitItemStack));
    }

    @Override
    public NMSItemStack loadItemStack(NBTCompound compound)
    {
        return wrapItemStack(new ItemStack(((NMSNBTCompound) compound).getHandle()));
    }

    public static NMSItemStack wrapItemStack(ItemStack itemStack)
    {
        return itemStack == null || itemStack.isEmpty() ? NMSItemStack.EMPTY : new NMSItemStack(itemStack);
    }

    public static ItemStack unwrapItemStack(TapItemStack nmsItemStack)
    {
        return nmsItemStack == null || nmsItemStack.isEmpty() ? ItemStack.a : ((NMSItemStack) nmsItemStack).getHandle();
    }

}
