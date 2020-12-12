package com.nemosw.spigot.tap.v1_12_R1.profile;

import com.mojang.authlib.GameProfile;
import com.nemosw.spigot.tap.profile.Profile;
import com.nemosw.spigot.tap.profile.ProfileSupport;
import net.minecraft.server.v1_12_R1.UserCache;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_12_R1.CraftServer;

import java.util.UUID;

public final class NMSProfileSupport implements ProfileSupport
{

    private final UserCache userCache = ((CraftServer) Bukkit.getServer()).getServer().getUserCache();

    @Override
    public Profile getProfile(String name)
    {
        GameProfile profile = userCache.getProfile(name);

        return profile == null ? null : new NMSProfile(profile);
    }

    @Override
    public Profile getProfile(UUID uniqueId)
    {
        GameProfile profile = userCache.a(uniqueId);

        return profile == null ? null : new NMSProfile(profile);
    }

}
