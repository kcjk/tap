package com.nemosw.spigot.tap.math;

import com.github.noonmaru.math.Vector;
import org.bukkit.World;
import org.bukkit.entity.Entity;

import java.util.List;
import java.util.function.Predicate;

public interface BoundingBox
{

    double getMaxX();

    double getMaxY();

    double getMaxZ();

    double getMinX();

    double getMinY();

    double getMinZ();

    BoundingBox move(double x, double y, double z);

    BoundingBox addCoord(double x, double y, double z);

    BoundingBox expand(double x, double y, double z);

    BoundingBox expand(double v);

    BoundingBox contract(double x, double y, double z);

    BoundingBox contract(double v);

    boolean intersectWith(BoundingBox box);

    boolean intersectWith(Entity entity);

    boolean isInside(double x, double y, double z);

    default boolean isInside(Vector v)
    {
        return isInside(v.x, v.y, v.z);
    }

    <T extends Entity> List<T> getEntities(World world, Entity exclude, Predicate<Entity> selector);

    RayTraceResult calculateRayTrace(Vector from, Vector to);

    BoundingBox copy();

}
