package com.nemosw.spigot.tap.event;

import com.github.noonmaru.collections.mut.LinkedNodeList;
import org.bukkit.event.Event;

import java.util.HashMap;

final class EventEntity
{

    private final LinkedNodeList<RegisteredEntityListener> registeredListeners = new LinkedNodeList<>();

    private final HashMap<EntityEventKey, EventEntityHandler> handlers = new HashMap<>();

    RegisteredEntityListener registerEvents(EntityListener listener, EntityEventExecutor[] executors)
    {
        int length = executors.length;
        RegisteredEntityExecutor[] registereds = new RegisteredEntityExecutor[length];
        HashMap<EntityEventKey, EventEntityHandler> handlers = this.handlers;

        for (int i = 0; i < length; i++)
        {
            EntityEventExecutor executor = executors[i];
            RegisteredEntityExecutor registered = new RegisteredEntityExecutor(listener, executor);
            EntityEventKey key = executor.eventKey;
            EventEntityHandler handler = handlers.get(key);

            if (handler == null)
                handlers.put(key, handler = new EventEntityHandler());

            handler.registerExecutor(registered);

            registereds[i] = registered;
        }

        RegisteredEntityListener registeredListener = new RegisteredEntityListener(registereds);
        registeredListener.node = this.registeredListeners.addNode(registeredListener);

        return registeredListener;
    }

    void handleEvent(EntityEventKey key, Event event)
    {
        EventEntityHandler handler = this.handlers.get(key);

        if (handler != null)
        {
            for (RegisteredEntityExecutor executor : handler.getExecutors())
            {
                executor.execute(event);
            }
        }
    }

    void clear()
    {
        LinkedNodeList<RegisteredEntityListener> registeredListeners = this.registeredListeners;

        while (!registeredListeners.isEmpty())
            registeredListeners.peek().unregister();

        registeredListeners.clear();

        HashMap<EntityEventKey, EventEntityHandler> handlers = this.handlers;

        for (EventEntityHandler handler : handlers.values())
            handler.clear();

        handlers.clear();
    }
}
