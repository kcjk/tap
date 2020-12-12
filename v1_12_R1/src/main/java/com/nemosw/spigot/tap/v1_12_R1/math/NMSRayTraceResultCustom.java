package com.nemosw.spigot.tap.v1_12_R1.math;

import net.minecraft.server.v1_12_R1.MovingObjectPosition;

public class NMSRayTraceResultCustom extends NMSRayTraceResult
{

    private final Object custom;

    public NMSRayTraceResultCustom(MovingObjectPosition result, Object custom)
    {
        super(result);

        this.custom = custom;
    }

    @Override
    public <T> T getCustom()
    {
        return (T) custom;
    }

}
