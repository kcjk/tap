package com.nemosw.spigot.tap.item;

import com.nemosw.spigot.tap.Tap;
import com.nemosw.spigot.tap.nbt.NBTCompound;
import org.bukkit.World;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

public interface TapItemStack
{

    TapItem getItem();

    int getId();

    int getAmount();

    boolean isEmpty();

    int getData();

    String getName();

    String getUnlocalizedName();

    String getDisplayName();

    List<String> getLore();

    boolean hasLore(Predicate<String> p);

    String findLore(Predicate<String> p);

    String findLoreLast(Predicate<String> p);

    boolean hasTag();

    NBTCompound getTag();

    boolean hasItemMeta();

    ItemMeta getItemMeta();

    ItemStack toItemStack();

    TapItemStack setItem(TapItem item);

    TapItemStack setItem(int itemId);

    TapItemStack setAmount(int amount);

    TapItemStack setData(int data);

    TapItemStack setDisplayName(String name);

    TapItemStack setLore(List<String> lore);

    TapItemStack addLore(List<String> lore);

    default TapItemStack addLore(String lore)
    {
        return addLore(Collections.singletonList(lore));
    }

    default TapItemStack addLore(String... lore)
    {
        return addLore(Arrays.asList(lore));
    }

    TapItemStack setUnbreakable(boolean flag);

    TapItemStack setHideFlags(int hideFlags);

    TapItemStack setTag(NBTCompound tag);

    TapItemStack setItemMeta(ItemMeta meta);

    Item spawn(World world, double x, double y, double z);

    Item spawnNaturally(World world, double x, double y, double z);

    boolean isSimilar(TapItemStack other);

    TapItemStack copy();

    default NBTCompound save()
    {
        return save(Tap.NBT.newCompound());
    }

    NBTCompound save(NBTCompound compound);

}
