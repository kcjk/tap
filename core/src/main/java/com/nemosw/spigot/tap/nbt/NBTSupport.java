package com.nemosw.spigot.tap.nbt;

import java.io.InputStream;

public interface NBTSupport
{

    NBTCompound loadCompound(InputStream in);

    NBTCompound loadCompound(byte[] bytes);

    NBTCompound fromJsonString(String json);

    NBTCompound newCompound();

    NBTList newList();

}
