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
import org.dreambot.api.methods.trade.TradeUser;
import org.dreambot.api.methods.walking.impl.Walking;
import org.dreambot.api.wrappers.interactive.Player;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.API;
import org.dreambotScript.Main;

public class HandleTrading extends Leaf<Main> {

    @Override
    public boolean isValid() {
    	return Trade.isOpen();
    }

    @Override
    public int onLoop() {
    	/**
    	 * If first trade screen open and accepted, wait
    	 * If not accepted and have items to put up, put up items then accept,wait
    	 * If not accepted and not have items to put up, accept,wait
    	 * if second screen open, accept, wait
    	 */
    	if(Trade.isOpen(1)) {
    		if(Trade.hasAcceptedTrade(TradeUser.US)) {
    			MethodProvider.sleepUntil(() -> (Trade.isOpen(2) || !Trade.isOpen()), (int) ((double) 15000 + API.rand2.nextInt(10000) * API.sleepMod));
    		} else {
    			if(Inventory.contains(API.PURE_ESS) && Inventory.interact(API.PURE_ESS, "Offer-All")) {
    				MethodProvider.sleep((int) ((double) 50 + API.rand2.nextInt(111) * API.sleepMod));
    				if(Trade.acceptTrade()) {
    					MethodProvider.sleepUntil(() -> (Trade.isOpen(2) || !Trade.isOpen()), (int) ((double) 15000 + API.rand2.nextInt(10000) * API.sleepMod));
    				}
    			}
    			if(!Inventory.contains(API.PURE_ESS) && Trade.contains(true, 1, API.PURE_ESS)) {
    				if(Trade.acceptTrade()) {
    					MethodProvider.sleepUntil(() -> (Trade.isOpen(2) || !Trade.isOpen()), (int) ((double) 15000 + API.rand2.nextInt(10000) * API.sleepMod));
    				}
    			}
    		}
    	} else {
    		if(!Trade.hasAcceptedTrade(TradeUser.US)) {
    			if(Trade.acceptTrade()) {
					MethodProvider.sleepUntil(() -> !Trade.isOpen(), (int) ((double) 15000 + API.rand2.nextInt(10000) * API.sleepMod));
				}
    		}
    	}
    	return (int) ((double) 50 + API.rand2.nextInt(111) * API.sleepMod);
    }
}
