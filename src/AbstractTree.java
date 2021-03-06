
public abstract class AbstractTree {
	Turtle t;
	private int initLevel;
	private float initLength;
	private float angle;
	private int initThickness;
	private int initColor;

	public AbstractTree(Turtle t, int level, float length, float angle, int thickness, int color){
		this.t = t;
		initLevel = level;
		initLength = length;
		this.angle = angle;
		initThickness = thickness;
		initColor = color;
		
	}
	
	abstract void drawTree(Turtle t, int level, float length, float angle, int thickness, int color);
	
	public void display(){
		drawTree(t, initLevel, initLength, angle, initThickness, initColor);
	}
	public int getLevel(){
		return initLevel;
	}
	
	public float getLength(){
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
