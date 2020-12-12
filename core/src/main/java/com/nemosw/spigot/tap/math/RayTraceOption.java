package com.nemosw.spigot.tap.math;

public final class RayTraceOption
{
    public static final int STOP_ON_LIQUID = 1;

    public static final int IGNORE_BLOCK_WITHOUT_BOUNDING_BOX = 2;

    public static final int RETURN_LAST_UNCOLLIDABLE_BLOCK = 4;

    public static boolean is(int option, int flag)
    {
        return (option & flag) != 0;
    }

    private RayTraceOption()
    {}
}
