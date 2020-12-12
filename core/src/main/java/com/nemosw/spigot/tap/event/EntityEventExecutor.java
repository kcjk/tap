package com.nemosw.spigot.tap.event;

import org.bukkit.event.Event;

public abstract class EntityEventExecutor
{
    final Class<? extends Event> eventClass;
    final Class<? extends Event> handlerClass;
    final EntityExtractor<? extends Event> entityExtractor;
    final EntityEventKey eventKey;
    final EntityEventPriority priority;
    final boolean ignoreCancelled;

    public EntityEventExecutor(Class<? extends Event> eventClass, Class<? extends Event> handlerClass, EntityExtractor<? extends Event> entityExtractor,
                               EntityEventPriority priority, boolean ignoreCancelled
    )
    {
        this.eventClass = eventClass;
        this.handlerClass = handlerClass;
        this.entityExtractor = entityExtractor;
        this.eventKey = entityExtractor.createEventKey(eventClass);
        this.priority = priority;
        this.ignoreCancelled = ignoreCancelled;
    }

    public Class<?> getEventClass()
    {
        return this.eventClass;
    }

    public Class<?> getHandlerClass()
    {
        return this.handlerClass;
    }

    public abstract void execute(EntityListener listener, Event event);
}
