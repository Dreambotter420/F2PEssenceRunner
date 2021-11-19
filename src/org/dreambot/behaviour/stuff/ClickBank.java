package org.dreambot.behaviour.stuff;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.dreambot.api.Client;
import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.methods.container.impl.bank.Bank;
import org.dreambot.api.methods.interactive.GameObjects;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.wrappers.interactive.GameObject;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.API;
import org.dreambot.utilities.ScriptAreas;
import org.dreambotScript.Main;

public class ClickBank extends Leaf<Main> {

    @Override
    public boolean isValid() {
    	return ScriptAreas.DESIRABLE_BANK.contains(Players.localPlayer()) && !Bank.isOpen();
    }

    @Override
    public int onLoop() {
    	API.randomAFK();
    	List<GameObject> banks = GameObjects.all("Bank booth");
    	ArrayList<GameObject> verifiedBanks = new ArrayList<GameObject>();
    	for(GameObject bank : banks)
    	{
    		if(bank != null && bank.exists() && ScriptAreas.DESIRABLE_BANK.contains(bank)) 
    		{
    			verifiedBanks.add(bank);
    		}
    	}
    	Collections.shuffle(verifiedBanks);
    	GameObject randomBank = verifiedBanks.get(0);
    	if(randomBank.interact("Bank")){
    		MethodProvider.sleepUntil(Bank::isOpen, (int) ((double) 15000 + API.rand2.nextInt(3000) * API.sleepMod));
		} else {
    		MethodProvider.logInfo("No bank or npc found in range of bank area!!!!");
    		MethodProvider.sleepUntil(() -> Client.getIdleTime() >= (int) ((double) 800 + API.rand2.nextInt(50) * API.sleepMod), 20000);
    	}
    	
    	return (int) ((double) 50 + API.rand2.nextInt(555) * API.sleepMod);
    }
}
