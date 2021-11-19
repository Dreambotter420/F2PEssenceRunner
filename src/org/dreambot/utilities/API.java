package org.dreambot.utilities;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.methods.map.Tile;
import org.dreambot.api.methods.world.World;
import org.dreambot.api.methods.world.Worlds;
import org.dreambot.api.utilities.Timer;

public class API {
	public static String currentBranch = "";
	public static String currentLeaf = "";
	
	/** ignore
	 * "Use" Portal
	 * "Enter" Mysterious ruins
	 * "Bank" Bank booth
	 */
	
	/**
	 * Script will hop to random world, 
	 * grab essence from falador bank and equip air tiara (if none of these exist, script closes client)
	 * walk/run to air altar, enter, walk to trade tile,
	 * hop to custom world,
	 * trade 27 essence towards custom RSN player
	 * hop back to another random world, exit portal, go to bank, repeat
	 */
	
	/**
	 * Set custom world to use;
	 * Set custom RSN to trade;
	 * Set custom tile to trade on;
	 */
	
	public static String traderName = "";
	public static int customWorld = 335;
	public static String mueller = "";
	
	
	public static boolean mule = false;
	public static Timer sendTradeTimer;
	public static int PURE_ESS = 7936;
	public static int TIARA;
	public static String TIARA_STR;
	public static int AIR_TIARA = 5527;
	public static int BODY_TIARA = 5533;
	public static int WITHDRAW_QTY = 27;
	public static Timer cameraTimer;
	public static boolean resendTrade = false;
	public static boolean initBank = false;
	public static int randomRun = 0;
	public static int randomWorld = 0;
	public static Tile randomTileInBank = null;
	public static Tile randomTileInArea = null;
	public static int worldHops = 0;
	public static boolean initialized = false;
	public static int walkFails = 0;
	public static int randZoom = 0;
	public static int randPitch = 0;
	public static Random rand2 = new Random();
	public static int essence = 0;
	public static double sleepMod;
	public static Timer runTimer = new Timer(1000000000);
	public static int randDestLimit = 0;
	public static void randomAFK()
	{
		int tmp = API.rand2.nextInt(20000);
		if(tmp < 2)  
		{
			int tmp2 = (int) ((double) 50 + API.rand2.nextInt(60000) * API.sleepMod);
			MethodProvider.logInfo("AFK: 0.001% chance, max 240s, randomized for " +tmp2+ "ms");
			MethodProvider.sleep(tmp2);
		}
		else if(tmp < 6)  
		{
			int tmp2 = (int) ((double) 50 + API.rand2.nextInt(30000) * API.sleepMod);
			MethodProvider.logInfo("AFK: 0.003% chance, max 120s, randomized for " +tmp2+ "ms");
			MethodProvider.sleep(tmp2);
		}
		else if(tmp < 25)
		{
			int tmp2 = (int) ((double) 50 + API.rand2.nextInt(10000) * API.sleepMod);
			MethodProvider.logInfo("AFK: 0.095% chance, max 40s, randomized for " +tmp2+ "ms");
			MethodProvider.sleep(tmp2);
		}
		else if(tmp < 150)  
		{
			int tmp2 = (int) ((double) 50 + API.rand2.nextInt(5000) * API.sleepMod);
			MethodProvider.logInfo("AFK: .625% chance, max 20s, randomized for " +tmp2+ "ms");
			MethodProvider.sleep(tmp2);
		}
		else if(tmp < 1000)  
		{
			int tmp2 = (int) ((double) 600 + API.rand2.nextInt(1200) * API.sleepMod);
			MethodProvider.logInfo("AFK: 4.25% chance, max 6.0s, randomized for " +tmp2+ "ms");
			MethodProvider.sleep(tmp2);
		}
		else if(tmp < 3000)  
		{
			int tmp2 = (int) ((double) 600 + API.rand2.nextInt(600) * API.sleepMod);
			MethodProvider.logInfo("AFK: 10.0% chance, max 3.2s, randomized for " +tmp2+ "ms");
			MethodProvider.sleep(tmp2);
		}
	}
	public static void randomizeWorld()
	{
		List<World> worlds = Worlds.noMinimumLevel();
		List<World> verifiedWorlds = new ArrayList<World>();
		for(World tmp : worlds)
		{
			if(		tmp.isMembers()
					|| tmp.isPVP()
					|| !tmp.isNormal()
					|| !tmp.isF2P()
					|| tmp.isSuspicious()
					|| tmp.isTournamentWorld()
					|| tmp.isTwistedLeague()
					|| tmp.isDeadmanMode()
					|| tmp.getWorld() == 301 //just avoid popular world
					|| tmp.getWorld() == customWorld)
			{
				//skip world
			} else verifiedWorlds.add(tmp);
		}
		randomWorld = verifiedWorlds.get(rand2.nextInt(verifiedWorlds.size() - 1)).getWorld();
	}
}
