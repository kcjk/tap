package com.nemosw.spigot.tap.debug;

import org.bukkit.Bukkit;
import org.bukkit.Server;

import java.util.function.Supplier;

public final class DebugModule
{

    private final String name;

    private final Supplier<? extends DebugProcess> processSupplier;

    private DebugProcess process;

    DebugModule(String name, Supplier<? extends DebugProcess> processSupplier)
    {
        this.name = name;
        this.processSupplier = processSupplier;
    }

    public String getName()
    {
        return name;
    }

    public DebugProcess getProcess()
    {
        return process;
    }

    public final boolean start()
    {
        if (process != null)
            return false;

        DebugProcess process = null;

        try
        {
            process = processSupplier.get();
            process.start(this);
        }
        catch (Exception e)
        {
            e.printStackTrace();

            if (process != null)
                process.clear();

            return false;
        }

        this.process = process;
        Bukkit.broadcast("Debug process §6§l" + this.name + " §rhas started", Server.BROADCAST_CHANNEL_ADMINISTRATIVE);

        return true;
    }

    public final boolean stop()
    {
        if (this.process == null)
            return false;

        process.stop();
        process = null;
        Bukkit.broadcast("Debug process §6§l" + name + " §rhas stopped", Server.BROADCAST_CHANNEL_ADMINISTRATIVE);

        return true;
    }

    public boolean isRunning()
    {
        return this.process != null && process.isRunning();
    }

}
