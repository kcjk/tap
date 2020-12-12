package com.nemosw.spigot.tap.event;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Event;
import org.bukkit.event.EventPriority;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.plugin.EventExecutor;
import org.bukkit.plugin.Plugin;

import java.util.*;

public final class EntityEventManager
{

    private static final EventListenerExecutor EXECUTOR = new EventListenerExecutor();

    private final Plugin plugin;

    private final Map<Class<? extends Event>, EventListener> registeredListeners = new HashMap<>();

    private final Map<Class<? extends EntityListener>, EntityEventExecutor[]> registeredExecutors = new HashMap<>();

    private final Map<Entity, EventEntity> entities = new WeakHashMap<>();

    public EntityEventManager(Plugin plugin)
    {
        this.plugin = plugin;

        ASMEventExecutor.registerEvents(new EntityEventListener(this), plugin);
    }

    private EntityEventExecutor[] createExecutor(Class<? extends EntityListener> clazz)
    {
        EntityEventExecutor[] executors = this.registeredExecutors.get(clazz);

        if (executors == null)
        {
            this.registeredExecutors.put(clazz, executors = ASMEntityEventExecutor.createExecutors(clazz));

            for (EntityEventExecutor executor : executors)
            {
                Class<? extends Event> handlerClass = executor.handlerClass;
                EventListener listener = this.registeredListeners.get(handlerClass);

                if (listener == null)
                {
                    listener = new EventListener();
                    this.plugin.getServer().getPluginManager().registerEvent(handlerClass, listener, EventPriority.HIGH, EXECUTOR, this.plugin, false);
                    this.plugin.getLogger().info("Entity listener registered: " + handlerClass.getName());
                    this.registeredListeners.put(handlerClass, listener);
                }

                listener.addExtractor(executor.entityExtractor);
            }
        }

        return executors;
    }

    public void registerListener(Class<? extends EntityListener> clazz)
    {
        createExecutor(clazz);
    }

    public RegisteredEntityListener registerEvents(Entity entity, EntityListener listener)
    {
        if (entity == null)
            throw new NullPointerException("Entity cannot be null");
        if (listener == null)
            throw new NullPointerException("Listener cannot be null");

        EntityEventExecutor[] executors = createExecutor(listener.getClass());

        EventEntity eventEntity = this.entities.get(entity);

        if (eventEntity == null)
            this.entities.put(entity, eventEntity = new EventEntity());

        return eventEntity.registerEvents(listener, executors);
    }

    void handleEvent(Entity entity, EntityEventKey key, Event event)
    {
        EventEntity eventEntity = this.entities.get(entity);

        if (eventEntity != null)
            eventEntity.handleEvent(key, event);
    }

    void removeEntity(LivingEntity entity)
    {
        EventEntity eventEntity = this.entities.remove(entity);

        if (eventEntity != null)
            eventEntity.clear();
    }

    public void unregisterAll()
    {
        for (EventListener listener : this.registeredListeners.values())
            HandlerList.unregisterAll(listener);

        this.registeredListeners.clear();
        this.registeredExecutors.clear();
        this.entities.clear();
    }

    static final EntityEventKey EVENT_KEY = new EntityEventKey();

    private static class EventListenerExecutor implements EventExecutor
    {
        @Override
        public void execute(Listener listener, Event event)
        {
            ((EventListener) listener).onEvent(event);
        }
    }

    private class EventListener implements Listener
    {
        private final Set<EntityExtractor<? extends Event>> entityExtractors = new LinkedHashSet<>();
        private EntityExtractor<? extends Event>[] baked;

        public void addExtractor(EntityExtractor<? extends Event> extractor)
        {
            if (this.entityExtractors.add(extractor))
                this.baked = null;
        }

        @SuppressWarnings({"unchecked", "rawtypes"})
        public void onEvent(Event event)
        {
            if (this.baked == null)
                this.baked = this.entityExtractors.toArray(new EntityExtractor[0]);

            Class<? extends Event> eventClass = event.getClass();

            for (EntityExtractor entityExtractor : this.baked)
            {
                if (entityExtractor.eventClass.isAssignableFrom(eventClass))
                {
                    Entity entity = entityExtractor.getEntity(event);

                    if (entity != null)
                        handleEvent(entity, EVENT_KEY.set(eventClass, entityExtractor), event);
                }
            }
        }
    }

}
