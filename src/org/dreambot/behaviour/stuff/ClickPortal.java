package org.dreambot.behaviour.stuff;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.dreambot.api.Client;
import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.methods.container.impl.bank.Bank;
import org.dreambot.api.methods.dialogues.Dialogues;
import org.dreambot.api.methods.interactive.GameObjects;
import org.dreambot.api.methods.interactive.NPCs;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.world.Worlds;
import org.dreambot.api.wrappers.interactive.GameObject;
import org.dreambot.api.wrappers.interactive.NPC;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.API;
import org.dreambot.utilities.ScriptAreas;
import org.dreambotScript.Main;

public class ClickPortal extends Leaf<Main> {

    @Override
    public boolean isValid() {
    	return ScriptAreas.INSIDE_ALTAR.contains(Players.localPlayer()) && 
    			Worlds.getCurrentWorld() != API.customWorld;
    }

    @Override
    public int onLoop() {
    	API.randomAFK();
    	API.randomWorld = 0;
    	GameObject ruins = GameObjects.closest("Portal");
    	if(ruins != null && ruins.exists()) 
		{
			if(ruins.interact("Use")){
	    		MethodProvider.sleepUntil(() -> ScriptAreas.DESIRABLE_ALTAR.contains(Players.localPlayer()), (int) ((double) 8000 + API.rand2.nextInt(3000) * API.sleepMod));
			} else {
	    		MethodProvider.logInfo("No portal found in range of Altar area!!!!");
	        	MethodProvider.sleepUntil(() -> Client.getIdleTime() >= (int) ((double) 800 + API.rand2.nextInt(50) * API.sleepMod), 20000);
	    	}
		}
    	
    	return (int) ((double) 50 + API.rand2.nextInt(555) * API.sleepMod);
    }
}
