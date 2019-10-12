package physicsFight;

import apcs.Window;

public class Main {

	public static void main(String[] args) {
		run();
	}
	
	public static void run() {
		
		Window.size(1270,690);
		
		Boss b = new Boss(250, 9, 4500);
		Boss b2 = new Boss(250, 9, 19050);
		
		Player p = new Player(Window.random(0,worldFinals.characters.length-1),0); // spawnIndex, CharacterId
		Sword s = new Sword(p,17,0,5);
		
		boolean pause = false;
		
		//System.out.println(p);
		//System.out.println(s);
		//System.out.println(b);
		
		while (true) {
			Window.frame();
			Window.out.color("black");
			Window.out.rectangle(0, 0, 1270, 690);
			
			
			if (!pause) {
				p.update();
			}
			p.show(s,b);
			p.showStats(b);
			
			if (!pause) {
				s.update(b,b2);
			}
			s.show();
			
			if (!pause) {
				b.update(p,s);
			}
			b.show(p);
			
			if (!pause) {
				b2.update(p,s);
			}
			b2.show(p);
			
			if (Window.key.pressed('p')) {
				if (pause) {
					pause = false;
				} else {
					pause = true;
				}
			}
			
			if (pause) {
				Window.out.color("white");
				Window.out.rectangle(0, Window.height()/2, 2600, 40);
				Window.out.color("black");
				Window.out.print("Game Paused",  Window.width()/2, Window.height()/2+5);
			}
			
			if (Window.key.pressed('r')) {
				Main.run();
			}
			
			
			
			Window.out.color("white");
			Window.out.fontSize(30);
		}
		}
}