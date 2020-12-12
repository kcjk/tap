package com.nemosw.spigot.tap.event;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Projectile;
import org.bukkit.event.Event;
import org.bukkit.event.block.*;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.enchantment.PrepareItemEnchantEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.hanging.HangingBreakByEntityEvent;
import org.bukkit.event.hanging.HangingPlaceEvent;
import org.bukkit.event.inventory.FurnaceExtractEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.event.vehicle.VehicleEvent;
import org.bukkit.projectiles.ProjectileSource;

import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.Map;

public abstract class EntityExtractor<T extends Event>
{
    private final Map<Class<?>, EntityEventKey> eventKeyByEventClass = new HashMap<>();

    final Class<? extends Event> eventClass;

    @SuppressWarnings("unchecked")
    public EntityExtractor()
    {
        eventClass = (Class<? extends Event>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    EntityEventKey createEventKey(Class<?> eventClass)
    {
        EntityEventKey eventKey = this.eventKeyByEventClass.get(eventClass);

        if (eventKey == null)
            this.eventKeyByEventClass.put(eventClass, eventKey = new EntityEventKey().set(eventClass, this));

        return eventKey;
    }

    public abstract Entity getEntity(T event);

    static final class BlockBreakEventExtractor extends EntityExtractor<BlockBreakEvent>
    {
        public Entity getEntity(BlockBreakEvent event)
        {
            return event.getPlayer();
        }
    }

    static final class BlockDamageEventExtractor extends EntityExtractor<BlockDamageEvent>
    {
        @Override
        public Entity getEntity(BlockDamageEvent event)
        {
            return event.getPlayer();
        }
    }

    static final class BlockIgniteEventExtractor extends EntityExtractor<BlockIgniteEvent>
    {
        public Entity getEntity(BlockIgniteEvent event)
        {
            return event.getIgnitingEntity();
        }
    }

    static final class BlockPlaceEventExtractor extends EntityExtractor<BlockPlaceEvent>
    {
        @Override
        public Entity getEntity(BlockPlaceEvent event)
        {
            return event.getPlayer();
        }
    }

    static final class SignChangeEventExtractor extends EntityExtractor<SignChangeEvent>
    {
        @Override
        public Entity getEntity(SignChangeEvent event)
        {
            return event.getPlayer();
        }
    }

    static final class EnchantItemEventExtractor extends EntityExtractor<EnchantItemEvent>
    {
        @Override
        public Entity getEntity(EnchantItemEvent event)
        {
            return event.getEnchanter();
        }
    }

    static final class PrepareItemEnchantEventExtractor extends EntityExtractor<PrepareItemEnchantEvent>
    {
        @Override
        public Entity getEntity(PrepareItemEnchantEvent event)
        {
            return event.getEnchanter();
        }
    }

    static final class EntityEventExtractor extends EntityExtractor<EntityEvent>
    {
        public Entity getEntity(EntityEvent event)
        {
            return event.getEntity();
        }
    }

    static final class HangingBreakByEntityEventExtractor extends EntityExtractor<HangingBreakByEntityEvent>
    {
        public Entity getEntity(HangingBreakByEntityEvent event)
        {
            return event.getRemover();
        }
    }

    static final class HangingPlaceEventExtractor extends EntityExtractor<HangingPlaceEvent>
    {
        @Override
        public Entity getEntity(HangingPlaceEvent event)
        {
            return event.getPlayer();
        }
    }

    static final class FurnaceExtractEventExtractor extends EntityExtractor<FurnaceExtractEvent>
    {
        @Override
        public Entity getEntity(FurnaceExtractEvent event)
        {
            return event.getPlayer();
        }
    }

    static final class InventoryCloseEventExtractor extends EntityExtractor<InventoryCloseEvent>
    {
        @Override
        public Entity getEntity(InventoryCloseEvent event)
        {
            return event.getPlayer();
        }
    }

    static final class InventoryInteractEventExtractor extends EntityExtractor<InventoryInteractEvent>
    {
        @Override
        public Entity getEntity(InventoryInteractEvent event)
        {
            return event.getWhoClicked();
        }
    }

    static final class InventoryOpenEventExtractor extends EntityExtractor<InventoryOpenEvent>
    {
        @Override
        public Entity getEntity(InventoryOpenEvent event)
        {
            return event.getPlayer();
        }
    }

    static final class PlayerEventExtractor extends EntityExtractor<PlayerEvent>
    {
        @Override
        public Entity getEntity(PlayerEvent event)
        {
            return event.getPlayer();
        }
    }

    static final class VehicleEventExtractor extends EntityExtractor<VehicleEvent>
    {
        @Override
        public Entity getEntity(VehicleEvent event)
        {
            return event.getVehicle();
        }
    }

    public static final class Damager extends EntityExtractor<EntityDamageByEntityEvent>
    {
        @Override
        public Entity getEntity(EntityDamageByEntityEvent event)
        {
            return event.getDamager();
        }
    }

    public static final class EntityDamageByEntity
    {
        public static final class Shooter extends EntityExtractor<EntityDamageByEntityEvent>
        {
            @Override
            public Entity getEntity(EntityDamageByEntityEvent event)
            {
                Entity damager = event.getDamager();

                if (damager instanceof Projectile)
                {
                    ProjectileSource source = ((Projectile) damager).getShooter();

                    if (source instanceof Entity)
                        return (Entity) source;
                }

                return null;
            }
        }
    }

    public static final class ProjectileHit
    {
        public static final class Shooter extends EntityExtractor<ProjectileHitEvent>
        {
            @Override
            public Entity getEntity(ProjectileHitEvent event)
            {
                ProjectileSource shooter = event.getEntity().getShooter();

                return shooter instanceof Entity ? (Entity) shooter : null;
            }
        }
    }
}
