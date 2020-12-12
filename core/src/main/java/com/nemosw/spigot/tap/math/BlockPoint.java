package com.nemosw.spigot.tap.math;

public final class BlockPoint
{
    public int x, y, z;

    public BlockPoint()
    {}

    public BlockPoint(int x, int y, int z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public BlockPoint set(int x, int y, int z)
    {
        this.x = x;
        this.y = y;
        this.z = z;

        return this;
    }

    public BlockPoint set(BlockPoint p)
    {
        this.x = p.x;
        this.y = p.y;
        this.z = p.z;

        return this;
    }

    public BlockPoint add(int x, int y, int z)
    {
        this.x += x;
        this.y += y;
        this.z += z;

        return this;
    }

    public BlockPoint add(BlockPoint p)
    {
        return add(p.x, p.y, p.z);
    }

    public BlockPoint subtract(int x, int y, int z)
    {
        this.x -= x;
        this.y -= y;
        this.z -= z;

        return this;
    }

    public BlockPoint subtract(BlockPoint p)
    {
        return subtract(p.x, p.y, p.z);
    }

    public BlockPoint multiply(int m)
    {
        x *= m;
        y *= m;
        z *= m;

        return this;
    }

    public BlockPoint multiply(int x, int y, int z)
    {
        this.x *= x;
        this.y *= y;
        this.z *= z;

        return this;
    }

    public BlockPoint multiply(BlockPoint p)
    {
        return multiply(p.x, p.y, p.z);
    }

    public BlockPoint divide(int x, int y, int z)
    {
        this.x /= x;
        this.y /= y;
        this.z /= z;

        return this;
    }

    public BlockPoint divide(BlockPoint p)
    {
        return divide(p.x, p.y, p.z);
    }
}
