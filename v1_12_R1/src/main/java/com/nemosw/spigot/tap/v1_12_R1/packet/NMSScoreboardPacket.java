package com.nemosw.spigot.tap.v1_12_R1.packet;

import com.nemosw.spigot.tap.packet.ScoreboardPacket;
import net.minecraft.server.v1_12_R1.IScoreboardCriteria;
import net.minecraft.server.v1_12_R1.PacketPlayOutScoreboardObjective;
import net.minecraft.server.v1_12_R1.ScoreboardObjective;

public class NMSScoreboardPacket implements ScoreboardPacket
{
	private static class FakeObjecitve extends ScoreboardObjective
	{
		String name;

		String displayName;

		FakeObjecitve()
		{
			super(null, "", IScoreboardCriteria.b);
		}

		@Override
		public String getDisplayName()
		{
			return this.displayName;
		}

		@Override
		public String getName()
		{
			return this.name;
		}
	}

	private static final FakeObjecitve SCOREBOARD_OBJECTIVE = new FakeObjecitve();

	@Override
	public NMSPacket scoreboardDisplayName(String name, String displayName)
	{
		FakeObjecitve objective = SCOREBOARD_OBJECTIVE;

		objective.name = name;
		objective.displayName = displayName;

		PacketPlayOutScoreboardObjective packet = new PacketPlayOutScoreboardObjective(objective, 2);

		objective.name = null;
		objective.displayName = null;

		return new NMSPacketFixed(packet);
	}

}
