package com.nemosw.spigot.tap.event;

import com.github.noonmaru.collections.Node;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;

final class RegisteredEntityExecutor
{

    EntityListener listener;

    EntityEventExecutor executor;

    Node<RegisteredEntityExecutor> node;

    RegisteredEntityExecutor(EntityListener listener, EntityEventExecutor executor)
    {
        this.listener = listener;
        this.executor = executor;
    }

    void remove()
    {
        this.executor = null;
        this.node.clear();
        this.node = null;
        this.listener = null;
    }

    void execute(Event event)
    {
        if (this.node == null || (this.executor.ignoreCancelled && event instanceof Cancellable && ((Cancellable) event).isCancelled()))
            return;

        try
        {
            this.executor.execute(this.listener, event);
        }
        catch (Throwable t)
        {
            t.printStackTrace();
        }
    }

}
