package org.dreambot.behaviour.stuff;

import org.dreambot.api.Client;
import org.dreambot.api.data.GameState;
import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.methods.container.impl.bank.Bank;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.widget.Widgets;
import org.dreambot.api.methods.world.Worlds;
import org.dreambot.api.methods.worldhopper.WorldHopper;
import org.dreambot.api.wrappers.widgets.WidgetChild;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.API;
import org.dreambotScript.Main;

public class HopRandomWorld extends Leaf<Main> {

    @Override
    public boolean isValid() {
        return Worlds.getCurrentWorld() == API.customWorld;
    }

    @Override
    public int onLoop() {
    	API.randomAFK();
    	if(Widgets.isOpen())
    	{
    		Widgets.closeAll();
    	}
    	API.randomizeWorld();
    	for(int i = 0; i < 25; i++) {
    		if(Client.getGameState() != GameState.LOGGED_IN) {
    			MethodProvider.sleep((int) ((double) 2222 + API.rand2.nextInt(2222) * API.sleepMod));
    			i--;
    		}
    		else if(Worlds.getCurrentWorld() == API.randomWorld) break;
    		else {
    			MethodProvider.log("Hopping to Random world " + API.randomWorld + " attempt #" + (API.worldHops+1));
    			WorldHopper.hopWorld(API.randomWorld);
    		}
    	}
    	if(API.worldHops >= 25){
    		MethodProvider.log("Over 25 hops and no success :-( going again...");
			API.worldHops = 0;
			API.randomizeWorld();
		}
    	return (int) ((double) 50 + API.rand2.nextInt(555) * API.sleepMod);
    }
}
