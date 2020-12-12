package com.nemosw.spigot.tap.v1_12_R1.packet;

import com.nemosw.spigot.tap.packet.TitlePacket;
import com.nemosw.spigot.tap.text.TextComponent;
import com.nemosw.spigot.tap.v1_12_R1.text.NMSTextComponent;
import net.minecraft.server.v1_12_R1.PacketPlayOutTitle;
import net.minecraft.server.v1_12_R1.PacketPlayOutTitle.EnumTitleAction;

public class NMSTitlePacket implements TitlePacket
{

	@Override
	public NMSPacket reset()
	{
		return new NMSPacketFixed(new PacketPlayOutTitle(EnumTitleAction.RESET, null));
	}

	@Override
	public NMSPacket title(TextComponent text)
	{
		return new NMSPacketFixed(new PacketPlayOutTitle(EnumTitleAction.TITLE, ((NMSTextComponent) text).component));
	}

	@Override
	public NMSPacket subtitle(TextComponent text)
	{
		return new NMSPacketFixed(new PacketPlayOutTitle(EnumTitleAction.SUBTITLE, ((NMSTextComponent) text).component));
	}

	@Override
	public NMSPacket show(int fadeIn, int stay, int fadeOut)
	{
		return new NMSPacketFixed(new PacketPlayOutTitle(fadeIn, stay, fadeOut));
	}

	@Override
	public NMSPacket compound(TextComponent title, TextComponent subtitle, int fadeIn, int stay, int fadeOut)
	{
		PacketPlayOutTitle resetPacket = new PacketPlayOutTitle(EnumTitleAction.RESET, null);
		PacketPlayOutTitle titlePacket = new PacketPlayOutTitle(EnumTitleAction.TITLE, ((NMSTextComponent) title).component);
		PacketPlayOutTitle showPacket = new PacketPlayOutTitle(fadeIn, stay, fadeOut);
		
		if (subtitle == null)
			return new NMSPacketMulti(resetPacket, titlePacket, showPacket);
		
		PacketPlayOutTitle subtitlePacket = new PacketPlayOutTitle(EnumTitleAction.SUBTITLE, ((NMSTextComponent) subtitle).component);
		
		return new NMSPacketMulti(resetPacket, titlePacket, subtitlePacket, showPacket);
	}

}
