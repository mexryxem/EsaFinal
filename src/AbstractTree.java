
public abstract class AbstractTree {
	Turtle t;
	int initLevel;
	int initLength;
	float angle;
	int initThickness;
	int initColor;

	public AbstractTree(Turtle t, int level, int length, float angle, int thickness, int color){
		this.t = t;
		initLevel = level;
		initLength = length;
		this.angle = angle;
		initThickness = thickness;
		initColor = color;
		
	}
	abstract void drawTree(Turtle t, int level, int length, float angle, int thickness, int color);
	
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
	
	public float getAngle(){
		return angle;
	}
	
}
