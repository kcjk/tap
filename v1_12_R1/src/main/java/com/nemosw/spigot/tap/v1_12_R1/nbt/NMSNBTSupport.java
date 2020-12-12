package com.nemosw.spigot.tap.v1_12_R1.nbt;

import com.nemosw.spigot.tap.nbt.NBTCompound;
import com.nemosw.spigot.tap.nbt.NBTList;
import com.nemosw.spigot.tap.nbt.NBTSupport;
import net.minecraft.server.v1_12_R1.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public final class NMSNBTSupport implements NBTSupport
{
	@Override
	public NBTCompound loadCompound(InputStream in)
	{
		try
		{
			return new NMSNBTCompound(NBTCompressedStreamTools.a(in));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		return null;
	}

    @Override
    public NBTCompound loadCompound(byte[] bytes)
    {
        return loadCompound(new ByteArrayInputStream(bytes));
    }

    @Override
	public NMSNBTCompound newCompound()
	{
		return new NMSNBTCompound(new NBTTagCompound());
	}

	@Override
	public NBTList newList()
	{
		return new NMSNBTList(new NBTTagList());
	}
	
	@Override
	public NMSNBTCompound fromJsonString(String json)
	{
		try
		{
			return new NMSNBTCompound(MojangsonParser.parse(json));
		}
		catch (MojangsonParseException e)
		{
			throw new IllegalArgumentException(e);
		}
	}
}
