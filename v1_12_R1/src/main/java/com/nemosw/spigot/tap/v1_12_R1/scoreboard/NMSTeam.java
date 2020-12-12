package com.nemosw.spigot.tap.v1_12_R1.scoreboard;

import com.nemosw.spigot.tap.scoreboard.CollisionRule;
import com.nemosw.spigot.tap.scoreboard.NameTagVisibility;
import com.nemosw.spigot.tap.scoreboard.TapTeam;
import org.bukkit.ChatColor;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public final class NMSTeam implements TapTeam
{

	final NMSScoreboard scoreboard;

	final String name;

	String displayName;

	String prefix = "";

	String suffix = "";

	boolean allowFriendlyFire;

	boolean canSeeFriendlyInvisibles;

	NameTagVisibility nameTagVisibility = NameTagVisibility.ALWAYS;

	CollisionRule collisionRule = CollisionRule.ALWAYS;

	ChatColor color = ChatColor.WHITE;

	final Set<String> entries = new HashSet<>();

	private boolean valid;

	NMSTeam(NMSScoreboard scoreboard, String name)
	{
		this.scoreboard = scoreboard;
		this.name = name;
		this.displayName = name;
		this.valid = true;
	}

	@Override
	public NMSScoreboard getScoreboard()
	{
		return this.scoreboard;
	}

	@Override
	public String getName()
	{
		return this.name;
	}

	@Override
	public String getDisplayName()
	{
		return this.displayName;
	}

	@Override
	public void setDisplayName(String displayName)
	{
		checkState();

		if (displayName == null)
			throw new NullPointerException("Display name cannot be null");
		if (displayName.length() > 32)
			throw new NullPointerException("Display name '" + displayName + "' is longer than the limit of 32 characters");

		this.displayName = displayName;

		update();
	}

	@Override
	public String getPrefix()
	{
		return this.prefix;
	}

	@Override
	public void setPrefix(String prefix)
	{
		checkState();

		if (prefix == null)
			throw new NullPointerException("Prefix cannot be null");
		if (prefix.length() > 32)
			throw new IllegalArgumentException("Prefix '" + prefix + "' is longer than the limit of 32 characters");

		this.prefix = prefix;

		update();
	}

	@Override
	public String getSuffix()
	{
		return this.suffix;
	}

	@Override
	public void setSuffix(String suffix)
	{
		checkState();

		if (suffix == null)
			throw new NullPointerException("Suffix cannot be null");
		if (suffix.length() > 32)
			throw new IllegalArgumentException("Suffix '" + suffix + "' is longer than the limit of 32 characters");

		this.suffix = suffix;

		update();
	}

	@Override
	public boolean allowFriendlyFire()
	{
		return this.allowFriendlyFire;
	}

	@Override
	public void setAllowFriendlyFire(boolean allowFriendlyFire)
	{
		checkState();

		if (this.allowFriendlyFire == allowFriendlyFire)
			return;

		this.allowFriendlyFire = allowFriendlyFire;

		update();
	}

	@Override
	public boolean canSeeFriendlyInvisibles()
	{
		return this.canSeeFriendlyInvisibles;
	}

	@Override
	public void setCanSeeFriendlyInvisibles(boolean canSeeFriendlyInvisibles)
	{
		checkState();

		if (this.canSeeFriendlyInvisibles == canSeeFriendlyInvisibles)
			return;

		this.canSeeFriendlyInvisibles = canSeeFriendlyInvisibles;

		update();
	}

	@Override
	public NameTagVisibility getNameTagVisibility()
	{
		return this.nameTagVisibility;
	}

	@Override
	public void setNameTagVisibility(NameTagVisibility visibility)
	{
		checkState();

		if (this.nameTagVisibility == visibility)
			return;

		this.nameTagVisibility = visibility;

		update();
	}

	@Override
	public CollisionRule getCollisionRule()
	{
		return this.collisionRule;
	}

	@Override
	public void setCollisionRule(CollisionRule collisionRule)
	{
		if (collisionRule == null)
			throw new NullPointerException("rule cannot be null");

		if (this.collisionRule == collisionRule)
			return;

		this.collisionRule = collisionRule;

		update();
	}

	@Override
	public void addEntry(String name)
	{
		checkState();

		if (name == null)
			throw new NullPointerException("Name cannot be null");

		if (this.entries.add(name))
			this.scoreboard.setEntryTeam(name, this);
	}

	@Override
	public boolean removeEntry(String name)
	{
		checkState();

		if (this.entries.remove(name))
		{
			this.scoreboard.removeEntryTeam(name, this);

			return true;
		}

		return false;
	}

	void removeEntryWithoutUpdate(String name)
	{
		this.entries.remove(name);
	}

	@Override
	public boolean hasEntry(String name)
	{
		return this.entries.contains(name);
	}

	private Set<String> unmodifiableEntries;

	@Override
	public Set<String> getEntries()
	{
		if (this.unmodifiableEntries == null)
			this.unmodifiableEntries = Collections.unmodifiableSet(this.entries);

		return this.unmodifiableEntries;
	}

	@Override
	public int getSize()
	{
		return this.entries.size();
	}

	@Override
	public ChatColor getColor()
	{
		return this.color;
	}

	@Override
	public void setColor(ChatColor chatColor)
	{
		checkState();

		if (chatColor == null)
			throw new NullPointerException("Chat color cannot be null");

		if (this.color == chatColor)
			return;

		this.color = chatColor;

		update();
	}

	@Override
	public void unregister()
	{
		checkState();

		this.scoreboard.unregisterTeam(this);
		remove();
	}

	void remove()
	{
		this.valid = false;

		this.entries.clear();
	}

	private void update()
	{
		this.scoreboard.sendAll(FakeTeam.getInstance(this).createUpdatePacket());
	}

	private void checkState()
	{
		if (!this.valid)
			throw new IllegalStateException("Invalid TapTeam '" + this.name + "' @" + Integer.toHexString(System.identityHashCode(this)));
	}
}
