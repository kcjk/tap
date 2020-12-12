package com.nemosw.spigot.tap.math;

import com.github.noonmaru.math.Vector;
import org.bukkit.World;
import org.bukkit.entity.Entity;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public interface RayTracer
{
    Vector getFrom(Vector v);

    Vector getFrom();

    RayTracer setFrom(double x, double y, double z);

    RayTracer setFrom(Vector v);

    Vector getTo(Vector v);

    Vector getTo();

    RayTracer setTo(double x, double y, double z);

    RayTracer setTo(Vector v);

    RayTraceResult setToRayTraceBlock(World world, int option);

    double getLength();

    BoundingBox toBox();

    <T extends Entity> List<T> getEntitiesInBox(World world, Entity exclusion, Predicate<Entity> selector);

    RayTraceResult rayTraceBlock(World world, int option);

    RayTraceResult calculate(BoundingBox box);

    RayTraceResult calculate(Entity entity, double expand);

    RayTraceResult rayTraceEntity(World world, Entity exclusion, double expand, Predicate<Entity> selector);

    List<? extends RayTraceResult> rayTraceEntities(World world, Entity exclusion, double expand, Predicate<Entity> selector);

    <T> RayTraceResult rayTraceCustom(Iterable<? extends T> iterable, T exclusion, Function<T, BoundingBox> func);

}
