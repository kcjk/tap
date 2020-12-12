package com.nemosw.spigot.tap.entity;

import com.nemosw.spigot.tap.item.TapItemStack;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.EquipmentSlot;

public interface TapLivingEntity extends TapEntity
{

    LivingEntity getBukkitEntity();

    float getEyeHeight();

    double getHealth();

    TapItemStack getEquipment(EquipmentSlot slot);

    void setEquipment(EquipmentSlot slot, TapItemStack item);

}
