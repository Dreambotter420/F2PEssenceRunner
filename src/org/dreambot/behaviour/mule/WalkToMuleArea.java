package org.dreambot.behaviour.mule;

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

public class WalkToMuleArea extends Leaf<Main> {

    @Override
    public boolean isValid() {
    	return !Players.localPlayer().getTile().equals(ScriptAreas.muleTile);
    }

    @Override
    public int onLoop() {
    	if(ScriptAreas.muleTile.distance(Players.localPlayer()) >= 7) { // far
        		if(API.randDestLimit == 0) API.randDestLimit = 4 + API.rand2.nextInt(9);
            	if(Walking.shouldWalk(API.randDestLimit)){
            		if(Walking.walk(ScriptAreas.muleTile)) API.randDestLimit = 0;
            		else MethodProvider.log("Failed walking step towards mule tile");
        		}
        	}
        	else if(ScriptAreas.muleTile.distance(Players.localPlayer()) < 7 && 
        			!Walking.walk(ScriptAreas.muleTile)) { // closer
        		MethodProvider.log("Failed walking step towards mule tile");
        	}
    	return (int) ((double) 444 + API.rand2.nextInt(888) * API.sleepMod);
    }
}
