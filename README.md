# F2P Essence Runner


A script that runs pure essence from bank to air or body altar & can only be ran on a Trade Unlocked account. Stays on random worlds all the time except when on 2 custom tiles, one to trade the runecrafter in the altar area, and another to trade the muler anywhere. So the behaviour of the bot goes: Hop to random world on script start, grab essence, go to altar, enter altar, walk to custom tile, hop to custom world, trade specified player the essence, hop to a different random world, exit altar portal, grab more essence, repeat. When banking, if the bot detects lack of tiara or pure essence then it will go to mule tile to wait for more supplies. The result: 

-55k XP/hr and 200k gp/hr at air altar and 95k XP/hr at body altar in F2P (with enough bots (20+)).

-Isolated area where humans are able to recognize "bot farm": Bots will not leave an obvious ant trail to your Runecrafter account which is blasting through RC levels, and only stack up at Mule Tile/world and Altar Area/world, even so they generally hop into the custom world on a single tile, trade, and hop out, so there is no mass movement amongst the bots. The exception is when your Runecrafter is more than 1 tile away from the trade tile.

-Lazily set up your numerous runner bots by only having to manually interface with a mule account which all the bots come to and trade over noted supplies before running can engage. Can be the same account that is doing the Crafting.


# Quickstart CLI params

This script requires customization to run, and supports Quickstart to accomplish this. The following -params are supported:

body  -> this makes it run body altar

air  -> this makes it run air altar

mule=mUlEnAmE  -> this makes it trade and accept supplies from named muled account

muletile=x,y,z  -> this makes it walk to specified tile to mule supplies when out (no pure essence or no tiara)

tradetile=x,y,z  -> (optional, script has coded default tiles) This Tile must be defined somewhere inside the respective altar areas (after entering either Air or Body Altar ruins), otherwise I'm not responsible for the nuclear apocolypse that may or may not result. This makes it stand here before hopping worlds to trade crafter.

crafter=cRaFtErNaMe  -> this makes it trade pure essence towards this player when inside altar area on custom world

world=x  -> this makes it mule & trade crafter only on this specific world


Alternatively, you can modify the code appropriately to have these values customized when launching the script:

# Main file

To change the default air altar to body altar, go to the function "public void onStart()" which does not have "onStart(String[] args)". In this function just copy+paste from the other onStart function all the variable assignments that pertain to either running Air altar, or Body altar. If you want to customize the tile to stand on while inside the altar area, change the following variable in the onStart() function:

ScriptAreas.tradeTile = new Tile(x, y, z);

# API file

public static String traderName = "cRaFtErNaMe";

public static int customWorld = x;

public static String mueller = "mUlEnAmE";

# ScriptAreas file

public static Tile muleTile = new Tile(x, y, z);

