package physicsFight;

import apcs.Window;

public class Sword {

	Player p;
	int x;
	int y;
	int w = 10;
	int h = 45;
	double angle;
	public int damage;
	public int mMin;
	public int mMax;
	
	public boolean show = true;
	
	public Sword(Player p, int damage, int min, int max) {
		this.p = p;
		
		mMin = min;
		mMax = max;
		this.damage = damage;
		
		this.x = p.x + (-p.dir*30);
		this.y = p.dy + h+10;
		this.angle = 0;
	}

	public void update(Boss b, Boss b2) {
		this.x = p.x + (-p.dir*40);
		if (p.grav == 1) {
			this.y = p.y - h/2;
		}
		if (p.grav == -1) {
			this.y = p.y + h;
		}
		
		if (Window.mouse.clicked()) {
			angle = -p.dir*35;
		}
		if (Window.mouse.released()) {
			angle = 0;
		}
		
		if (x >= b.x && x <= b.x+b.length && y >= b.y && y <=b.length) {
			int modifier = Window.random(mMin, mMax);
			b.health -= damage + modifier;
			b.opHealth += damage + modifier;
		}
		if (x >= b2.x && x <= b2.x+b2.length && y >= b2.y && y <=b2.length) {
			int modifier = Window.random(mMin, mMax);
			b.health -= damage + modifier;
			b.opHealth += damage + modifier;
		}
	}
	
	public void show() {
		if (show)  {
			Window.out.color("green");
			Window.out.rectangle(x, y, w, h, angle);
			//Window.out.image("C:\\Users\\heath\\Desktop\\java class\\Example with Teachers\\src\\physicsFight\\res\\sword.png", x, y, angle);
		}
	}

	public void doNotShow() {
		show = false;
		
	}

}
