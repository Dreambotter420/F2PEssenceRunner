package org.dreambot.behaviour.stuff;

import java.util.List;

import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.container.impl.equipment.Equipment;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.map.Map;
import org.dreambot.api.methods.map.Tile;
import org.dreambot.api.methods.tabs.Tab;
import org.dreambot.api.methods.tabs.Tabs;
import org.dreambot.api.methods.walking.impl.Walking;
import org.dreambot.api.wrappers.interactive.Player;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.API;
import org.dreambot.utilities.ScriptAreas;
import org.dreambotScript.Main;

public class FindTileInBank extends Leaf<Main> {

    @Override
    public boolean isValid() {
        return API.randomTileInBank == null;
    }

    @Override
    public int onLoop() {
    	Tile p = Map.getWalkable(ScriptAreas.OVERSHOT_BANK.getRandomTile());
		if(!ScriptAreas.OVERSHOT_BANK.contains(p))
		{
			for(int i = 0; i< 99; i++)
			{
				p = Map.getWalkable(ScriptAreas.OVERSHOT_BANK.getRandomTile());
				if(ScriptAreas.OVERSHOT_BANK.contains(p)) break;
			}
		}
		if(!ScriptAreas.OVERSHOT_BANK.contains(p))
		{
			MethodProvider.log("Tried 99 times to grab valid walkable tile in bank and failed... sleeping forever (de-bug pls)...");
			MethodProvider.sleep(1000000000);
		} else	API.randomTileInBank = p;	
    	return (int) ((double) 50 + API.rand2.nextInt(25) * API.sleepMod);
    }
}
