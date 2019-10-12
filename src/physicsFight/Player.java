package physicsFight;

import java.applet.AudioClip;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import apcs.Window;

public class Player {

	int x;
	int y;
	int dx;
	int dy;
	int ww;

	int health;
	int opHealth = 0;

	int cd;

	float xForce = 0;
	int maxForce = 25;

	public int sideLength = worldFinals.sideLength;
	
	int grav = 1;

	int gravityState = 1;

	float jumpForce;
	float gravity = worldFinals.gravity;

	boolean isNotDead = true;
	
	float hue = 0;
	public int dir = 1;

	public Player(int spawnIndex, int characterId) {
		if (spawnIndex < 1) {
			spawnIndex = 1;
		}
		cd = characterId;
		jumpForce = worldFinals.characters[characterId].jumpForce;
		health = worldFinals.characters[characterId].health;
		spawnIndex--;
		y = worldFinals.spawns[spawnIndex * 2 + 1];
		x = worldFinals.spawns[spawnIndex * 2];
	}

	public void update() {
		if (health <= 0) {
			isNotDead = false;
		}
		if (isNotDead) {
			x += dx;
			y += dy;
			if (y + sideLength < Window.height()) {
				y += gravity;
				if (gravityState == 1) {
					y += gravity;
					dy += gravity;
				} else {
					y -= gravity;
					dy -= gravity;
				}
			}

			if (Window.key.pressed('w')) {
				if (gravityState == 1) {
					dy -= jumpForce;
				} else {
					dy += jumpForce;
				}
			}

			if (Window.key.pressed('a')) {
				xForce = worldFinals.characters[cd].addSpeed;
				dir = 1;
			}
			if (Window.key.pressed('d')) {
				xForce = -worldFinals.characters[cd].addSpeed;
				dir = -1;
			}
			if (Window.key.released('a')) {
				xForce = -xForce;
			}
			if (Window.key.released('d')) {
				xForce = -xForce;
			}

			if (y + sideLength >= Window.height()) {
				y = Window.height() - sideLength / 2;
				gravityState = 1;
				grav = 1;
			}
			if (y <= 0) {
				y = sideLength / 2;
				gravityState = 0;
				grav = 0;
			}
			if (!hitEdge()) {
				x += dx;
				dx += xForce;
			}

			if (xForce >= maxForce) {
				xForce = 0;
			} else if (xForce >= -maxForce) {
				xForce = 0;
			}
		}

	}

	boolean hitEdge() {
		if (x <= 0 || x + sideLength >= Window.width())
			return true;
		else
			return false;
	}

	public void showStats(Boss b) {
		if (isNotDead) {
			Window.out.color("white");
			Window.out.print(worldFinals.characters[cd].name+"'s Stats: ", 50, 50);
			if (gravityState == 1)
				Window.out.print("Gravity", 50, 80);
			else
				Window.out.print("AntiGravity", 50, 80);

			Window.out.print("x: " + x, 50, 110);
			Window.out.print("y: " + y, 50, 140);
			Window.out.print("dx: " + dx, 50, 170);
			Window.out.print("xForce: " + xForce, 50, 200);
			Window.out.color(health, 0, 0);
			Window.out.rectangle(50+70, 230-10, 200, 30);
			
			Window.out.color(opHealth,opHealth,opHealth);
			Window.out.print("Health: " + health, 50, 230);
		}
	}

	// if (y <= 1) {
	// gravity = - gravity;
	// }

	public void show(Sword s, Boss b) {
		if (isNotDead) {
			Window.out.color(worldFinals.characters[cd].r,worldFinals.characters[cd].g,worldFinals.characters[cd].b);
			Window.out.rectangle(x, y, 50, 50);
		} else if (!isNotDead) {
			Window.out.color("gold");
			Window.out.fontSize(50*2);
			Window.out.print("Rip: "+worldFinals.characters[cd].name, 350, Window.height()/2+50+200);
			Window.out.color("red");
			Window.out.fontSize(225);
			Window.out.print("Game Over", 50, Window.height()/2+50);
			s.doNotShow();
			
			if (Window.mouse.clicked()) {
				Window.out.fontSize(30);
				Main.run();
			}
//			URL url = null;
//			try {
//				url = new URL("");
//			} catch (MalformedURLException e) {
//				e.printStackTrace();
//			}
//			AudioClip newAudioClip = Window.newAudioClip(url);
//			newAudioClip.play();
		} else if (b.health <= 0) {
			Window.out.color("gold");
			Window.out.fontSize(50*2);
			Window.out.print("Rip: Boss Baby", 350, Window.height()/2+50+200);
			Window.out.color("red");
			Window.out.fontSize(225);
			Window.out.print("You Win!", 50, Window.height()/2+50);
			s.doNotShow();
			
			if (Window.mouse.clicked()) {
				Window.out.fontSize(30);
				Main.run();
			}
		}
	}

}
