package com.example;

import com.google.common.collect.ImmutableSet;
import java.util.Locale;
import java.util.Set;
import java.util.regex.Pattern;
import net.runelite.api.NPC;
import net.runelite.api.events.OverheadTextChanged;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;

@PluginDescriptor(
	name = "Penance Runner eating anim",
	description = "Restores the missing Penance Runner eating anim",
	tags = {"BA, Barb assault, Barbarian assault, Penance Runner"}
)
public class ExamplePlugin extends Plugin
{
	private static final int TRIGGER_ANIMATION_ID = 5103;
	private static final Set<String> TRIGGER_TEXTS = ImmutableSet.of("chomp, chomp.", "blughhhh.");

	private static final Set<Integer> PENANCE_RUNNER_IDS = ImmutableSet.of(
		1669, 5748, 5749, 5750, 5751, 5752, 5753, 5754, 5755, 5756
	);

	private static final Pattern TAGS = Pattern.compile("<[^>]*>");

	@Subscribe
	public void onOverheadTextChanged(OverheadTextChanged event)
	{
		if (!(event.getActor() instanceof NPC))
		{
			return;
		}
		final NPC npc = (NPC) event.getActor();

		final String rawText = event.getOverheadText();
		if (rawText == null || rawText.isBlank())
		{
			return;
		}

		final int npcId = npc.getId();
		final String text = normalize(rawText);

		if (PENANCE_RUNNER_IDS.contains(npcId))
		{
			if (TRIGGER_TEXTS.contains(text))
			{
				playAnimation(npc);
			}
			return;
		}

	}

	private static String normalize(String text)
	{
		return TAGS.matcher(text)
			.replaceAll("")
			.trim()
			.toLowerCase(Locale.ENGLISH);
	}

	private static void playAnimation(NPC npc)
	{
		npc.setAnimation(-1);
		npc.setAnimation(TRIGGER_ANIMATION_ID);
	}
}
