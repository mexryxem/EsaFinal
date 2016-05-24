
public class SplitTree extends AbstractTree{
	

	
	public SplitTree(Turtle t, int level, float length, float angle, int thickness, int color) {
		super(t, level, length, angle, thickness, color);
	}

	@Override
	public void drawTree(Turtle t, int level, float length, float angle, int thickness, int color){
		if(level <= 1){
			t.forward(length);
			t.forward(-length);
			return;
		}
		
		t.setThickness(thickness);
		t.setColor(color);
		
		// bottom of trunk
		t.forward(length/4);
		
		// bottom left branch
		t.turnLeft(angle);
		drawTree(t, level - 1, length/2f, angle, (int)(thickness/1.5), (int)(color/.7));
		
		// mid bottom of trunk
		t.turnRight(angle);
		t.forward(length/4);
		
		// bottom right branch
		t.turnRight(angle);
		drawTree(t, level - 1, length/2f, angle, (int)(thickness/1.5), (int)(color/.7));
		
		// mid top of trunk
		t.turnLeft(angle);
		t.forward(length/4);
		
		// top left branch
		t.turnLeft(angle);
		drawTree(t, level - 1, length/2f, angle, (int)(thickness/1.5), (int)(color/.7));
		
		// top of trunk
		t.turnRight(angle);
		t.forward(length/4);
		
		// top right branch
		t.turnRight(angle);
		drawTree(t, level - 1, length/2f, angle, (int)(thickness/1.5), (int)(color/.7));
		
		//reset
		t.turnLeft(angle);
		t.forward(-length);
	
	}
}
