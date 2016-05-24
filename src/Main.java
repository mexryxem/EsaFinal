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
	private int currentMode = 0;
	
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
		
		//currentMode = Mode.INTRO;
		
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
		
		System.out.println(currentMode);
		
	}
	
	public void displayTrees(){
		t.goToPoint(HALF_A_THIRD, TREE_START_Y);
		tree1.display();
		
		t.goToPoint(SECOND_THIRD + HALF_A_THIRD, TREE_START_Y);
		tree2.display();
		
		t.goToPoint(LAST_THIRD + HALF_A_THIRD, TREE_START_Y);
		tree3.display();
	}
	

	public void keyPressed(){
		if(currentMode == 0) currentMode ++;
		if(key == CODED){
			if(key == ENTER) end = true;
		}
	}
	
	public void displayFinalTree(){
		t.goToPoint(WIDTH/2, TREE_START_Y);
		currentTree.display();
	}
	
	public void mouseReleased(){
		if(currentMode == 0) currentMode ++;
		else {
			updateCurrentTree();
			updateOptions();
			currentMode ++;
		}
	}
	
	
	public void updateOptions(){
		System.out.println("updating tree options");
		tree1 = getTransformedInstanceTree(currentTree);
		tree2 = getTransformedInstanceTree(currentTree);
		tree3 = getTransformedInstanceTree(currentTree);
		System.out.println("done updating tree options");
	}
	
	
	public AbstractTree getTransformedInstanceTree(AbstractTree tree){
		if(tree instanceof SlingshotTree){
			System.out.println("updating slingshot");
			int level = tree.getLevel() + (int)(Math.random() * 3);
			return (new SlingshotTree(t, level, 100, 30, 7, 10));
			
		} else if(tree instanceof ForkTree){
			System.out.println("updating fork");
			int level = tree.getLevel() + (int)( Math.random() * 3);
			return (new SlingshotTree(t, level, 100, 30, 7, 10));
			
		}
		
		System.out.println("updating split");
		int level = tree.getLevel() + (int)( Math.random() * 3);
		return (new SlingshotTree(t, level, 100, 30, 7, 10));
	}
	
	
	public void updateCurrentTree(){
		System.out.println("updating current tree");
		if(mouseX >= FIRST_THIRD && mouseX < SECOND_THIRD)
			currentTree = getInstanceTree(tree1);
				
		else if(mouseX >= SECOND_THIRD && mouseX < LAST_THIRD)
			currentTree = getInstanceTree(tree2);
				
		// if mouse is in the last third of the screen
		else {currentTree = getInstanceTree(tree3);
		System.out.println("done updating current tree");}
		
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
		//text("press any key to continue", WIDTH/2, LENGTH/2 + 100, -200); //TODO: z parameter is not working
	}
}
