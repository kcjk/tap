package com.nemosw.spigot.tap.v1_12_R1.entity;

import com.google.common.collect.MapMaker;
import com.nemosw.spigot.tap.entity.TapEntity;
import com.nemosw.spigot.tap.entity.TapEntitySupport;
import net.minecraft.server.v1_12_R1.*;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_12_R1.CraftServer;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftEntity;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public final class NMSEntitySupport implements TapEntitySupport
{

    private static NMSEntitySupport instance;

    public static NMSEntitySupport getInstance()
    {
        return instance;
    }

    private final Map<Class<?>, Function<Entity, ? extends NMSEntity>> wrappers = new HashMap<>();

    private final Map<Entity, NMSEntity> entities = new MapMaker().weakValues().makeMap();

    public NMSEntitySupport()
    {
        if (instance != null)
            throw new IllegalStateException();

        registerWrapper(Entity.class, NMSEntity::new);
        registerWrapper(EntityLiving.class, NMSLivingEntity::new);
        registerWrapper(EntityArmorStand.class, NMSArmorStand::new);
        registerWrapper(EntityPlayer.class, NMSPlayer::new);
    }

    private void registerWrapper(Class<? extends Entity> entityClass, Function<Entity, ? extends NMSEntity> wrapper)
    {
        wrappers.put(entityClass, wrapper);
    }

    private Function<Entity, ? extends NMSEntity> getWrapper(Class<?> clazz)
    {
        Function<Entity, ? extends NMSEntity> wrapper;

        do
        {
            wrapper = wrappers.get(clazz);

            if (wrapper != null)
                break;

            clazz = clazz.getSuperclass();
        }
        while (Entity.class.isAssignableFrom(clazz));

        return wrapper;
    }

    @SuppressWarnings("unchecked")
    public <T extends TapEntity> T wrapEntity(Entity entity)
    {
        if (entity == null)
            throw new NullPointerException("Entity cannot be null");

        NMSEntity nmsEntity = entities.get(entity);

        if (nmsEntity == null)
            entities.put(entity, nmsEntity = getWrapper(entity.getClass()).apply(entity));

        return (T) nmsEntity;
    }

    @Override
    public <T extends TapEntity> T wrapEntity(org.bukkit.entity.Entity entity)
    {
        return wrapEntity(((CraftEntity) entity).getHandle());
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends TapEntity> T createEntity(Class<? extends org.bukkit.entity.Entity> entityClass)
    {
        Class<? extends Entity> nmsEntityClass = NMSEntityTypes.getEntityClass(entityClass);

        if (nmsEntityClass != null)
            return wrapEntity(EntityTypes.a(nmsEntityClass, ((CraftServer) Bukkit.getServer()).getServer().getWorld()));

        throw new NullPointerException();
    }

}
