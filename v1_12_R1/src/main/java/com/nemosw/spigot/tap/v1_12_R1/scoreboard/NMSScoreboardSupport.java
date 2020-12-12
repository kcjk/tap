package com.nemosw.spigot.tap.v1_12_R1.scoreboard;

import com.nemosw.spigot.tap.scoreboard.ScoreboardSupport;

public final class NMSScoreboardSupport implements ScoreboardSupport
{
	@Override
	public NMSScoreboard newScoreboard()
	{
		return new NMSScoreboard();
	}

}
