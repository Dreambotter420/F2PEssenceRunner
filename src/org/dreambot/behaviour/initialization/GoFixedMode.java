package org.dreambot.behaviour.initialization;

import org.dreambot.api.ClientSettings;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.*;
import org.dreambotScript.Main;


public class GoFixedMode extends Leaf<Main>{

    @Override
    public boolean isValid() {
    	return ClientSettings.isResizableActive();
    }

    @Override
    public int onLoop() {
    	ClientSettings.toggleResizable(false);
		return((int) ((double) 666 + API.rand2.nextInt(666) * API.sleepMod));
    }

}