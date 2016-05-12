
public abstract class AbstractTree {
	Turtle t;
	int initLevel;
	int initLength;
	int angle;
	int initThickness;
	int initColor;

	abstract void drawTree(Turtle t, int level, float length, float angle, int thickness, int color);
	
	public int getLevel(){
		return initLevel;
	}
	
	public int getLength(){
		return initLength;
	}
	
	public int getThickness(){
		return initThickness;
	}
	
	public int getColor(){
		return initColor;
	}
	
}
