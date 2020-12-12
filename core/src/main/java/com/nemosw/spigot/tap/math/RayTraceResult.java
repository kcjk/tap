package com.nemosw.spigot.tap.math;

import org.bukkit.block.BlockFace;
import org.bukkit.entity.Entity;


public interface RayTraceResult
{

    double getX();

    double getY();

    double getZ();

    BlockPoint getBlockPoint();

    BlockFace getFace();

    <T extends Entity> T getEntity();

    <T> T getCustom();

}
