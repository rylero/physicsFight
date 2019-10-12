package physicsFight;

import apcs.Window;

public class Boss {

	int x = 4500;
	int y = 100;
	int dx = -33;
	int dy = Window.random(-3, 3);
	int c = 0;
	public int health;
	public int opHealth = 0;
	public float damage;
	public int length = 200;

	public Boss(int health, float damage, int x) {
		this.health = health;
		this.damage = damage;
		this.x = x;
	}

	public void update(Player p, Sword s) {
		if (health > 0) {
			if (c % 5 == 0) {
				dy = Window.random(-3, 3);
			}

			if (x <= Window.width() / 2 - 100 && dy != 4) {
				dx = 0;
				if (y <= 0)
					dy = -1;
				else
					dy = 0;

			}

			x += dx;
			y += dy;
			c += 1;

			if (p.x >= x && p.x <= x + length && p.y >= y && p.y <= y + length && s.angle == 0) {
				p.health -= damage;
				p.opHealth += damage;
			}
		}
	}

	public void show(Player p) {
		if (p.isNotDead && health >= 0) {
			int xx = x + length / 2;
			int yy = y + length / 2;
			Window.out.color("red");
			Window.out.rectangle(xx, yy, length, length); // body
			Window.out.color("black");
			Window.out.circle(xx - 100 / 2, yy - 100 / 2, 20); // eye
			Window.out.circle(xx + 100 / 2, yy - 100 / 2, 20); // eye
			// fang
			Window.out.color("white");
			int width = 30;
			int length = 50;
			Window.out.line(xx - width / 2, yy, xx, yy + length);
			Window.out.line(xx - width / 2, yy, xx + width / 2, yy);
			Window.out.line(xx + width / 2, yy, xx, yy + length);

			Window.out.fontSize(30);
			Window.out.print("health: " + health, x + length / 2, y - 35);
		}
	}

}
