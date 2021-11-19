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

public class WalkToBank extends Leaf<Main> {

    @Override
    public boolean isValid() {
        if(ScriptAreas.DESIRABLE_BANK.contains(Players.localPlayer())) API.randomTileInBank = null;
    	return API.randomTileInBank != null;
    }

    @Override
    public int onLoop() {
    	if(API.randomTileInBank.distance(Players.localPlayer()) >= 7) { // far
        		if(API.randDestLimit == 0) API.randDestLimit = 4 + API.rand2.nextInt(9);
            	if(Walking.shouldWalk(API.randDestLimit))
        		{
            		if(Walking.walk(API.randomTileInBank))
            			
            			API.randDestLimit = 0;
            		else
            		{
            			API.walkFails++;
            			MethodProvider.log("Failed walking step towards bank");
            		}
        		}
        	}
        	else if(API.randomTileInBank.distance(Players.localPlayer()) < 7) { // closer
        		if(API.randomTileInBank.distance(Players.localPlayer()) <= 2) 
        		{
        			API.randomTileInBank = null;
        			return (int) ((double) 50 + API.rand2.nextInt(100) * API.sleepMod);
        		}
        		if(!Walking.walk(API.randomTileInBank))
        		{
        			MethodProvider.log("Failed walking step towards bank");
        			API.walkFails++;
        		}
        			
        	}
    	if(API.walkFails  >= 10)
    	{
    		API.walkFails = 0;
    		API.randomTileInBank = null;
    	}
    	return (int) ((double) 444 + API.rand2.nextInt(888) * API.sleepMod);
    }
}
