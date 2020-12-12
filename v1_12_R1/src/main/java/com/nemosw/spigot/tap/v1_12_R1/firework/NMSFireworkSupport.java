package com.nemosw.spigot.tap.v1_12_R1.firework;

import com.nemosw.spigot.tap.firework.FireworkEffect;
import com.nemosw.spigot.tap.firework.FireworkSupport;

public class NMSFireworkSupport implements FireworkSupport
{

	@Override
	public FireworkEffect build(FireworkEffect.Builder builder)
	{
		return new NMSFireworkEffect(builder);
	}

}
