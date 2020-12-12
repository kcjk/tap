package com.nemosw.spigot.tap.v1_12_R1.nbt;

import com.nemosw.spigot.tap.nbt.NBTCompound;
import com.nemosw.spigot.tap.nbt.NBTList;
import net.minecraft.server.v1_12_R1.*;

public final class NMSNBTList implements NBTList
{

    private final NBTTagList list;

    public NMSNBTList(NBTTagList list)
    {
        this.list = list;
    }

    public NBTTagList getHandle()
    {
        return list;
    }

    @Override
    public int[] getIntArray(int i)
    {
        return this.list.d(i);
    }

    @Override
    public void addIntArray(int[] value)
    {
        this.list.add(new NBTTagIntArray(value));
    }

    @Override
    public void addCompound(NBTCompound compound)
    {
        this.list.add(((NMSNBTCompound) compound).getHandle());
    }

    @Override
    public float getFloat(int i)
    {
        return this.list.g(i);
    }

    @Override
    public void addFloat(float value)
    {
        this.list.add(new NBTTagFloat(value));
    }

    @Override
    public double getDouble(int i)
    {
        return this.list.f(i);
    }

    @Override
    public void addDouble(double value)
    {
        this.list.add(new NBTTagDouble(value));
    }

    @Override
    public String getString(int i)
    {
        return this.list.getString(i);
    }

    @Override
    public void addString(String value)
    {
        this.list.add(new NBTTagString(value));
    }

    @Override
    public NMSNBTCompound getCompound(int i)
    {
        return new NMSNBTCompound(this.list.get(i));
    }

    @Override
    public int size()
    {
        return this.list.size();
    }

    @Override
    public int hashCode()
    {
        return this.list.hashCode();
    }

    @Override
    public String toString()
    {
        return this.list.toString();
    }

}
