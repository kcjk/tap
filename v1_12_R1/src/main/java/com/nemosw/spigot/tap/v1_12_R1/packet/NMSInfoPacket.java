package com.nemosw.spigot.tap.v1_12_R1.packet;

import com.nemosw.spigot.tap.ChatType;
import com.nemosw.spigot.tap.packet.InfoPacket;
import com.nemosw.spigot.tap.text.TextComponent;
import com.nemosw.spigot.tap.v1_12_R1.text.NMSTextComponent;
import com.github.noonmaru.tools.reflection.ReflectionUtils;
import net.minecraft.server.v1_12_R1.ChatMessageType;
import net.minecraft.server.v1_12_R1.PacketPlayOutChat;
import net.minecraft.server.v1_12_R1.PacketPlayOutPlayerListHeaderFooter;

import java.lang.reflect.Field;

public class NMSInfoPacket implements InfoPacket
{

	private static final ChatMessageType[] CHAT_MESSAGE_TYPES;
	
	private static final Field FIELD_HEADER = ReflectionUtils.findPrivateField(PacketPlayOutPlayerListHeaderFooter.class, "a");
	
	private static final Field FIELD_FOOTER = ReflectionUtils.findPrivateField(PacketPlayOutPlayerListHeaderFooter.class, "b");

	static
	{
		ChatMessageType[] chatMessageTypes = new ChatMessageType[Math.max(ChatType.values().length, ChatMessageType.values().length)];
		chatMessageTypes[ChatType.CHAT.ordinal()] = ChatMessageType.CHAT;
		chatMessageTypes[ChatType.SYSTEM.ordinal()] = ChatMessageType.SYSTEM;
		chatMessageTypes[ChatType.GAME_INFO.ordinal()] = ChatMessageType.GAME_INFO;
		CHAT_MESSAGE_TYPES = chatMessageTypes;
	}

	@Override
	public NMSPacket chat(TextComponent text, ChatType type)
	{
		return new NMSPacketFixed(new PacketPlayOutChat(((NMSTextComponent) text).component, CHAT_MESSAGE_TYPES[type.ordinal()]));
	}

	@Override
	public NMSPacket playerListHeaderFooter(TextComponent header, TextComponent footer)
	{
		PacketPlayOutPlayerListHeaderFooter packet = new PacketPlayOutPlayerListHeaderFooter();
		
		try
		{
			FIELD_HEADER.set(packet, ((NMSTextComponent) header).component);
			FIELD_FOOTER.set(packet, ((NMSTextComponent) footer).component);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return new NMSPacketFixed(packet);
	}

}
