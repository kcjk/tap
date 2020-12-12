package com.nemosw.spigot.tap.v1_12_R1.scoreboard;

import net.minecraft.server.v1_12_R1.PacketPlayOutScoreboardScore;
import net.minecraft.server.v1_12_R1.ScoreboardScore;

public final class FakeScore extends ScoreboardScore
{
	private static final FakeScore INSTANCE = new FakeScore();
	
	public static FakeScore getInstance(NMSScore score)
	{
		FakeScore fakeScore = INSTANCE;
		
		fakeScore.score = score;
		
		return fakeScore;
	}
	
	private NMSScore score;
	
	private FakeScore()
	{
		super(null, FakeObjective.getInstance(), null);
	}
	
	@Override
	public FakeObjective getObjective()
	{
		return FakeObjective.getInstance();
	}
	
	@Override
	public String getPlayerName()
	{
		return this.score.name;
	}
	
	@Override
	public int getScore()
	{
		return this.score.score;
	}

	PacketPlayOutScoreboardScore createUpdatePacket()
	{ 
		NMSScore score = this.score;
		FakeObjective objective = FakeObjective.getInstance(score.objective);
		PacketPlayOutScoreboardScore packet = new PacketPlayOutScoreboardScore(this);
		
		this.score = null;
		objective.objective = null;
		
		return packet;
	}

	PacketPlayOutScoreboardScore createRemovePacket()
	{
		NMSScore score = this.score;
		FakeObjective objective = FakeObjective.getInstance(score.objective);
		PacketPlayOutScoreboardScore packet = new PacketPlayOutScoreboardScore(this.score.name, objective);
		
		this.score = null;
		objective.objective = null;
		
		return packet;
	}
}
