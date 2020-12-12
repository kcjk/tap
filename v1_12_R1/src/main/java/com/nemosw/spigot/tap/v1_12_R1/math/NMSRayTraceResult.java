package com.nemosw.spigot.tap.v1_12_R1.math;

import com.nemosw.spigot.tap.math.BlockPoint;
import com.nemosw.spigot.tap.math.RayTraceResult;
import net.minecraft.server.v1_12_R1.BlockPosition;
import net.minecraft.server.v1_12_R1.Entity;
import net.minecraft.server.v1_12_R1.EnumDirection;
import net.minecraft.server.v1_12_R1.MovingObjectPosition;
import org.bukkit.block.BlockFace;

public class NMSRayTraceResult implements RayTraceResult
{

    private final MovingObjectPosition result;

    public NMSRayTraceResult(MovingObjectPosition result)
    {
        this.result = result;
    }

    public MovingObjectPosition getHandle()
    {
        return result;
    }

    @Override
    public double getX()
    {
        return result.pos.x;
    }

    @Override
    public double getY()
    {
        return result.pos.y;
    }

    @Override
    public double getZ()
    {
        return result.pos.z;
    }

    @Override
    public BlockPoint getBlockPoint()
    {
        BlockPosition pos = result.a();

        return pos == null ? null : new BlockPoint(pos.getX(), pos.getY(), pos.getZ());
    }

    @Override
    public BlockFace getFace()
    {
        EnumDirection direction = this.result.direction;

        if (direction == null)
            return null;

        switch (direction)
        {
            case DOWN:
                return BlockFace.DOWN;
            case EAST:
                return BlockFace.EAST;
            case NORTH:
                return BlockFace.NORTH;
            case SOUTH:
                return BlockFace.SOUTH;
            case UP:
                return BlockFace.UP;
            case WEST:
                return BlockFace.WEST;
            default:
                return null;
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends org.bukkit.entity.Entity> T getEntity()
    {
        Entity entity = result.entity;

        return (T) (entity == null ? null : entity.getBukkitEntity());
    }

    @Override
    public <T> T getCustom()
    {
        return null;
    }

}
