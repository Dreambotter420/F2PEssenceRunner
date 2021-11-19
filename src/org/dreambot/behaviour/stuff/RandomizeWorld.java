package org.dreambot.behaviour.stuff;

import org.dreambot.api.methods.interactive.Players;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.API;
import org.dreambot.utilities.ScriptAreas;
import org.dreambotScript.Main;

public class RandomizeWorld extends Leaf<Main> {

    @Override
    public boolean isValid() {
        return API.randomWorld == 0;
    }

    @Override
    public int onLoop() {
    	API.randomizeWorld();
    	return (int) ((double) 5000 + API.rand2.nextInt(3000) * API.sleepMod);
    }
}
