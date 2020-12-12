package com.nemosw.spigot.tap.math;

import com.github.noonmaru.math.Vector;
import org.bukkit.World;
import org.bukkit.entity.Entity;

import java.util.function.Predicate;

public interface MathSupport
{

    BoundingBox getBoundingBox(Entity entity);

    BoundingBox newBoundingBox(double minX, double minY, double minZ, double maxX, double maxY, double maxZ);

    RayTraceResult rayTraceBlock(World world, Vector from, Vector to, int options);

    RayTraceResult rayTraceEntity(World world, Entity entity, Vector from, Vector to, double expand, Predicate<Entity> selector);

    RayTraceResult rayTrace(World world, Entity entity, Vector from, Vector to, int options, double expand, Predicate<Entity> selector);

    RayTracer newRayTraceCalculator(Vector from, Vector to);

}
