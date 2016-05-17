
public abstract class AbstractTree {
	Turtle t;
	private int initLevel;
	private int initLength;
	private float angle;
	private int initThickness;
	private int initColor;

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
