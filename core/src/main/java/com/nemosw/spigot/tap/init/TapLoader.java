package com.nemosw.spigot.tap.init;

import com.nemosw.spigot.tap.attach.Tools;
import org.bukkit.plugin.Plugin;

public final class TapLoader
{
    public static void init(Plugin plugin)
    {
        MCConfigAdapters.init();
    }

    public static void load(Plugin  plugin)
    {
        try
        {
            Tools.loadAttachLibrary(plugin.getDataFolder());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private TapLoader() {}
}
