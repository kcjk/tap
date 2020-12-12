package com.nemosw.spigot.tap.command;

import com.nemosw.spigot.tap.Tap;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.BlockCommandSender;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.List;

public class CommandComponent
{

    public final String usage;

    public final String description;

    public final int argumentsLength;

    public final String permission;

    public CommandComponent(String usage, String description)
    {
        this(usage, description, null, 0);
    }

    public CommandComponent(String usage, String description, int argumentsLength)
    {
        this(usage, description, null, argumentsLength);
    }

    public CommandComponent(String usage, String description, String permission)
    {
        this(usage, description, permission, 0);
    }

    public CommandComponent(String usage, String description, String permission, int argumentsLength)
    {
        this.usage = usage;
        this.description = description;
        this.permission = permission;
        this.argumentsLength = Math.max(0, argumentsLength);
    }

    private static String createMessage(CommandSender sender, String label, String componentLabel, String message)
    {
        return "§7" + sender.getName() + ": §r" + label + " §6" + componentLabel + " §r" + message;
    }

    protected final void broadcast(CommandSender sender, String label, String componentLabel, String message)
    {
        if (sender instanceof BlockCommandSender)
        {
            BlockCommandSender commandBlock = (BlockCommandSender) sender;
            World world = commandBlock.getBlock().getWorld();

            String value = world.getGameRuleValue("commandBlockOutput");

            if (value != null && !Boolean.parseBoolean(value))
                return;
        }
        else if (sender instanceof Player)
        {
            Player player = (Player) sender;
            World world = player.getWorld();

            String value = world.getGameRuleValue("sendCommandFeedback");

            if (value != null && value.equals("false"))
                return;
        }

        message = createMessage(sender, label, componentLabel, message);
        String permission = this.permission;

        if (permission != null)
        {
            Bukkit.getConsoleSender().sendMessage(message);

            for (Player player : Bukkit.getOnlinePlayers())
                if (player.hasPermission(permission))
                    player.sendMessage(message);
        }
        else
            Bukkit.broadcastMessage(message);
    }

    public boolean testPermission(CommandSender sender)
    {
        String permission = this.permission;

        return permission == null || sender.hasPermission(permission);
    }

    public String getPermissionMessage(CommandSender sender)
    {
        String permission = this.permission;

        if (permission == null || sender.hasPermission(permission))
            return null;

        return Message.NO_PERMISSION;
    }

    public static <T extends Entity> T matchOneEntity(CommandSender sender, String token, Class<T> targetClass)
    {
        List<T> entities = matchEntities(sender, token, targetClass);
        return entities.size() == 1 ? entities.get(0) : null;
    }

    public static <T extends Entity> List<T> matchEntities(CommandSender sender, String token, Class<T> targetClass)
    {
        return Tap.ENTITY_SELECTOR.matchEntities(sender, token, targetClass);
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String componentLabel, ArgumentList args)
    {
        return false;
    }

    public Iterable<String> onTabComplete(CommandSender sender, Command command, String label, String componentLabel, ArgumentList args)
    {
        return Collections.emptyList();
    }

}
