package com.nemosw.spigot.tap.text;

public interface TextSupport
{

    TextComponent fromText(String text);

    TextComponent jsonToComponent(String json);

    TextComponent fromJsonLenient(String json);

}
