package logic.entity;

public abstract class  Minion extends BaseEntity{

	public Minion(String name, int hp, int atk) {
		super(name, hp, atk);
		// TODO Auto-generated constructor stub
	}
	
	public abstract void speedUp(double multiplier);
	
}
