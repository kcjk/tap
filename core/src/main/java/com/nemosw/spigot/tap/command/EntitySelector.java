package com.nemosw.spigot.tap.command;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;

import java.util.List;

public interface EntitySelector
{

    <T extends Entity> List<T> matchEntities(CommandSender sender, String token, Class<? extends Entity> target);

}
