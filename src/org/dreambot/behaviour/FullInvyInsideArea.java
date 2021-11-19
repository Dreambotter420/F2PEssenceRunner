package org.dreambot.behaviour;

import org.dreambot.api.Client;
import org.dreambot.api.data.GameState;
import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.container.impl.bank.Bank;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.trade.Trade;
import org.dreambot.framework.Root;
import org.dreambot.utilities.API;
import org.dreambot.utilities.ScriptAreas;
import org.dreambotScript.Main;

public class FullInvyInsideArea extends Root<Main> {
    @Override
    public boolean isValid() {
    	return Client.getGameState() == GameState.LOGGED_IN && 
        		((Inventory.onlyContains(API.PURE_ESS) &&
        	        		Inventory.count(API.PURE_ESS) >= 1)
        				|| Trade.isOpen()) && 
        		ScriptAreas.INSIDE_ALTAR.contains(Players.localPlayer());
    }

}
