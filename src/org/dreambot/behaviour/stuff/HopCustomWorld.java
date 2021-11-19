package org.dreambot.behaviour.stuff;

import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.widget.Widgets;
import org.dreambot.api.methods.world.Worlds;
import org.dreambot.api.methods.worldhopper.WorldHopper;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.API;
import org.dreambot.utilities.ScriptAreas;
import org.dreambotScript.Main;

public class HopCustomWorld extends Leaf<Main> {

    @Override
    public boolean isValid() {
        return Worlds.getCurrentWorld() != API.customWorld &&
        		(Players.localPlayer().getTile().equals(ScriptAreas.tradeTile) ||
        				Players.localPlayer().getTile().equals(ScriptAreas.muleTile));
    }

    @Override
    public int onLoop() {
    	API.randomAFK();
    	if(Widgets.isOpen())
    	{
    		Widgets.closeAll();
    	}
    	if(API.worldHops < 25)
		{
			MethodProvider.log("Hopping to Custom world " + API.customWorld + " attempt #" + (API.worldHops+1));
			WorldHopper.hopWorld(API.customWorld);
			API.worldHops++;
		} else 
		{
			MethodProvider.log("Over 25 hops and no success :-( going again...");
			API.worldHops = 0;
		}
    	return (int) ((double) 2222 + API.rand2.nextInt(2222) * API.sleepMod);
    }
}
