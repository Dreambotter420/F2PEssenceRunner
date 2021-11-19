package org.dreambot.behaviour.stuff;

import org.dreambot.api.Client;
import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.methods.interactive.GameObjects;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.wrappers.interactive.GameObject;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.API;
import org.dreambot.utilities.ScriptAreas;
import org.dreambotScript.Main;

public class ClickAltar extends Leaf<Main> {

    @Override
    public boolean isValid() {
    	return ScriptAreas.DESIRABLE_ALTAR.contains(Players.localPlayer());
    }

    @Override
    public int onLoop() {
    	API.randomAFK();
    	GameObject ruins = GameObjects.closest("Mysterious ruins");
    	if(ruins != null && ruins.exists()) 
		{
			if(ruins.interact("Enter")){
	    		MethodProvider.sleepUntil(() -> ScriptAreas.INSIDE_ALTAR.contains(Players.localPlayer()), (int) ((double) 8000 + API.rand2.nextInt(3000) * API.sleepMod));
			} else {
	    		MethodProvider.logInfo("No altar found in range of Altar area!!!!");
	    		MethodProvider.sleepUntil(() -> Client.getIdleTime() >= (int) ((double) 800 + API.rand2.nextInt(50) * API.sleepMod), 20000);
	    	}
		}
    	
    	return (int) ((double) 50 + API.rand2.nextInt(555) * API.sleepMod);
    }
}
