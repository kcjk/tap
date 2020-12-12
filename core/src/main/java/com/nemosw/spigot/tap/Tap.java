package com.nemosw.spigot.tap;

import com.nemosw.spigot.tap.block.TapBlockSupport;
import com.nemosw.spigot.tap.command.EntitySelector;
import com.nemosw.spigot.tap.entity.TapEntitySupport;
import com.nemosw.spigot.tap.inventory.InventorySupport;
import com.nemosw.spigot.tap.item.TapItemSupport;
import com.nemosw.spigot.tap.math.MathSupport;
import com.nemosw.spigot.tap.nbt.NBTSupport;
import com.nemosw.spigot.tap.profile.ProfileSupport;
import com.nemosw.spigot.tap.scoreboard.ScoreboardSupport;
import com.nemosw.spigot.tap.sound.SoundSupport;
import com.nemosw.spigot.tap.text.TextSupport;
import com.nemosw.spigot.tap.world.WorldSupport;

public final class Tap
{
    public static final TapBlockSupport BLOCK = LibraryLoader.load(TapBlockSupport.class);

    public static final EntitySelector ENTITY_SELECTOR = LibraryLoader.load(EntitySelector.class);

    public static final TapEntitySupport ENTITY = LibraryLoader.load(TapEntitySupport.class);

    public static final InventorySupport INVENTORY = LibraryLoader.load(InventorySupport.class);

    public static final TapItemSupport ITEM = LibraryLoader.load(TapItemSupport.class);

    public static final MathSupport MATH = LibraryLoader.load(MathSupport.class);

    public static final SoundSupport SOUND = LibraryLoader.load(SoundSupport.class);

    public static final ProfileSupport PROFILE = LibraryLoader.load(ProfileSupport.class);

    public static final ScoreboardSupport SCOREBOARD = LibraryLoader.load(ScoreboardSupport.class);

    public static final TextSupport TEXT = LibraryLoader.load(TextSupport.class);

    public static final WorldSupport WORLD = LibraryLoader.load(WorldSupport.class);

    public static final NBTSupport NBT = LibraryLoader.load(NBTSupport.class);

    private Tap() {}
}
