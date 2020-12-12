package com.nemosw.spigot.tap.event;

import com.github.noonmaru.collections.Node;

public final class RegisteredEntityListener
{

    RegisteredEntityExecutor[] executors;

    Node<RegisteredEntityListener> node;

    RegisteredEntityListener(RegisteredEntityExecutor[] executors)
    {
        this.executors = executors;
    }

    public final void unregister()
    {
        if (this.node != null)
        {
            this.node.unlink();
            this.node = null;

            RegisteredEntityExecutor[] executors = this.executors;
            this.executors = null;

            for (int i = 0, length = executors.length; i < length; i++)
            {
                RegisteredEntityExecutor executor = executors[i];
                executors[i] = null;
                executor.remove();
            }
        }
    }

}
