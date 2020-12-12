package com.nemosw.spigot.tap.packet;

import com.nemosw.spigot.tap.ChatType;
import com.nemosw.spigot.tap.Tap;
import com.nemosw.spigot.tap.text.TextComponent;

public interface InfoPacket
{

    Packet chat(TextComponent text, ChatType type);

    default Packet chat(String text, ChatType type)
    {
        return chat(Tap.TEXT.fromText(text), type);
    }

    Packet playerListHeaderFooter(TextComponent header, TextComponent footer);

    default Packet playerListHeaderFooter(String header, String footer)
    {
        return playerListHeaderFooter(Tap.TEXT.fromText(header), Tap.TEXT.fromText(footer));
    }

}
