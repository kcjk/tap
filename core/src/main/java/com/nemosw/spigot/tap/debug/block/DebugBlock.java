package com.nemosw.spigot.tap.debug.block;

import com.nemosw.spigot.tap.Tap;
import com.nemosw.spigot.tap.block.TapBlock;
import com.nemosw.spigot.tap.block.TapBlockData;
import com.nemosw.spigot.tap.debug.DebugProcess;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import java.util.Collections;

@SuppressWarnings("ALL")
public class DebugBlock extends DebugProcess
{
    @Override
    public void onStart()
    {
        registerListener(new Listener()
        {
            @EventHandler
            public void onPlayerInteract(PlayerInteractEvent event)
            {
               if (event.getHand() != EquipmentSlot.HAND)
                   return;

                Action action = event.getAction();

                if (action == Action.LEFT_CLICK_AIR || action == Action.LEFT_CLICK_BLOCK)
                {
                    Player player = event.getPlayer();
                    ItemStack itemStack = player.getInventory().getItemInMainHand();

                    if (itemStack != null && itemStack.getType().isBlock())
                    {
                        TapBlock block = Tap.BLOCK.getBlock(itemStack.getTypeId());
                        TapBlockData blockData = block.getBlockData(itemStack.getDurability());

                        for (int i = 0; i < 7; i++)
                            player.sendMessage("");

                        player.sendMessage("§6Item");
                        player.sendMessage(block.getTextureId());
                        player.sendMessage(block.getId() + ":" + blockData.toLegacyData());
                    }
                }
                else if (action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK)
                {
                    Player player = event.getPlayer();
                    Block block = player.getTargetBlock(Collections.singleton(Material.AIR), 64);

                    if (block != null)
                    {
                        TapBlock tapBlock = Tap.BLOCK.getBlock(block.getTypeId());
                        TapBlockData blockData = tapBlock.getBlockData(block.getData() & 0xFF);

                        for (int i = 0; i < 7; i++)
                            player.sendMessage("");

                        player.sendMessage("§bBlock");
                        player.sendMessage(tapBlock.getTextureId());
                        player.sendMessage(tapBlock.getId() + ":" + blockData.toLegacyData() + " " + (block.getData() & 0xFF));
                    }
                }
            }
        });
    }
}
