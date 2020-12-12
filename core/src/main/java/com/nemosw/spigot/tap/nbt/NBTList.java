package com.nemosw.spigot.tap.nbt;

public interface NBTList
{

    int[] getIntArray(int i);

    void addIntArray(int[] value);

    float getFloat(int i);

    void addFloat(float value);

    double getDouble(int i);

    void addDouble(double value);

    String getString(int i);

    void addString(String value);

    NBTCompound getCompound(int i);

    void addCompound(NBTCompound compound);

    int size();

}
