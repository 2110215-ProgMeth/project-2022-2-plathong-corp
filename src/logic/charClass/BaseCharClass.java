package logic.charClass;

public abstract class BaseCharClass {
	private int hp,atk;
	private String name;
	
	public BaseCharClass(String name , int hp ,int atk) {
		setHp(hp);
		setAtk(atk);
		setName(name);
		
	}
	
	public abstract void normalAttack();
	public abstract void performUltmate();
	public abstract void performBuff();

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
}
