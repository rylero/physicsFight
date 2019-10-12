package physicsFight;

public class Character {
	
	public float jumpForce;
	public float addSpeed;
	public String name;
	public int health;
	int r;
	int g;
	int b;
	
	public Character(String name, float jumpForce, float addSpeed, int health, int r, int g, int b) {
		this.jumpForce = jumpForce;
		this.addSpeed = addSpeed;
		this.name = name;
		this.health = health;
		this.r = r;
		this.g = g;
		this.b = b;
	}

}
