package org.dreambotScript;

import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.methods.container.impl.bank.Bank;
import org.dreambot.api.methods.map.Map;
import org.dreambot.api.methods.map.Tile;
import org.dreambot.api.methods.world.Worlds;
import org.dreambot.api.randoms.RandomEvent;
import org.dreambot.api.script.AbstractScript;
import org.dreambot.api.script.Category;
import org.dreambot.api.script.ScriptManifest;
import org.dreambot.api.script.listener.ChatListener;
import org.dreambot.api.script.listener.InventoryListener;
import org.dreambot.api.utilities.Timer;
import org.dreambot.api.wrappers.items.Item;
import org.dreambot.api.wrappers.widgets.message.Message;
import org.dreambot.behaviour.*;
import org.dreambot.behaviour.initialization.*;
import org.dreambot.behaviour.mule.*;
import org.dreambot.behaviour.stuff.*;
import org.dreambot.framework.Branch;
import org.dreambot.framework.Tree;
import org.dreambot.paint.CustomPaint;
import org.dreambot.paint.PaintInfo;
import org.dreambot.utilities.*;
import java.awt.*;

@ScriptManifest(author = "Dreambotter420", name = "Essence Runner", 
description = "Runs essence to a custom player on a custom world at air or body altar", version = 1.2, category = Category.MISC, image = "lFwvTuW.jpg")
public class Main extends AbstractScript implements PaintInfo, ChatListener{

    /**
     * @param args script quick launch arguments
     */

    @Override
    public void onStart(String... args) {
    	ScriptAreas.OVERSHOT_ALTAR = ScriptAreas.OVERSHOT_ALTARAREA_AIR;
		ScriptAreas.OVERSHOT_BANK = ScriptAreas.OVERSHOT_BANK_FALLY;
		ScriptAreas.DESIRABLE_ALTAR = ScriptAreas.DESIRABLE_ALTARAREA_AIR;
		ScriptAreas.INSIDE_ALTAR = ScriptAreas.INSIDE_ALTARAREA_AIR;
		ScriptAreas.DESIRABLE_BANK = ScriptAreas.DESIRABLE_BANKAREA_FALLY;
		ScriptAreas.tradeTile = ScriptAreas.tradeTileAir;
		API.TIARA = API.AIR_TIARA;
		API.TIARA_STR = "Air tiara";
		MethodProvider.log("Default area: Air Altar");
    	for(String argument : args) {
    		if(argument.toLowerCase().startsWith("body")) {
    			ScriptAreas.OVERSHOT_ALTAR = ScriptAreas.OVERSHOT_ALTARAREA_BODY;
    			ScriptAreas.OVERSHOT_BANK = ScriptAreas.OVERSHOT_BANK_EDGEVILLE;
    			ScriptAreas.DESIRABLE_ALTAR = ScriptAreas.DESIRABLE_ALTARAREA_BODY;
    			ScriptAreas.INSIDE_ALTAR = ScriptAreas.INSIDE_ALTARAREA_BODY;
    			ScriptAreas.DESIRABLE_BANK = ScriptAreas.DESIRABLE_BANK_EDGEVILLE;
    			ScriptAreas.tradeTile = ScriptAreas.tradeTileBody;
    			API.TIARA = API.BODY_TIARA;
    			API.TIARA_STR = "Body tiara";
    			MethodProvider.log("Setting area: Body Altar");
    		}
    		if(argument.toLowerCase().startsWith("air")) {
    			ScriptAreas.OVERSHOT_ALTAR = ScriptAreas.OVERSHOT_ALTARAREA_AIR;
    			ScriptAreas.OVERSHOT_BANK = ScriptAreas.OVERSHOT_BANK_FALLY;
    			ScriptAreas.DESIRABLE_ALTAR = ScriptAreas.DESIRABLE_ALTARAREA_AIR;
    			ScriptAreas.INSIDE_ALTAR = ScriptAreas.INSIDE_ALTARAREA_AIR;
    			ScriptAreas.DESIRABLE_BANK = ScriptAreas.DESIRABLE_BANKAREA_FALLY;
    			ScriptAreas.tradeTile = ScriptAreas.tradeTileAir;
    			API.TIARA = API.AIR_TIARA;
    			API.TIARA_STR = "Air tiara";
    			MethodProvider.log("Setting area: Air Altar");
    		}
    		
    	}
    	for(String argument : args) {
    		if(argument.startsWith("mule=")) {
    			API.mueller = argument.replace("mule=","");
    			if(API.traderName.isBlank()) {
    				MethodProvider.log("Mule name blank! Exiting quickstarted script...");
    				this.stop();
    			}
    			MethodProvider.log("Will look for mule with name: " + API.mueller);
    		}
    		if(argument.startsWith("crafter=")) {
    			API.traderName = argument.replace("crafter=","");
    			if(API.traderName.isBlank()) {
    				MethodProvider.log("Crafter name blank! Exiting quickstarted script...");
    				this.stop();
    			}
    			MethodProvider.log("Assigned to runecrafter with name: " + API.traderName);
    		}
    		if(argument.startsWith("world=")) {
    			API.customWorld = Integer.parseInt(argument.replace("world=",""));
    			MethodProvider.log("Assigned custom world:" + API.customWorld);
    		}
    		if(argument.startsWith("muletile=")) {
    			String tileInput = argument.replace("muletile=","");
    			String[] xyz = tileInput.split(",",0);
    			if(xyz.length < 3) {
    				MethodProvider.log("Mule Tile input too short! Needs to specify x,y,z coordinates. Exiting quickstarted script...");
    				MethodProvider.log("" + xyz[0]);
    				MethodProvider.log("" + xyz[1]);
    				MethodProvider.log("" + xyz[2]);
    				this.stop();
    			}
    			if(xyz.length > 3) {
    				MethodProvider.log("Mule Tile input too long! Needs to specify x,y,z coordinates. Exiting quickstarted script...");
    				MethodProvider.log("" + xyz[0]);
    				MethodProvider.log("" + xyz[1]);
    				MethodProvider.log("" + xyz[2]);
    				this.stop();
    			}
    			ScriptAreas.muleTile = Map.getWalkable(new Tile(Integer.parseInt(xyz[0]),Integer.parseInt(xyz[1]),Integer.parseInt(xyz[2])));
    			MethodProvider.log("Assigning Mule Tile: ("+
    					ScriptAreas.muleTile.getX()+
    					","+ScriptAreas.muleTile.getY()+
    					","+ScriptAreas.muleTile.getZ()+")");
    		}
    		if(argument.startsWith("tradetile=")) {
    			String tileInput = argument.replace("tradetile=","");
    			String[] xyz = tileInput.split(",",0);
    			if(xyz.length < 3) {
    				MethodProvider.log("Trade Tile input too short! Needs to specify x,y,z coordinates. Exiting quickstarted script...");
    				MethodProvider.log("" + xyz[0]);
    				MethodProvider.log("" + xyz[1]);
    				MethodProvider.log("" + xyz[2]);
    				this.stop();
    			}
    			if(xyz.length > 3) {
    				MethodProvider.log("Trade Tile input too long! Needs to specify x,y,z coordinates. Exiting quickstarted script...");
    				MethodProvider.log("" + xyz[0]);
    				MethodProvider.log("" + xyz[1]);
    				MethodProvider.log("" + xyz[2]);
    				this.stop();
    			}
    			ScriptAreas.tradeTile = Map.getWalkable(new Tile(Integer.parseInt(xyz[0]),Integer.parseInt(xyz[1]),Integer.parseInt(xyz[2])));
    			MethodProvider.log("Assigning Trade Tile: ("+
    					ScriptAreas.tradeTile.getX()+
    					","+ScriptAreas.tradeTile.getY()+
    					","+ScriptAreas.tradeTile.getZ()+")");
    		}
    	}
        instantiateTree();
        getRandomManager().disableSolver(RandomEvent.DISMISS);
    }

    /**
     * On start from script launcher
     */
    @Override
    public void onStart() {
    	ScriptAreas.OVERSHOT_ALTAR = ScriptAreas.OVERSHOT_ALTARAREA_AIR;
		ScriptAreas.OVERSHOT_BANK = ScriptAreas.OVERSHOT_BANK_FALLY;
		ScriptAreas.DESIRABLE_ALTAR = ScriptAreas.DESIRABLE_ALTARAREA_AIR;
		ScriptAreas.INSIDE_ALTAR = ScriptAreas.INSIDE_ALTARAREA_AIR;
		ScriptAreas.DESIRABLE_BANK = ScriptAreas.DESIRABLE_BANKAREA_FALLY;
		ScriptAreas.tradeTile = ScriptAreas.tradeTileAir;
		API.TIARA = API.AIR_TIARA;
		API.TIARA_STR = "Air tiara";
        instantiateTree();
        getRandomManager().disableSolver(RandomEvent.DISMISS);
    }

    private final Tree<Main> tree = new Tree<>();
    private Branch<Main> initScript;
    private Branch<Main> altarnotFullyInventoried;
    private Branch<Main> notFullyInventoried;
    private Branch<Main> fullyInventoried;
    private Branch<Main> altarfullyInventoried;
    private Branch<Main> mule;
    private Branch<Main> exitOtherPortal;
    
   
    private void instantiateTree() {
    	mule = new ToMule();
    	exitOtherPortal = new ExitOppositePortals();
    	initScript = new InitializeScriptStuff();
    	notFullyInventoried = new NotFullInvy();
    	fullyInventoried = new FullInvy();
    	altarfullyInventoried = new FullInvyInsideArea();
    	altarnotFullyInventoried = new NotFullInvyInsideArea();
    	
    	tree.addBranch(initScript.addLeafs(
        		new CloseAllWidgets(),
        		new DisableRoofs(),
        		new GoFixedMode(), 
        		new DisableLeftClickAttackStuff(),
        		new UntoggleAIDS(), //randomizes world for next step
        		new HopRandomWorld(),
        		new SetCameraUp(), 
        		new Initialize()));
    	
    	tree.addBranch(exitOtherPortal.addLeafs(
    			new ExitOpposingPortals()));
    	
    	tree.addBranch(mule.addLeafs(
    			new CheckMuled(),
    			new WalkToMuleArea(),
        		new HopRandomWorld_Mule(),
        		new HopAfterMule(),
        		new HopCustomWorld(),
    			new HandleMuling(),
    			new SendTrade()));
    	
    	tree.addBranch(altarnotFullyInventoried.addLeafs(
        		new RandomizeWorld(),
        		new HopRandomWorld(),
        		new ClickPortal()));
        
        tree.addBranch(altarfullyInventoried.addLeafs(
        		new HandleTrading(),
        		new WalkToTradeTile(),
        		new HopCustomWorld(),
        		new SendTrade()));
        
        tree.addBranch(fullyInventoried.addLeafs(
        		new EnableRun(),
        		new ClickAltar(),
        		new WalkToArea(),
        		new FindTileInArea()));
        
        tree.addBranch(notFullyInventoried.addLeafs(
        		new BankShit(),
        		new SetCameraUpAgain(),
        		new EnableRun(),
        		new ClickBank(),
        		new WalkToBank(),
        		new FindTileInBank()));
        
        
        
    }

    /**
     * onLoop is a infinite loop
     * @return gets the leaf and executes it
     */

    @Override
    public int onLoop() {
        return this.tree.onLoop();
    }

    @Override
    public void onGameMessage(Message msg) {
    	if(msg.getMessage().contains("Other player is busy at the moment")) {
    		API.resendTrade = true;
    	}
    	if(msg.getMessage().contains("Your account is currently restricted for trading")) {
    		MethodProvider.log("Exiting Ess Runner script due to non-integration of trade unlocking features:");
    		MethodProvider.log("|||||||||||||||||||||||");
    		MethodProvider.log("" + msg.getMessage());
    		this.stop();
    	}
    }
    
    /**
     * @return the information for the paint
     */
    @Override
    public String[] getPaintInfo() {
        String altar = "";
        String bank = "";
    	if(ScriptAreas.DESIRABLE_ALTAR != null && ScriptAreas.DESIRABLE_ALTAR.equals(ScriptAreas.DESIRABLE_ALTARAREA_AIR)) {
        	altar = "AIR ALTAR";
        	bank = "FALLY BANK";
        } else {
        	altar = "BODY ALTAR";
        	bank = "EDGEVILLE BANK";
        }
    	return new String[] {
                "Essence Runner v" + getManifest().version()+" by Dreambotter420 ^_^",
                "Runtime " + Timer.formatTime(API.runTimer.elapsed()),
                "Current Branch: " + API.currentBranch,
                "Current Leaf: " + API.currentLeaf,
                "Altar: " + altar,
                "Bank: " + bank,
                "Player to trade: " + API.traderName,
                "Custom world: " + API.customWorld,
                "Current world: " + Worlds.getCurrentWorld(),
                "Essence in bank left: " + API.essence,
               
                "XP/hour: 0!!"
        };
        
    }
    
    /**
     * Instantiate the paint object, can be customized to liking.
     */
    private final CustomPaint CUSTOM_PAINT = new CustomPaint(this,
            CustomPaint.PaintLocations.TOP_LEFT_PLAY_SCREEN, new Color[]{new Color(255, 251, 255)},
            "Trebuchet MS",
            new Color[]{new Color(50, 50, 50, 175)},
            new Color[]{new Color(28, 28, 29)},
            1, false, 5, 3, 0);

    private final RenderingHints aa = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);


    /**
     * paint for the script
     */
    @Override
    public void onPaint(Graphics g) {
        Graphics2D gg = (Graphics2D) g;
        gg.setRenderingHints(aa);

        CUSTOM_PAINT.paint(gg);
    }

	
    

}
