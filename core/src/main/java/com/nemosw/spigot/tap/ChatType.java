package com.nemosw.spigot.tap;

public enum ChatType
{
    CHAT((byte) 0), SYSTEM((byte) 1), GAME_INFO((byte) 2);

    private final byte id;

    ChatType(byte id)
    {
        this.id = id;
    }

    public byte getId()
    {
        return this.id;
    }
}
