package org.dreambot.behaviour.stuff;

import java.util.List;

import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.container.impl.equipment.Equipment;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.map.Tile;
import org.dreambot.api.methods.tabs.Tab;
import org.dreambot.api.methods.tabs.Tabs;
import org.dreambot.api.methods.walking.impl.Walking;
import org.dreambot.api.wrappers.interactive.Player;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.API;
import org.dreambot.utilities.ScriptAreas;
import org.dreambotScript.Main;

public class WalkToTradeTile extends Leaf<Main> {

    @Override
    public boolean isValid() {
    	return !Players.localPlayer().getTile().equals(ScriptAreas.tradeTile);
    }

    @Override
    public int onLoop() {
    	if(Walking.walk(ScriptAreas.tradeTile))
		{
			MethodProvider.sleepUntil(() -> Players.localPlayer().getTile().equals(ScriptAreas.tradeTile), 
					(int) ((double) 1588 + API.rand2.nextInt(999) * API.sleepMod));
		}
    	return (int) ((double) 50 + API.rand2.nextInt(111) * API.sleepMod);
    }
}
