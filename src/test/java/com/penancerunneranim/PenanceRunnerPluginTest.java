package com.penancerunneranim;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class PenanceRunnerPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(PenanceRunnerPlugin.class);
		RuneLite.main(args);
	}
}