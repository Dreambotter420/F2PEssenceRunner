package org.dreambot.behaviour.initialization;

import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.methods.input.Camera;
import org.dreambot.api.methods.widget.Widgets;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.*;
import org.dreambotScript.Main;


public class CloseAllWidgets extends Leaf<Main>{

    @Override
    public boolean isValid() {
    	return Widgets.isOpen();
    }

    @Override
    public int onLoop() {
    	if(Widgets.closeAll())
    	{
    		MethodProvider.log("Closed some initial widgets");
    	}
    	
    	
		return (int) ((double) 25 + API.rand2.nextInt(25) * API.sleepMod);
    }

}