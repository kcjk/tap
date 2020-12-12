package com.nemosw.spigot.tap.debug;

import com.nemosw.spigot.tap.command.ArgumentList;
import com.nemosw.spigot.tap.command.CommandComponent;
import com.nemosw.spigot.tap.command.TabSupport;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.Arrays;
import java.util.Collections;

public final class CommandDebug extends CommandComponent
{
    private final DebugManager manager;

    public CommandDebug(DebugManager manager)
    {
        super("<Module> <start | stop>", "Starts or stops debugging.", "tap.debug", 2);

        this.manager = manager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String componentLabel, ArgumentList args)
    {
        String moduleName = args.next();
        DebugModule module = manager.getModule(moduleName);

        if (module == null)
        {
            sender.sendMessage("Not found debug module for " + moduleName);
            return true;
        }

        String state = args.next();

        if (state.equalsIgnoreCase("start"))
        {
            if (!module.start())
                sender.sendMessage("Debug " + module.getName() + " is already running.");
        }
        else if (state.equalsIgnoreCase("stop"))
        {
            if (!module.stop())
                sender.sendMessage("Debug " + module.getName() + " is not running");
        }
        else
        {
            return false;
        }

        return true;
    }

    @Override
    public Iterable<String> onTabComplete(CommandSender sender, Command command, String label, String componentLabel, ArgumentList args)
    {
        String arg = args.next();

        if (!args.hasNext())
            return TabSupport.complete(manager.getModuleNames(), arg);

        arg = args.next();

        if (!args.hasNext())
            return TabSupport.complete(Arrays.asList("start", "stop"), arg);

        return Collections.emptyList();
    }
}
