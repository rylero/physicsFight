package physicsFight;

import apcs.Window;

public class worldFinals {

	public static final int sideLength = 50;

	public static float gravity = 2f;

	public static int[] spawns = {
			Window.width()/2-sideLength/4,Window.height()/2,
			500,70,
			900,70
	};
	
	public static Character[] characters = {
			new Character("Iron Man",3.0f,1.9f,725,255,215,0)
	};
}
