package com.nemosw.spigot.tap.v1_12_R1.sound;

import com.nemosw.spigot.tap.sound.Sound;
import com.nemosw.spigot.tap.sound.SoundSupport;
import net.minecraft.server.v1_12_R1.MinecraftKey;
import net.minecraft.server.v1_12_R1.SoundEffect;

public final class NMSSoundSupport implements SoundSupport
{
	
	@Override
	public Sound fromName(String name)
	{
		MinecraftKey key = new MinecraftKey(name);
		SoundEffect soundEffect = SoundEffect.a.get(key);
		
		if (soundEffect == null)
			soundEffect = new SoundEffect(key);
		
		return new NMSSound(name, soundEffect);
	}
	
}
