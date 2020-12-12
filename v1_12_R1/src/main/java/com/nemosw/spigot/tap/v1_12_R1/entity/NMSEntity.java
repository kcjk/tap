package com.nemosw.spigot.tap.v1_12_R1.entity;

import com.nemosw.spigot.tap.entity.TapEntity;
import com.nemosw.spigot.tap.v1_12_R1.math.NMSBoundingBox;
import net.minecraft.server.v1_12_R1.Entity;

public class NMSEntity implements TapEntity
{

	protected final Entity entity;

	NMSEntity(Entity entity)
	{
		this.entity = entity;
	}

	public Entity getHandle()
	{
		return entity;
	}

	@Override
	public int getId()
	{
		return entity.getId();
	}

	@Override
	public org.bukkit.entity.Entity getBukkitEntity()
	{
		return this.entity.getBukkitEntity();
	}

	@Override
	public float getWidth()
	{
		return this.entity.width;
	}

	@Override
	public float getHeight()
	{
		return this.entity.length;
	}

	@Override
	public double getPosX()
	{
		return this.entity.locX;
	}

	@Override
	public double getPosY()
	{
		return this.entity.locY;
	}

	@Override
	public double getPosZ()
	{
		return this.entity.locZ;
	}

    @Override
    public double getPrevX()
    {
        return entity.lastX;
    }

    @Override
    public double getPrevY()
    {
        return entity.lastY;
    }

    @Override
    public double getPrevZ()
    {
        return entity.lastZ;
    }

    @Override
	public float getYaw()
	{
		return this.entity.yaw;
	}

	@Override
	public float getPitch()
	{
		return this.entity.pitch;
	}

	@Override
	public boolean isInvisible()
	{
		return entity.isInvisible();
	}

	@Override
	public boolean isGlowing()
	{
		return entity.glowing;
	}

	@Override
	public boolean isOnGround()
	{
		return this.entity.onGround;
	}

	@Override
	public boolean isDead()
	{
		return this.entity.dead;
	}

	@Override
	public NMSBoundingBox getBoundingBox()
	{
		return new NMSBoundingBox(this.entity.getBoundingBox());
	}

	@Override
	public void setPosition(double x, double y, double z)
	{
		this.entity.setPosition(x, y, z);
	}

	@Override
	public void setPositionAndRotation(double x, double y, double z, float yaw, float pitch)
	{
		entity.setPositionRotation(x, y, z, yaw, pitch);
	}
	
	@Override
	public void setCustomName(String name)
	{
		this.entity.setCustomName(name == null ? "" : name);
	}
	
	@Override
	public void setCustomNameVisible(boolean visible)
	{
		this.entity.setCustomNameVisible(visible);
	}

	@Override
	public void setInvisible(boolean invisible)
	{
		this.entity.setInvisible(invisible);
	}

	@Override
	public void setGlowing(boolean glowing)
	{
		this.entity.g(glowing);
	}

	@Override
	public void setGravity(boolean gravity)
	{
		this.entity.setNoGravity(!gravity);
	}

}
