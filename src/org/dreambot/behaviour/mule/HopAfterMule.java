package org.dreambot.behaviour.mule;

import java.util.List;

import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.container.impl.equipment.Equipment;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.map.Tile;
import org.dreambot.api.methods.tabs.Tab;
import org.dreambot.api.methods.tabs.Tabs;
import org.dreambot.api.methods.walking.impl.Walking;
import org.dreambot.api.methods.world.Worlds;
import org.dreambot.api.wrappers.interactive.Player;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.API;
import org.dreambot.utilities.ScriptAreas;
import org.dreambotScript.Main;

public class HopAfterMule extends Leaf<Main> {

    @Override
    public boolean isValid() {
    	return Inventory.contains(API.TIARA_STR);
    }

    @Override
    public int onLoop() {
    	API.randomWorld = 0;
    	return (int) ((double) 50 + API.rand2.nextInt(111) * API.sleepMod);
    }
}
