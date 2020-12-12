package com.nemosw.spigot.tap.inventory;

import com.nemosw.spigot.tap.entity.TapPlayer;
import com.nemosw.spigot.tap.item.TapItemStack;

import java.util.List;

public interface TapPlayerInventory extends TapInventory
{

    List<? extends TapItemStack> getInventoryContents();

    TapPlayer getHolder();

    TapItemStack getHelmet();

    TapItemStack getChestplate();

    TapItemStack getLeggings();

    TapItemStack getBoots();

    List<? extends TapItemStack> getArmorContents();

    TapItemStack getHeldItemMainHand();

    void setHeldItemMainHand(TapItemStack item);

    TapItemStack getHeldItemOffHand();

    void setHeldItemOffHand(TapItemStack item);

    TapItemStack getCursor();

    int getHeldItemSlot();

    default boolean pickup(TapItemStack item)
    {
        return addItem(item) == item.getAmount();
    }

    void setHelmet(TapItemStack helmet);

    void setChestplate(TapItemStack chestplate);

    void setLeggins(TapItemStack leggins);

    void setBoots(TapItemStack boots);

    void setCursor(TapItemStack cursor);

    void setArmorContents(List<? extends TapItemStack> armors);

    void setHeldItemSlot(int slot);

    void update();

    void update(int slot);

    void updateCursor();

}
