package com.nemosw.spigot.tap.v1_12_R1.entity;

import net.minecraft.server.v1_12_R1.EnumItemSlot;
import org.bukkit.inventory.EquipmentSlot;

public final class NMSEquipmentSlot
{

    private static final EnumItemSlot[] EQUIPMENT_SLOTS;

    static
    {
        EnumItemSlot[] slots = new EnumItemSlot[EnumItemSlot.values().length];
        slots[EquipmentSlot.CHEST.ordinal()] = EnumItemSlot.CHEST;
        slots[EquipmentSlot.FEET.ordinal()] = EnumItemSlot.FEET;
        slots[EquipmentSlot.HEAD.ordinal()] = EnumItemSlot.HEAD;
        slots[EquipmentSlot.LEGS.ordinal()] = EnumItemSlot.LEGS;
        slots[EquipmentSlot.HAND.ordinal()] = EnumItemSlot.MAINHAND;
        slots[EquipmentSlot.OFF_HAND.ordinal()] = EnumItemSlot.OFFHAND;
        EQUIPMENT_SLOTS = slots;
    }

    public static EnumItemSlot toNMS(EquipmentSlot slot)
    {
        return EQUIPMENT_SLOTS[slot.ordinal()];
    }

    private NMSEquipmentSlot() {}
}
