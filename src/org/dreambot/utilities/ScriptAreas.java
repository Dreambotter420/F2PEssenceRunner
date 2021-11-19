package org.dreambot.utilities;

import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.map.Tile;

public class ScriptAreas {
	public static String currentBranch = "";
	public static String currentLeaf = "";
	
	public static Area INSIDE_ALTAR;
	public static Area OVERSHOT_ALTAR;
	public static Area DESIRABLE_ALTAR;
	public static Area DESIRABLE_BANK;
	public static Area OVERSHOT_BANK;
	public static Tile tradeTile;
	
	public static Tile tradeTileAir = new Tile(2842, 4832, 0);
	public static Tile tradeTileBody = new Tile(2521, 4838, 0);
	public static final Area muleArea = new Area(
			new Tile(3077, 3513, 1),
			new Tile(3083, 3513, 1),
			new Tile(3083, 3508, 1),
			new Tile(3077, 3508, 1));
	public static Tile muleTile = new Tile(3078, 3513, 1);
	public static final Area INSIDE_ALTARAREA_BODY = new Area(
			new Tile(2500, 4846, 0),
			new Tile(2501, 4853, 0),
			new Tile(2507, 4860, 0),
			new Tile(2533, 4860, 0),
			new Tile(2542, 4852, 0),
			new Tile(2538, 4835, 0),
			new Tile(2532, 4828, 0),
			new Tile(2518, 4819, 0),
			new Tile(2505, 4832, 0));
	public static final Area OVERSHOT_ALTARAREA_BODY = new Area(
			new Tile(3055, 3447, 0),
			new Tile(3056, 3444, 0),
			new Tile(3053, 3441, 0),
			new Tile(3044, 3439, 0),
			new Tile(3041, 3441, 0),
			new Tile(3036, 3444, 0),
			new Tile(3041, 3449, 0),
			new Tile(3043, 3449, 0),
			new Tile(3048, 3449, 0));
	public static final Area DESIRABLE_ALTARAREA_BODY = new Area(
			new Tile(3049, 3447, 0),
			new Tile(3053, 3450, 0),
			new Tile(3058, 3453, 0),
			new Tile(3064, 3450, 0),
			new Tile(3065, 3447, 0),
			new Tile(3066, 3445, 0),
			new Tile(3066, 3439, 0),
			new Tile(3062, 3435, 0),
			new Tile(3056, 3435, 0),
			new Tile(3049, 3439, 0));
	public static final Area OVERSHOT_BANK_EDGEVILLE = new Area(
			new Tile(3091, 3488, 0),
			new Tile(3091, 3499, 0),
			new Tile(3092, 3503, 0),
			new Tile(3097, 3505, 0),
			new Tile(3101, 3503, 0),
			new Tile(3104, 3498, 0),
			new Tile(3100, 3496, 0),
			new Tile(3098, 3493, 0),
			new Tile(3095, 3488, 0));
	public static final Area DESIRABLE_BANK_EDGEVILLE = new Area(
			new Tile(3091, 3499, 0),
			new Tile(3097, 3499, 0),
			new Tile(3098, 3498, 0),
			new Tile(3101, 3496, 0),
			new Tile(3102, 3494, 0),
			new Tile(3102, 3490, 0),
			new Tile(3100, 3485, 0),
			new Tile(3098, 3483, 0),
			new Tile(3082, 3483, 0),
			new Tile(3086, 3496, 0));
	public static final Area OVERSHOT_BANK_FALLY = new Area(
			new Tile(3009, 3358, 0),
			new Tile(3009, 3355, 0),
			new Tile(3019, 3355, 0),
			new Tile(3019, 3357, 0),
			new Tile(3017, 3359, 0),
			new Tile(3019, 3364, 0));
	public static final Area DESIRABLE_BANKAREA_FALLY = new Area(
			new Tile(3019, 3367, 0),
			new Tile(3019, 3355, 0),
			new Tile(3018, 3350, 0),
			new Tile(3011, 3344, 0),
			new Tile(3006, 3345, 0),
			new Tile(3002, 3354, 0),
			new Tile(3007, 3368, 0),
			new Tile(3011, 3368, 0),
			new Tile(3014, 3367, 0));
	public static final Area DESIRABLE_ALTARAREA_AIR = new Area(
			new Tile(2980, 3291, 0),
			new Tile(2981, 3296, 0),
			new Tile(2988, 3302, 0),
			new Tile(2996, 3300, 0),
			new Tile(2998, 3295, 0),
			new Tile(2997, 3292, 0),
			new Tile(2992, 3289, 0),
			new Tile(2987, 3287, 0),
			new Tile(2983, 3288, 0),
			new Tile(2979, 3289, 0));
	public static final Area OVERSHOT_ALTARAREA_AIR = new Area(
			new Tile(2978, 3292, 0),
			new Tile(2980, 3294, 0),
			new Tile(2986, 3294, 0),
			new Tile(2987, 3291, 0),
			new Tile(2988, 3285, 0),
			new Tile(2984, 3282, 0),
			new Tile(2976, 3283, 0),
			new Tile(2974, 3285, 0),
			new Tile(2976, 3288, 0));
	public static final Area INSIDE_ALTARAREA_AIR = new Area(
			new Tile(2840, 4825, 0),
			new Tile(2836, 4829, 0),
			new Tile(2835, 4834, 0),
			new Tile(2837, 4839, 0),
			new Tile(2839, 4842, 0),
			new Tile(2847, 4843, 0),
			new Tile(2851, 4839, 0),
			new Tile(2852, 4828, 0),
			new Tile(2846, 4825, 0));
	
}
