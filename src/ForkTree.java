
public class ForkTree extends AbstractTree {
	

	public ForkTree(Turtle t, int level, float length, float angle, int thickness, int color) {
		super(t, level, length, angle, thickness, color);
	}

	@Override
	public void drawTree(Turtle t, int level, float length, float angle, int thickness, int color) {
		if(level <= 1){
			t.forward(length);
			t.forward(-length);
			return;
		}
		
		t.setThickness(thickness);
		t.setColor(color);
		
		// trunk
		t.forward(length);
		
		// left branch
		t.turnLeft(angle);
		drawTree(t, level - 1, length/1.3f, angle, (int)(thickness/1.5), (int)(color/.7));
		
		// middle left branch
		t.turnRight(angle * 2 / 3);
		drawTree(t, level - 1, length/1.3f, angle, (int)(thickness/1.5), (int)(color/.7));
		
		// middle right branch
		t.turnRight(angle * 2 / 3);
		drawTree(t, level - 1, length/1.3f, angle, (int)(thickness/1.5), (int)(color/.7));
		
		// right branch
		t.turnRight(angle * 2 / 3);
		drawTree(t, level - 1, length/1.3f, angle, (int)(thickness/1.5), (int)(color/.7));
		
		// reset
		t.turnLeft(angle);
		t.forward(- length);
		
	}
	
}
