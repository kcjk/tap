package com.nemosw.spigot.tap.v1_12_R1.profile;

import com.mojang.authlib.GameProfile;
import com.nemosw.spigot.tap.profile.Profile;

import java.util.UUID;

public final class NMSProfile implements Profile
{

	private final GameProfile profile;

	NMSProfile(GameProfile profile)
	{
		this.profile = profile;
	}

	@Override
	public UUID getUniqueId()
	{
		return this.profile.getId();
	}

	@Override
	public String getName()
	{
		return this.profile.getName();
	}

	@Override
	public boolean isLegacy()
	{
		return this.profile.isLegacy();
	}

    public GameProfile getHandle()
    {
        return profile;
    }

	@Override
	public String toString()
	{
		return this.profile.toString();
	}


}
