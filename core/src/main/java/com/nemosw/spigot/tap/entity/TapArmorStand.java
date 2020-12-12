package com.nemosw.spigot.tap.entity;

import com.github.noonmaru.math.Vector;
import org.bukkit.entity.ArmorStand;

public interface TapArmorStand extends TapLivingEntity
{

    @Override
    ArmorStand getBukkitEntity();

    Vector getHeadPos();

    void setHeadPose(float x, float y, float z);

    void setBodyPose(float x, float y, float z);

    void setLeftArmPose(float x, float y, float z);

    void setRightArmPose(float x, float y, float z);

    void setLeftLegPose(float x, float y, float z);

    void setRightLegPose(float x, float y, float z);

}
