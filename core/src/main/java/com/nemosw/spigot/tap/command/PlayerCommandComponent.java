package com.nemosw.spigot.tap.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Collections;

public class PlayerCommandComponent extends CommandComponent
{
    public PlayerCommandComponent(String usage, String description)
    {
        super(usage, description, null);
    }

    public PlayerCommandComponent(String usage, String description, String permission)
    {
        super(usage, description, permission, 0);
    }

    public PlayerCommandComponent(String usage, String description, int argumentLength)
    {
        super(usage, description, null, argumentLength);
    }

    public PlayerCommandComponent(String usage, String description, String permission, int argumentLength)
    {
        super(usage, description, permission, argumentLength);
    }

    @Override
    public boolean testPermission(CommandSender sender)
    {
        return sender instanceof Player && super.testPermission(sender);
    }

    @Override
    public String getPermissionMessage(CommandSender sender)
    {
        return sender instanceof Player ? super.getPermissionMessage(sender) : Message.CANNOT_PERFORM_IN_CONSOLE;
    }

    @Override
    public final boolean onCommand(CommandSender sender, Command command, String label, String componentLabel, ArgumentList args)
    {
        return this.onCommand((Player) sender, command, label, componentLabel, args);
    }

    public boolean onCommand(Player sender, Command command, String label, String componentLabel, ArgumentList args)
    {
        return false;
    }

    @Override
    public final Iterable<String> onTabComplete(CommandSender sender, Command command, String label, String componentLabel, ArgumentList args)
    {
        return this.onTabComplete((Player) sender, command, label, componentLabel, args);
    }

    public Iterable<String> onTabComplete(Player sender, Command command, String label, String componentLabel, ArgumentList args)
    {
        return Collections.emptyList();
    }

}
