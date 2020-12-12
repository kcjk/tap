package com.nemosw.spigot.tap.packet;

import com.nemosw.spigot.tap.AnimationType;
import com.nemosw.spigot.tap.item.TapItemStack;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.EquipmentSlot;

public interface EntityPacket
{

    Packet animation(Entity entity, AnimationType animation);

    Packet destroy(int... entityIds);

    Packet equipment(int entityId, EquipmentSlot slot, TapItemStack item);

    Packet headRotation(Entity entity, float yaw);

    Packet metadata(Entity entity);

    Packet relativeMove(int entityId, double moveX, double moveY, double moveZ, boolean onGround);

    Packet relativeMoveLook(int entityId, double moveX, double moveY, double moveZ, float yaw, float pitch, boolean onGround);

    Packet spawnMob(LivingEntity entity);

    Packet teleport(Entity entity, double x, double y, double z, float yaw, float pitch, boolean onGround);

}
