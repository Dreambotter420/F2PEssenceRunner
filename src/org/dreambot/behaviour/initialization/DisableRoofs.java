package org.dreambot.behaviour.initialization;

import org.dreambot.api.ClientSettings;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.*;
import org.dreambotScript.Main;


public class DisableRoofs extends Leaf<Main>{

    @Override
    public boolean isValid() {
    	return ClientSettings.roofsEnabled();
    }

    @Override
    public int onLoop() {
    	ClientSettings.toggleRoofs(false);
		return((int) ((double) 100 + API.rand2.nextInt(300) * API.sleepMod));
    }

}