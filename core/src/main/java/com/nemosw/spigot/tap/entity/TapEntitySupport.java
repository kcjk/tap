package com.nemosw.spigot.tap.entity;

import org.bukkit.entity.Entity;

public interface TapEntitySupport
{

    <T extends TapEntity> T wrapEntity(Entity entity);

    <T extends TapEntity> T createEntity(Class<? extends Entity> entityClass);

}
