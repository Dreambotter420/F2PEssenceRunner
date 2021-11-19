package org.dreambot.behaviour;

import org.dreambot.api.Client;
import org.dreambot.api.data.GameState;
import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.framework.Root;
import org.dreambot.utilities.API;
import org.dreambotScript.Main;

public class ToMule extends Root<Main> {
    @Override
    public boolean isValid() {
    	return Client.getGameState() == GameState.LOGGED_IN && 
        		API.mule;
    }

}
