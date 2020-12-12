package com.nemosw.spigot.tap.v1_12_R1.entity;

import com.github.noonmaru.math.Vector;
import com.nemosw.spigot.tap.entity.TapArmorStand;
import net.minecraft.server.v1_12_R1.Entity;
import net.minecraft.server.v1_12_R1.EntityArmorStand;
import net.minecraft.server.v1_12_R1.Vec3D;
import net.minecraft.server.v1_12_R1.Vector3f;
import org.bukkit.entity.ArmorStand;

public class NMSArmorStand extends NMSLivingEntity implements TapArmorStand
{

	private static Vector toVector(Vector3f v)
	{
		return new Vector(v.getX(), v.getY(), v.getZ());
	}

	private final EntityArmorStand armorStand;

	NMSArmorStand(Entity entity)
	{
		super(entity);

		this.armorStand = (EntityArmorStand) entity;
	}

	@Override
	public EntityArmorStand getHandle()
	{
		return this.armorStand;
	}
	
	@Override
	public ArmorStand getBukkitEntity()
	{
		return (ArmorStand) this.armorStand.getBukkitEntity();
	}

	@Override
	public Vector getHeadPos()
	{
		return toVector(armorStand.headPose);
	}

	@Override
	public void setHeadPose(float x, float y, float z)
	{
		this.armorStand.setHeadPose(new Vector3f(x, y, z));
	}

	@Override
	public void setBodyPose(float x, float y, float z)
	{
		this.armorStand.setBodyPose(new Vector3f(x, y, z));
	}

	@Override
	public void setLeftArmPose(float x, float y, float z)
	{
		this.armorStand.setLeftArmPose(new Vector3f(x, y, z));
	}

	@Override
	public void setRightArmPose(float x, float y, float z)
	{
		this.armorStand.setRightArmPose(new Vector3f(x, y, z));
	}

	@Override
	public void setLeftLegPose(float x, float y, float z)
	{
		this.armorStand.setLeftLegPose(new Vector3f(x, y, z));
	}

	@Override
	public void setRightLegPose(float x, float y, float z)
	{
		this.armorStand.setRightLegPose(new Vector3f(x, y, z));
	}

}
