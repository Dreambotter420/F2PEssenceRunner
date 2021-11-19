package org.dreambot.behaviour.stuff;

import java.util.List;

import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.container.impl.equipment.Equipment;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.map.Tile;
import org.dreambot.api.methods.tabs.Tab;
import org.dreambot.api.methods.tabs.Tabs;
import org.dreambot.api.methods.trade.Trade;
import org.dreambot.api.methods.walking.impl.Walking;
import org.dreambot.api.wrappers.interactive.Player;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.API;
import org.dreambotScript.Main;

public class SendTrade extends Leaf<Main> {

    @Override
    public boolean isValid() {
    	return !Trade.isOpen();
    }

    @Override
    public int onLoop() {
    	String name;
    	if(API.mule) {
    		name = API.mueller;
    	} else {
    		name = API.traderName;
    	}
    	if(Players.closest(name) != null) {
    		if(Players.closest(name).interact("Trade with")) {
    			MethodProvider.sleepUntil(() -> Trade.isOpen() || API.resendTrade, (int) ((double) 50000 + API.rand2.nextInt(10000) * API.sleepMod));
    			if(API.resendTrade) {
    				MethodProvider.sleep((int) ((double) 4000 + API.rand2.nextInt(2222) * API.sleepMod));
    				API.resendTrade = false;
    			}
    		}
    	}
    	return (int) ((double) 50 + API.rand2.nextInt(111) * API.sleepMod);
    }
}
