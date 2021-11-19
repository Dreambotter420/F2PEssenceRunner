package org.dreambot.behaviour.stuff;

import org.dreambot.api.methods.walking.impl.Walking;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.*;
import org.dreambotScript.Main;


public class EnableRun extends Leaf<Main>{

    @Override
    public boolean isValid() {
    	return API.randomRun == 0 || (!Walking.isRunEnabled() && (Walking.getRunEnergy() > API.randomRun));
    }

    @Override
    public int onLoop() {
    	if(API.randomRun == 0)
		{
			API.randomRun = 25 + API.rand2.nextInt(50);
		} else {
			Walking.toggleRun();
			API.randomRun = 0;
		}
		return((int) ((double) 50 + API.rand2.nextInt(300) * API.sleepMod));
    }

}