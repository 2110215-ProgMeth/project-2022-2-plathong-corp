package logic.entity;

public class Werewolf extends Minion{

	public Werewolf() {
		super("Werewolf", 100 , 10);
		speedUp(1.2);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void speedUp(double multiplier) {
		// TODO Auto-generated method stub
		setSpeed(this.getSpeed()*multiplier);
	}

	@Override
	public void normalAttack(Player p) {
		// TODO Auto-generated method stub
		
	}


}
