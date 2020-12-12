package com.nemosw.spigot.tap.event;

import com.github.noonmaru.collections.mut.EventNodeList;
import com.github.noonmaru.collections.Node;

import java.util.ArrayList;
import java.util.EnumMap;

final class EventEntityHandler implements EventNodeList.NodeListener<RegisteredEntityExecutor>
{

    private final EnumMap<EntityEventPriority, EventNodeList<RegisteredEntityExecutor>> executorsByPriority = new EnumMap<>(EntityEventPriority.class);

    private RegisteredEntityExecutor[] executors;

    void registerExecutor(RegisteredEntityExecutor registered)
    {
        EnumMap<EntityEventPriority, EventNodeList<RegisteredEntityExecutor>> executorsByPriority = this.executorsByPriority;
        EntityEventExecutor executor = registered.executor;
        EntityEventPriority priority = executor.priority;
        EventNodeList<RegisteredEntityExecutor> registeredExecutors = executorsByPriority.get(priority);

        if (registeredExecutors == null)
        {
            executorsByPriority.put(priority, registeredExecutors = new EventNodeList<>());
            registeredExecutors.registerListener(EventNodeList.EventType.UNLINK, this);
        }

        registered.node = registeredExecutors.addNode(registered);
        this.executors = null;
    }

    @Override
    public void onEvent(EventNodeList.EventType type, Node<RegisteredEntityExecutor> node, RegisteredEntityExecutor item)
    {
        this.executors = null;
    }

    public RegisteredEntityExecutor[] getExecutors()
    {
        RegisteredEntityExecutor[] executors = this.executors;

        if (executors == null)
        {
            ArrayList<RegisteredEntityExecutor> list = new ArrayList<>();

            for (EventNodeList<RegisteredEntityExecutor> executorList : this.executorsByPriority.values())
                list.addAll(executorList);

            this.executors = executors = list.toArray(new RegisteredEntityExecutor[0]);
        }

        return executors;
    }

    void clear()
    {
        this.executorsByPriority.clear();
        this.executors = null;
    }

}
