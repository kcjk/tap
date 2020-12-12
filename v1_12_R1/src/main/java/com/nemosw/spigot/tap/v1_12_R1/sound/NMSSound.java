package com.nemosw.spigot.tap.v1_12_R1.sound;

import com.nemosw.spigot.tap.sound.Sound;
import net.minecraft.server.v1_12_R1.SoundEffect;

public class NMSSound extends Sound
{
	
	private final SoundEffect soundEffect;
	
	protected NMSSound(String name, SoundEffect soundEffect)
	{
		super(name);
		
		this.soundEffect = soundEffect;
	}

	public SoundEffect getHandle()
    {
        return soundEffect;
    }

}
