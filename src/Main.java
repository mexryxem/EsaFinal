import processing.core.PApplet;
import processing.core.PFont;

public class Main extends PApplet{
	//applet dimensions
	private final int LENGTH = 600;
	private final int WIDTH = 1300;
	
	// the horizontal starting point for each third of the screen; 
	private final int FIRST_THIRD = 0;
	private final int SECOND_THIRD = 433;
	private final int LAST_THIRD = 867;
	
	
	private final int HALF_A_THIRD = (int)(WIDTH/6);
	private final int TREE_START_Y = LENGTH - LENGTH/6;
	
	private boolean end = false;
	private int currentMode;
	
	// the tree that the game is working towards
	// it is updated every time one of the tree options
	// is selected
	private AbstractTree currentTree;
	
	//tree options that corresponds to a third of the screen
	private AbstractTree tree1;
	private AbstractTree tree2;
	private AbstractTree tree3;
	
	private Turtle t;
	
	// word stuff
	PFont f;
	
	public void setup(){
		size(WIDTH, LENGTH);
		background(255);
		
		currentMode = 0;
		
		t = new Turtle(this);
		t.setDirection(90);
		
		tree1 = new SlingshotTree(t, 3, 100, 30, 7, 10);
		tree2 = new ForkTree(t, 3, 100, 60, 7, 10);
		tree3 = new SplitTree(t, 3, 280, 30, 7, 10);
		
		//currentTree will be updated immediately after Phase1 - this is just to initialize it
		currentTree = new SlingshotTree(t, 1, 100, 30, 7, 10);
		
		// word stuff
		f = createFont("Georgia", 200);
		textFont(f);
		textAlign(CENTER, CENTER);
		
	}
	
	public void draw(){
		background(255);
		if(currentMode == 0) displayIntro();
		else if(end) displayFinalTree();
		else displayTrees();		
	}
	
	public void displayTrees(){
		t.goToPoint(HALF_A_THIRD, TREE_START_Y);
		tree1.display();
		
		t.goToPoint(SECOND_THIRD + HALF_A_THIRD, TREE_START_Y);
		tree2.display();
		
		t.goToPoint(LAST_THIRD + HALF_A_THIRD, TREE_START_Y);
		tree3.display();
	}
	

	//handles changing mode from intro to next
	public void keyPressed(){
		if(currentMode == 0) currentMode ++;
		else if(currentMode != 0){
			end = true;
		}
	}
	
	//handles the changing of modes
	public void mouseReleased(){
		if(currentMode == 0) currentMode ++;
		else {
			updateCurrentTree();
			updateOptions();
			currentMode ++;
		}
	}
	
	public void updateOptions(){
		tree1 = getTransformedInstanceTree(currentTree);
		tree2 = getTransformedInstanceTree(currentTree);
		tree3 = getTransformedInstanceTree(currentTree);
	}
	
	
	public AbstractTree getTransformedInstanceTree(AbstractTree tree){
		if(tree instanceof SlingshotTree){
			int level = (int)(3 + Math.random() * 13);
			float angle = (float)( -60 + Math.random() * 60);
			float length = (float)(90 + Math.random() * 100);
			return (new SlingshotTree(t, level, length, angle, 7, 10));
			
		} else if(tree instanceof ForkTree){
			int level = (int)(3 + Math.random() * 7);
			float angle = (float)( -60 + Math.random() * 60);
			float length = (float)(90 + Math.random() * 100);
			return (new ForkTree(t, level, length, angle, 7, 10));
			
		}
		
		int level = (int)(3 + Math.random() * 5);
		float angle = (float)( -60 + Math.random() * 60);
		float length = (float)(90 + Math.random() * 100);
		return (new SplitTree(t, level, length, angle, 7, 10));
	}
	
	
	public void updateCurrentTree(){
		if(mouseX >= FIRST_THIRD && mouseX < SECOND_THIRD)
			currentTree = getInstanceTree(tree1);
				
		else if(mouseX >= SECOND_THIRD && mouseX < LAST_THIRD)
			currentTree = getInstanceTree(tree2);
				
		// if mouse is in the last third of the screen
		else currentTree = getInstanceTree(tree3);		
	}
	
	public AbstractTree getInstanceTree(AbstractTree tree){
		if(tree instanceof SlingshotTree){
			return (new SlingshotTree(t, tree.getLevel(), tree.getLength(),
					tree.getAngle(), tree.getThickness(), tree.getThickness()));
			
		} else if(tree instanceof ForkTree){
			return (new ForkTree(t, tree.getLevel(), tree.getLength(),
					tree.getAngle(), tree.getThickness(), tree.getThickness()));
		}
		
		return (new SplitTree(t, tree.getLevel(), tree.getLength(),
				tree.getAngle(), tree.getThickness(), tree.getThickness()));
		
	}
	
	public void displayIntro(){
		fill(47,79,79);
		text("welcome", WIDTH/2, LENGTH/2);
	}
	
	public void displayFinalTree(){
		background(255);
		t.goToPoint(WIDTH/2, TREE_START_Y);
		currentTree.display();
	}
}
