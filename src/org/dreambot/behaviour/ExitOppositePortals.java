package org.dreambot.behaviour;

import org.dreambot.api.Client;
import org.dreambot.api.data.GameState;
import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.world.Worlds;
import org.dreambot.framework.Root;
import org.dreambot.utilities.API;
import org.dreambot.utilities.ScriptAreas;
import org.dreambotScript.Main;

public class ExitOppositePortals extends Root<Main> {
    @Override
    public boolean isValid() {
    	return Client.getGameState() == GameState.LOGGED_IN &&
    			((ScriptAreas.INSIDE_ALTAR.equals(ScriptAreas.INSIDE_ALTARAREA_AIR) && 
    					ScriptAreas.INSIDE_ALTARAREA_BODY.contains(Players.localPlayer())) || 
    					(ScriptAreas.INSIDE_ALTAR.equals(ScriptAreas.INSIDE_ALTARAREA_BODY) && 
    	    					ScriptAreas.INSIDE_ALTARAREA_AIR.contains(Players.localPlayer())));
    }

}
