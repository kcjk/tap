package com.nemosw.spigot.tap.entity;

import com.nemosw.spigot.tap.math.BoundingBox;
import org.bukkit.entity.Entity;

public interface TapEntity
{

    Entity getBukkitEntity();

    int getId();

    float getWidth();

    float getHeight();

    double getPrevX();

    double getPrevY();

    double getPrevZ();

    double getPosX();

    double getPosY();

    double getPosZ();

    float getYaw();

    float getPitch();

    boolean isInvisible();

    boolean isGlowing();

    boolean isOnGround();

    boolean isDead();

    BoundingBox getBoundingBox();

    void setPosition(double x, double y, double z);

    void setPositionAndRotation(double x, double y, double z, float yaw, float pitch);

    void setCustomName(String name);

    void setCustomNameVisible(boolean visible);

    void setInvisible(boolean invisible);

    void setGlowing(boolean glowing);

    void setGravity(boolean gravity);

}
