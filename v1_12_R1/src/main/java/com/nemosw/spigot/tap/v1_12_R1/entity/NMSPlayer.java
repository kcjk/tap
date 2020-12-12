package com.nemosw.spigot.tap.v1_12_R1.entity;

import com.nemosw.spigot.tap.entity.TapPlayer;
import com.nemosw.spigot.tap.inventory.TapPlayerInventory;
import com.nemosw.spigot.tap.item.TapItemStack;
import com.nemosw.spigot.tap.v1_12_R1.inventory.NMSPlayerInventory;
import net.minecraft.server.v1_12_R1.Entity;
import net.minecraft.server.v1_12_R1.EntityPlayer;
import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftInventoryPlayer;
import org.bukkit.entity.Player;

public class NMSPlayer extends NMSLivingEntity implements TapPlayer
{

	private final EntityPlayer player;

	NMSPlayer(Entity entity)
	{
		super(entity);

		this.player = (EntityPlayer) entity;
	}

	public EntityPlayer getHandle()
	{
		return this.player;
	}

	@Override
	public Player getBukkitEntity()
	{
		return this.player.getBukkitEntity();
	}

	@Override
	public int getLevel()
	{
		return this.player.getExpToLevel();
	}

	@Override
	public int getFoodLevel()
	{
		return this.player.getFoodData().foodLevel;
	}

	private TapPlayerInventory inv;

	@Override
	public TapPlayerInventory getInventory()
	{
		TapPlayerInventory inv = this.inv;

		return inv == null ? this.inv = new NMSPlayerInventory((CraftInventoryPlayer) this.player.getBukkitEntity().getInventory(), this) : inv;
	}

	@Override
	public TapItemStack getHeldItemMainHand()
	{
		return getInventory().getHeldItemMainHand();
	}

	@Override
	public TapItemStack getHeldItemOffHand()
	{
		return getInventory().getHeldItemOffHand();
	}

}
