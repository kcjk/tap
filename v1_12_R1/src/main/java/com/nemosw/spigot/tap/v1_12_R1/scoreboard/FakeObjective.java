package com.nemosw.spigot.tap.v1_12_R1.scoreboard;

import net.minecraft.server.v1_12_R1.IScoreboardCriteria;
import net.minecraft.server.v1_12_R1.PacketPlayOutScoreboardDisplayObjective;
import net.minecraft.server.v1_12_R1.PacketPlayOutScoreboardObjective;
import net.minecraft.server.v1_12_R1.ScoreboardObjective;

public final class FakeObjective extends ScoreboardObjective
{
	private static final FakeObjective INSTANCE = new FakeObjective();
	
	static FakeObjective getInstance()
	{
		return INSTANCE;
	}
	
	public static FakeObjective getInstance(NMSObjective objective)
	{
		FakeObjective fakeObjective = INSTANCE;
		
		fakeObjective.objective = objective;
		
		return fakeObjective;
	}
	
	NMSObjective objective;
	
	private FakeObjective()
	{
		super(null, null, IScoreboardCriteria.b);
	}
	
	@Override
	public String getName()
	{
		return this.objective.name;
	}
	
	@Override
	public String getDisplayName()
	{
		return this.objective.displayName;
	}

	PacketPlayOutScoreboardObjective createCreatePacket()
	{
		PacketPlayOutScoreboardObjective packet = new PacketPlayOutScoreboardObjective(this, 0);
		
		this.objective = null;
		
		return packet;
	}

	PacketPlayOutScoreboardObjective createUpdatePacket()
	{
		PacketPlayOutScoreboardObjective packet = new PacketPlayOutScoreboardObjective(this, 2);
		
		this.objective = null;
		
		return packet;
	}

	PacketPlayOutScoreboardDisplayObjective createDisplayPacket()
	{
		PacketPlayOutScoreboardDisplayObjective packet = new PacketPlayOutScoreboardDisplayObjective(this.objective.getDisplaySlot().ordinal(), this);
		
		this.objective = null;
		
		return packet;
	}

	PacketPlayOutScoreboardObjective createRemovePacket()
	{
		PacketPlayOutScoreboardObjective packet = new PacketPlayOutScoreboardObjective(this, 1);
		
		this.objective = null;
		
		return packet;
	}
}
