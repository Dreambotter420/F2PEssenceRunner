package org.dreambot.behaviour.initialization;

import org.dreambot.api.ClientSettings;
import org.dreambot.api.data.ActionMode;
import org.dreambot.api.methods.MethodProvider;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.*;
import org.dreambotScript.Main;


public class DisableLeftClickAttackStuff extends Leaf<Main>{

    @Override
    public boolean isValid() {
    	return (ClientSettings.getNPCAttackOptionsMode() != ActionMode.ALWAYS_RIGHT_CLICK) ||
    			(ClientSettings.getPlayerAttackOptionsMode() != ActionMode.ALWAYS_RIGHT_CLICK);
    }

    @Override
    public int onLoop() {
    	if(ClientSettings.getNPCAttackOptionsMode() != ActionMode.ALWAYS_RIGHT_CLICK)
    	{
    		ClientSettings.setNPCAttackOptionsMode(ActionMode.ALWAYS_RIGHT_CLICK);
    		MethodProvider.sleep((int) ((double) 100 + API.rand2.nextInt(300) * API.sleepMod));
    	}
    	if(ClientSettings.getPlayerAttackOptionsMode() != ActionMode.ALWAYS_RIGHT_CLICK)
    		ClientSettings.setPlayerAttackOptionsMode(ActionMode.ALWAYS_RIGHT_CLICK);
		return (int) ((double) 100 + API.rand2.nextInt(300) * API.sleepMod);
    }

}