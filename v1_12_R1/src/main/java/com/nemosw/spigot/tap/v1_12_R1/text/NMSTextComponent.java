package com.nemosw.spigot.tap.v1_12_R1.text;

import com.nemosw.spigot.tap.text.TextComponent;
import net.minecraft.server.v1_12_R1.IChatBaseComponent;

public class NMSTextComponent extends TextComponent
{
	
	public final IChatBaseComponent component;

	public NMSTextComponent(IChatBaseComponent component)
	{
		this.component = component;
	}
	
}
