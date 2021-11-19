package org.dreambot.behaviour.initialization;

import org.dreambot.api.ClientSettings;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.*;
import org.dreambotScript.Main;


public class UntoggleAIDS extends Leaf<Main>{

    @Override
    public boolean isValid() {
    	return ClientSettings.isAcceptAidEnabled();
    }

    @Override
    public int onLoop() {
    	ClientSettings.toggleAcceptAid(false);
    	API.randomizeWorld();
		return((int) ((double) 100 + API.rand2.nextInt(300) * API.sleepMod));
    }

}