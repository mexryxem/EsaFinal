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
	
	// the different phases of the simulation
	// each mode represents a new screen displaying something different
	private Mode currentMode;
	private enum Mode{
		INTRO, TREE_TYPE, PHASE1, PHASE2, PHASE3, PHASE4, END
	}
	
	
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
		
		currentMode = Mode.INTRO;
		
		t = new Turtle(this);
		t.setDirection(90);
		
		tree1 = new SlingshotTree(t, 2, 100, 30, 7, 10);
		tree2 = new ForkTree(t, 2, 100, 30, 7, 10);
		tree3 = new SplitTree(t, 2, 100, 30, 7, 10);
		
		//currentTree will be updated immediately after Phase1 - this is just to initialize it
		currentTree = new SlingshotTree(t, 1, 100, 30, 7, 10);
		
		// word stuff
		f = createFont("Georgia", 200);
		textFont(f);
		textAlign(CENTER, CENTER);
		
	}
	
	public void draw(){
		background(255);
		if(currentMode == Mode.INTRO) displayIntro();
		else if(currentMode == Mode.END) displayFinalTree();
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
	
	public void displayFinalTree(){
		currentTree.display();
	}
	
	public void keyPressed(){
		if(currentMode == Mode.INTRO)
			currentMode = Mode.TREE_TYPE;
	}
	
	// this handles changing of modes and updating the currentTree
	public void mouseReleased(){
		if(currentMode == Mode.TREE_TYPE){
			System.out.println("p1");
			updateCurrentTree();
			updateOptions();
			currentMode = Mode.PHASE1;
					
		} else if(currentMode == Mode.PHASE1){
			System.out.println("p2");
			updateCurrentTree();
			updateOptions();
			currentMode = Mode.PHASE2;
			
		} else if(currentMode == Mode.PHASE2){
			System.out.println("p3");
			updateCurrentTree();
			updateOptions();
			currentMode = Mode.PHASE3;
			
		} else if(currentMode == Mode.PHASE3){
			System.out.println("p4");
			updateCurrentTree();
			updateOptions();
			currentMode = Mode.PHASE4;
			
		} else if(currentMode == Mode.PHASE4){
			System.out.println("end");
			updateCurrentTree();
			updateOptions();
			currentMode = Mode.END;
		}
			
	}
	
	public void updateOptions(){
		tree1 = getTransformedInstanceTree(tree1);
		tree2 = getTransformedInstanceTree(tree2);
		tree3 = getTransformedInstanceTree(tree3);
	}
	
	public AbstractTree getTransformedInstanceTree(AbstractTree tree){
		if(tree instanceof SlingshotTree){
			return (new SlingshotTree(t,
					tree1.getLevel() * (int)(1 + Math.random() * 13),
					tree1.getLength() * (int)(90 + Math.random() * 150),
					tree1.getAngle() * (float)(25 + Math.random() * 35),
					tree1.getThickness(),
					tree1.getColor()));
			
		} else if(tree instanceof ForkTree){
			return (new ForkTree(t,
					tree1.getLevel() * (int)(1 + Math.random() * 13),
					tree1.getLength() * (int)(90 + Math.random() * 150),
					tree1.getAngle() * (float)(25 + Math.random() * 35),
					tree1.getThickness(),
					tree1.getColor()));
		}
		
		return (new SplitTree(t,
				tree1.getLevel() * (int)(1 + Math.random() * 13),
				tree1.getLength() * (int)(90 + Math.random() * 150),
				tree1.getAngle() * (float)(25 + Math.random() * 35),
				tree1.getThickness(),
				tree1.getColor()));
	}
	
	public void updateCurrentTree(){
		if(mouseX >= FIRST_THIRD && mouseX < SECOND_THIRD)
			currentTree = getInstanceTree(tree1);
				
		else if(mouseX >= SECOND_THIRD && mouseX < LAST_THIRD)
			currentTree = getInstanceTree(tree2);
				
		// if mouse is in the last third of the screen
		currentTree = getInstanceTree(tree3);
		
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
