package com.nemosw.spigot.tap.debug;

import com.google.common.base.Suppliers;
import com.nemosw.spigot.tap.debug.block.DebugBlock;
import com.nemosw.spigot.tap.debug.text.TextDebug;

import java.util.Collection;
import java.util.Set;
import java.util.TreeMap;
import java.util.function.Supplier;

public final class DebugManager
{

    private final TreeMap<String, DebugModule> modulesByName = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

    public DebugManager()
    {
        registerModule("block", DebugBlock::new);
        registerModule("text", TextDebug::new);
    }

    private void registerModule(String name, Supplier<? extends DebugProcess> processSupplier)
    {
        if (modulesByName.containsKey(name))
            throw new IllegalArgumentException("Name " + name + " is already in use");

        modulesByName.put(name, new DebugModule(name, processSupplier));
    }

    public DebugModule getModule(String moduleName)
    {
        return modulesByName.get(moduleName);
    }

    private final Supplier<Set<String>> moduleNames = Suppliers.memoize(modulesByName::keySet);

    public final Set<String> getModuleNames()
    {
        return moduleNames.get();
    }

    private final Supplier<Collection<DebugModule>> modules = Suppliers.memoize(modulesByName::values);

    public Collection<DebugModule> getModules()
    {
        return modules.get();
    }

}
