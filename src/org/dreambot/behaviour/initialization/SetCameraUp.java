package org.dreambot.behaviour.initialization;

import org.dreambot.api.methods.input.Camera;
import org.dreambot.api.methods.widget.Widgets;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.*;
import org.dreambotScript.Main;


public class SetCameraUp extends Leaf<Main>{

    @Override
    public boolean isValid() {
    	return Camera.getPitch() < 301 || Camera.getZoom() > 250;
    }

    @Override
    public int onLoop() {
    	if(Widgets.closeAll())
    	{
    		if(Camera.getPitch() < 301)
        	{
        		if(API.randPitch == 0) API.randPitch = (int) ((double) 330 + API.rand2.nextInt(8) * API.sleepMod); //between 330 and 364 pitch
        		Camera.mouseRotateToPitch(API.randPitch);
        		API.randPitch = 0;
        	}
        	if(Camera.getZoom() > 250)
        	{
        		if(API.randZoom == 0) API.randZoom = (int) ((double) 183 + API.rand2.nextInt(15) * API.sleepMod); //between 183 and 243 zoom
        		Camera.setZoom(API.randZoom);
        		API.randZoom = 0;
        	}
    	}
    	
    	
		return (int) ((double) 25 + API.rand2.nextInt(25) * API.sleepMod);
    }

}