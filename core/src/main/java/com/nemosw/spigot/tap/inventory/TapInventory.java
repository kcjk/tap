package com.nemosw.spigot.tap.inventory;

import com.nemosw.spigot.tap.item.TapItemStack;

import java.util.List;

public interface TapInventory
{

    int getSize();

    int getFirstEmpty();

    TapItemStack getItem(int slot);

    List<? extends TapItemStack> getContents();

    int indexOf(TapItemStack item);

    boolean contains(TapItemStack item);

    void setItem(int slot, TapItemStack item);

    void setContents(List<? extends TapItemStack> contents);

    int canHold(TapItemStack item);

    int addItem(TapItemStack item);

    int removeItem(TapItemStack item);

    void clear();

    org.bukkit.inventory.Inventory getBukkitInventory();

}
