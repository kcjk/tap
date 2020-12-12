package com.nemosw.spigot.tap.v1_12_R1.entity;

import com.nemosw.spigot.tap.entity.TapLivingEntity;
import com.nemosw.spigot.tap.item.TapItemStack;
import com.nemosw.spigot.tap.v1_12_R1.item.NMSItemStack;
import com.nemosw.spigot.tap.v1_12_R1.item.NMSItemSupport;
import net.minecraft.server.v1_12_R1.Entity;
import net.minecraft.server.v1_12_R1.EntityLiving;
import net.minecraft.server.v1_12_R1.EnumItemSlot;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.EquipmentSlot;

public class NMSLivingEntity extends NMSEntity implements TapLivingEntity
{

    protected final EntityLiving living;

    NMSLivingEntity(Entity entity)
    {
        super(entity);

        this.living = (EntityLiving) entity;
    }

    @Override
    public EntityLiving getHandle()
    {
        return living;
    }

    @Override
    public LivingEntity getBukkitEntity()
    {
        return (LivingEntity) super.getBukkitEntity();
    }

    @Override
    public float getEyeHeight()
    {
        return this.living.getHeadHeight();
    }

    @Override
    public double getHealth()
    {
        return this.living.getHealth();
    }

    @Override
    public NMSItemStack getEquipment(EquipmentSlot slot)
    {
        return NMSItemSupport.wrapItemStack(living.getEquipment(NMSEquipmentSlot.toNMS(slot)));
    }

    @Override
    public void setEquipment(EquipmentSlot slot, TapItemStack item)
    {
        living.setSlot(NMSEquipmentSlot.toNMS(slot), NMSItemSupport.unwrapItemStack(item));
    }

}
