package org.dreambot.behaviour.stuff;

import java.util.ArrayList;
import java.util.List;

import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.container.impl.bank.Bank;
import org.dreambot.api.methods.container.impl.bank.BankMode;
import org.dreambot.api.methods.container.impl.equipment.Equipment;
import org.dreambot.api.methods.input.Keyboard;
import org.dreambot.api.methods.settings.PlayerSettings;
import org.dreambot.api.methods.widget.Widget;
import org.dreambot.api.methods.widget.Widgets;
import org.dreambot.api.wrappers.items.Item;
import org.dreambot.api.wrappers.widgets.WidgetChild;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.API;
import org.dreambotScript.Main;

public class BankShit extends Leaf<Main> {

    @Override
    public boolean isValid() {
    	return Bank.isOpen();
    }
    /**
	 * Check for anything in inventory except pure essence, if so, deposit all
	 * Check for anything in equipment except air tiara, if so, deposit all equipment
	 * if no air tiara equipped, check inventory for air tiara, if have, equip
	 * if no air tiara in equipment or inventory, check bank, if so, withdraw one
	 * if no air tiara to be found, system.exit();
	 * check inventory for 27 pure essence
	 * check bank for any pure essence, if not, system.exit();
	 * if none, check withdraw quantity set to 27, if not, set to 27, check if set to withdraw-x, if not, set it
	 * withdraw 27 pure essence, close bank
	 */
    
    
	
    @Override
    public int onLoop() {
    	API.randomAFK();
    	boolean waitItems = false;
    	boolean waitEquipment = false;
    	if(!Inventory.isEmpty() && !Inventory.onlyContains(API.PURE_ESS) && !Inventory.onlyContains(API.TIARA))
    	{
    		MethodProvider.log("Test");
    		Bank.depositAllItems();
    		waitItems = true;
    	}
    	if(!Equipment.onlyContains(API.TIARA) && !Equipment.isEmpty())
    	{
    		Bank.depositAllEquipment();
    		waitEquipment = true;
    	}
    	if(waitItems) MethodProvider.sleepUntil(Inventory::isEmpty, (int) ((double) 1555 + API.rand2.nextInt(999) * API.sleepMod));
    	if(waitEquipment) MethodProvider.sleepUntil(Equipment::isEmpty, (int) ((double) 1555 + API.rand2.nextInt(999) * API.sleepMod));
    	
    	if(!Equipment.contains(API.TIARA)) {
    		if(!Inventory.contains(API.TIARA)) {
    			if(!Bank.contains(API.TIARA_STR)) {
    				MethodProvider.log("RIP no tiara, buy one!!");
    				if(!Inventory.isEmpty()) Bank.depositAllItems();
    				API.mule = true;
    			} else if(Bank.withdraw(API.TIARA)) {
    				MethodProvider.sleepUntil(() -> Inventory.contains(API.TIARA), (int) ((double) 1555 + API.rand2.nextInt(999) * API.sleepMod)); 
    			}
    		} else {
    			if(Inventory.get(API.TIARA).interact("Wear")){
    				MethodProvider.sleepUntil(() -> Equipment.contains(API.TIARA), (int) ((double) 1555 + API.rand2.nextInt(999) * API.sleepMod)); 
    	    		
    			}
			}
    	} else {
    		if(Inventory.contains(API.PURE_ESS) && Inventory.count(API.PURE_ESS) == 27) {
        		if(Bank.close()) {
        			MethodProvider.sleepUntil(() -> !Bank.isOpen(), (int) ((double) 888 + API.rand2.nextInt(888) * API.sleepMod)); 
        		}
        	} else {
        		if(!Bank.contains(API.PURE_ESS)) {
        			MethodProvider.log("RIP no pure essence, buy more!!");
        			if(!Inventory.isEmpty()) Bank.depositAllItems();
    				API.mule = true;
        		} else {
        			API.essence = Bank.count(API.PURE_ESS);
        		}
        		/**
            	 * now we need to verify withdraw settings and correct them if necessary:
                 * Playersetting (115) = 0 -> Unnoted
                 * Playersetting (304) = x -> 2 * (Withdraw-x qty)
                 * Playersetting (1666) = 12 -> Withdraw-X
                 */
            	if(Bank.getWithdrawMode() != BankMode.ITEM) {
            		Bank.setWithdrawMode(BankMode.ITEM);
            	}
            	if(PlayerSettings.getConfig(304) != (2 * API.WITHDRAW_QTY)) { // playersettings stores this value as 2x the actual withdraw qty
            		WidgetChild enterWithdrawQty = Widgets.getChildWidget(162,42);
            		WidgetChild withdrawXWidget = Widgets.getChildWidget(12,35);
            		if(enterWithdrawQty != null && enterWithdrawQty.isVisible()) {
            			Keyboard.type(API.WITHDRAW_QTY, true);
            			MethodProvider.sleepUntil(() -> PlayerSettings.getConfig(1666) == 12, (int) ((double) 888 + API.rand2.nextInt(888) * API.sleepMod));
            		} else if (withdrawXWidget != null && withdrawXWidget.isVisible()){
            			if(withdrawXWidget.interact("Set custom quantity")){
            				MethodProvider.sleepUntil(() -> (enterWithdrawQty != null && enterWithdrawQty.isVisible()),(int) ((double) 1500 + API.rand2.nextInt(1111) * API.sleepMod));
            	    	}
            		}
            	}
            	if(PlayerSettings.getConfig(1666) != 12) {
            		if(Widgets.getChildWidget(12,35).interact()) {
                		MethodProvider.sleepUntil(() -> PlayerSettings.getConfig(1666) == 12, (int) ((double) 1555 + API.rand2.nextInt(1111) * API.sleepMod));
            		}
            	}
            	
            	if(PlayerSettings.getConfig(1666) != 12 ||
            			Bank.getWithdrawMode() != BankMode.ITEM || 
            			PlayerSettings.getConfig(304) != (2 * API.WITHDRAW_QTY)) {
            		return (int) ((double) 50 + API.rand2.nextInt(111) * API.sleepMod);
            	}
            	
            	if(Bank.get(API.PURE_ESS).interact("Withdraw-27")) {
            		MethodProvider.sleepUntil(() -> (Inventory.contains(API.PURE_ESS) && Inventory.count(API.PURE_ESS) == 27), (int) ((double) 1555 + API.rand2.nextInt(1111) * API.sleepMod));
            	}
        	}
    	}
    	
    	return (int) ((double) 50 + API.rand2.nextInt(999) * API.sleepMod);
    }

}
