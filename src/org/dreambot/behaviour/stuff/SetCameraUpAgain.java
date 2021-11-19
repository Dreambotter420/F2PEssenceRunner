package org.dreambot.behaviour.stuff;

import org.dreambot.api.methods.input.Camera;
import org.dreambot.api.methods.widget.Widgets;
import org.dreambot.api.utilities.Timer;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.*;
import org.dreambotScript.Main;


public class SetCameraUpAgain extends Leaf<Main>{

    @Override
    public boolean isValid() {
    	return API.cameraTimer.finished() && (Camera.getPitch() < 301 || Camera.getZoom() > 250);
    }

    @Override
    public int onLoop() {
    	if(Widgets.isOpen()) Widgets.closeAll();
    	if(!Widgets.isOpen())
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
    	if(Camera.getPitch() >= 301 && Camera.getZoom() <= 250)
    	{
    		API.cameraTimer = new Timer((int) ((double) 60000 + API.rand2.nextInt(150000) * API.sleepMod));
    	}
		return (int) ((double) 25 + API.rand2.nextInt(25) * API.sleepMod);
    }
}