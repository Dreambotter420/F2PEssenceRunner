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

public class WalkToArea extends Leaf<Main> {

    @Override
    public boolean isValid() {
        if(ScriptAreas.DESIRABLE_ALTAR.contains(Players.localPlayer())) API.randomTileInArea = null;
    	return API.randomTileInArea != null;
    }

    @Override
    public int onLoop() {
    	if(API.randomTileInArea.distance(Players.localPlayer()) >= 7) { // far
        		if(API.randDestLimit == 0) API.randDestLimit = 4 + API.rand2.nextInt(9);
            	if(Walking.shouldWalk(API.randDestLimit))
        		{
            		if(Walking.walk(API.randomTileInArea))
            			
            			API.randDestLimit = 0;
            		else
            		{
            			API.walkFails++;
            			MethodProvider.log("Failed walking step towards bank");
            		}
        		}
        	}
        	else if(API.randomTileInArea.distance(Players.localPlayer()) < 7) { // closer
        		if(API.randomTileInArea.distance(Players.localPlayer()) <= 2) 
        		{
        			API.randomTileInArea = null;
        			return (int) ((double) 50 + API.rand2.nextInt(100) * API.sleepMod);
        		}
        		if(!Walking.walk(API.randomTileInArea))
        		{
        			MethodProvider.log("Failed walking step towards bank");
        			API.walkFails++;
        		}
        			
        	}
    	if(API.walkFails  >= 10)
    	{
    		API.walkFails = 0;
    		API.randomTileInArea = null;
    	}
    	return (int) ((double) 444 + API.rand2.nextInt(888) * API.sleepMod);
    }
}
