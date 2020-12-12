package com.nemosw.spigot.tap.v1_12_R1.entity;

import net.minecraft.server.v1_12_R1.*;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_12_R1.CraftServer;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;

import java.util.*;

public final class NMSEntityTypes
{

    private static final Map<Class<? extends org.bukkit.entity.Entity>, Class<? extends Entity>> ENTITIES = new HashMap<>();

    static
    {
        World world = ((CraftServer) Bukkit.getServer()).getServer().getWorld();

        for (MinecraftKey key : EntityTypes.a())
        {
            Entity entity = EntityTypes.a(key, world);

            if (entity != null)
                registerEntity(entity.getBukkitEntity().getClass(), entity.getClass());
        }

        registerEntity(CraftPlayer.class, EntityPlayer.class);
    }

    private static void registerEntity(Class<?> bukkitClass, Class<?> nmsClass)
    {
        Set<Class<?>> bukkitClasses = getEntityClasses(bukkitClass);

        for (Class<?> bukkitEntityClass : bukkitClasses)
        {
            Class<?> nmsEntityClass = nmsClass;
            Class<?> oldNMSEntityClass = ENTITIES.get(bukkitEntityClass.asSubclass(org.bukkit.entity.Entity.class));

            while (Entity.class.isAssignableFrom(nmsEntityClass))
            {
                if (oldNMSEntityClass == null || nmsEntityClass.isAssignableFrom(oldNMSEntityClass))
                {
                    ENTITIES.put(bukkitEntityClass.asSubclass(org.bukkit.entity.Entity.class), nmsEntityClass.asSubclass(Entity.class));
                    break;
                }

                nmsEntityClass = nmsEntityClass.getSuperclass();
            }
        }
    }

    private static Set<Class<?>> getEntityClasses(Class<?> entityClass)
    {
        HashSet<Class<?>> result = new LinkedHashSet<>();

        while (org.bukkit.entity.Entity.class.isAssignableFrom(entityClass))
        {
            result.add(entityClass);
            getEntityInterfaces(result, entityClass.getInterfaces());
            entityClass = entityClass.getSuperclass();
        }

        return result;
    }

    private static void getEntityInterfaces(Set<Class<?>> result, Class<?>[] interfaces)
    {
        for (Class<?> inter : interfaces)
        {
            if (org.bukkit.entity.Entity.class.isAssignableFrom(inter))
            {
                result.add(inter);
                getEntityInterfaces(result, inter.getInterfaces());
            }
        }
    }

    public static Class<? extends Entity> getEntityClass(Class<? extends org.bukkit.entity.Entity> entityClass)
    {
        Class<?> target = entityClass;
        Class<? extends Entity> found;

        do
        {
            found = ENTITIES.get(target);

            if (found != null)
                break;

            target = target.getSuperclass();
        }
        while (org.bukkit.entity.Entity.class.isAssignableFrom(target));

        return found;
    }

    private NMSEntityTypes() {}

}
