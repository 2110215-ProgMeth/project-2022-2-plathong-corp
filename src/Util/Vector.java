package Util;

public class Vector <T>{
	private T x,y;
	
	public Vector(T x , T y) {
		this.x = x;
		this.y = y;
	}

	public T getX() {
		return x;
	}

	public T getY() {
		return y;
	}

	public void setX(T x) {
		this.x = x;
	}

	public void setY(T y) {
		this.y = y;
	}
	
	
}
