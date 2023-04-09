package logic.entity;

public abstract class BaseEntity {
	private int hp,atk;
	private double speed;
	private String name;
	
	public BaseEntity(String name , int hp ,int atk) {
		setHp(hp);
		setAtk(atk);
		setName(name);
		setSpeed(speed);
		
	}
	
	public abstract void normalAttack(Player p);

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getAtk() {
		return atk;
	}

	public void setAtk(int atk) {
		this.atk = atk;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void setSpeed(double speed) {
		this.speed = speed;
	}
	
	public double getSpeed() {
		return speed;
	}
}
