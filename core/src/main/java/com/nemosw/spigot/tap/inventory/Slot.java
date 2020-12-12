package com.nemosw.spigot.tap.inventory;

public final class Slot
{

    private static final int[] RAW_SLOTS = new int[40];

    static
    {
        for (int i = 0; i < 40; i++)
        {
            int slot;

            if (i < 9)
                slot = i + 36;
            else if (i >= 36)
                slot = i - 36;
            else
                slot = i;

            RAW_SLOTS[i] = slot;
        }
    }

    public static int getRawSlot(int slot)
    {
        return RAW_SLOTS[slot];
    }

    private Slot()
    {}

}
