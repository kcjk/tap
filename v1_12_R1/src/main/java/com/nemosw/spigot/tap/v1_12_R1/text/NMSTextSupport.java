package com.nemosw.spigot.tap.v1_12_R1.text;

import com.nemosw.spigot.tap.text.TextSupport;
import net.minecraft.server.v1_12_R1.IChatBaseComponent.ChatSerializer;

@SuppressWarnings("deprecation")
public class NMSTextSupport implements TextSupport
{

    @Override
    public NMSTextComponent fromText(String text)
    {
        return fromJsonLenient("{\"text\":\"" + text + "\"}");
    }

    @Override
    public NMSTextComponent jsonToComponent(String json)
    {
        return new NMSTextComponent(ChatSerializer.a(json));
    }

    @Override
    public NMSTextComponent fromJsonLenient(String json)
    {
        return new NMSTextComponent(ChatSerializer.b(json));
    }

}
