package com.nemosw.spigot.tap.v1_12_R1.inventory;

import com.google.common.collect.MapMaker;
import com.nemosw.spigot.tap.inventory.InventorySupport;
import com.nemosw.spigot.tap.inventory.TapInventory;
import com.nemosw.spigot.tap.v1_12_R1.entity.NMSEntitySupport;
import com.nemosw.spigot.tap.v1_12_R1.entity.NMSPlayer;
import net.minecraft.server.v1_12_R1.IInventory;
import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftInventory;
import org.bukkit.inventory.PlayerInventory;

import java.util.Map;

public final class NMSInventorySupport implements InventorySupport
{

	private final Map<IInventory, TapInventory> cache;

	public NMSInventorySupport()
	{
		cache = new MapMaker().weakValues().makeMap();
	}

	@Override
	public TapInventory fromInventory(org.bukkit.inventory.Inventory inventory)
	{
		Map<IInventory, TapInventory> cache = this.cache;
		CraftInventory craftInventory = (CraftInventory) inventory;
		IInventory iinventory = craftInventory.getInventory();
		TapInventory tapInventory = cache.get(iinventory);

		if (tapInventory == null)
		{
			if (inventory instanceof org.bukkit.inventory.PlayerInventory)
            {
                NMSPlayer nmsPlayer = NMSEntitySupport.getInstance().wrapEntity(((PlayerInventory) inventory).getHolder());

                return nmsPlayer.getInventory();
            }

			tapInventory = new NMSInventory(iinventory, craftInventory);
			cache.put(iinventory, tapInventory);
		}

		return tapInventory;
	}

}
