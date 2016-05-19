
public class SlingshotTree extends AbstractTree{

	public SlingshotTree(Turtle t, int level, float length, float angle, int thickness, int color) {
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
		
		// right branch
		t.turnRight(2 * angle);
		drawTree(t, level - 1, length/1.3f, angle, (int)(thickness/1.5), (int)(color/.7));
		
		t.turnLeft(angle);
		t.forward(-length);
	}
}
