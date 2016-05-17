import processing.core.PApplet;

public class Main extends PApplet{
	// the horizontal starting point for each third of the screen; 
	private final int FIRST_THIRD = 0;
	private final int SECOND_THIRD = 433;
	private final int LAST_THIRD = 867;
	
	//applet dimensions
	private final int LENGTH = 600;
	private final int WIDTH = 1300;
	
	
	private Mode currentMode;
	// the different phases of the simulation
	// each mode represents a new screen displaying something different
	private enum Mode{
		INTRO, TREE_TYPE, PHASE1, PHASE2, PHASE3, PHASE4, END
	}
	
	//tree that corresponds to a third of the screen
	private AbstractTree tree1;
	private AbstractTree tree2;
	private AbstractTree tree3;
	
	private AbstractTree currentTree;
	
	private Turtle t;
	
	//tree saved with all the attributes
	public void setup(){
		size(WIDTH, LENGTH);
		background(255);
		
		currentMode = Mode.INTRO;
		
		t = new Turtle(this);
		
		tree1 = new SlingshotTree(t, 1, 100, 30, 7, 10);
		tree2 = new ForkTree(t, 1, 100, 30, 7, 10);
		tree3 = new SplitTree(t, 1, 100, 30, 7, 10);
		
		//currentTree will change immediately after Phase1 - this is just to initialize it
		currentTree = new SlingshotTree(t, 1, 100, 30, 7, 10);
	}
	
	public void draw(){
		if(currentMode == Mode.INTRO) displayIntro();
		else if(currentMode == Mode.END) displayFinalTree();
		else displayTrees();
		
	}
	
	public void displayTrees(){
		// move to middle of first third
		// display tree1
		
		// move to middle of second third
		// display tree2
		
		// move to middle of last third
		// display tree3
	}
	
	public void displayFinalTree(){
		currentTree.display();
	}
	
	// this handles changing of modes and updating the currentTree
	public void mouseReleased(){
		if(currentMode == Mode.INTRO){
			currentMode = Mode.TREE_TYPE;
		
		} else if(currentMode == Mode.TREE_TYPE){
			updateCurrentTree();
			updateOptions();
			currentMode = Mode.PHASE1;
					
		} else if(currentMode == Mode.PHASE1){
			updateCurrentTree();
			updateOptions();
			currentMode = Mode.PHASE2;
			
		} else if(currentMode == Mode.PHASE2){
			updateCurrentTree();
			updateOptions();
			currentMode = Mode.PHASE3;
			
		} else if(currentMode == Mode.PHASE3){
			updateCurrentTree();
			updateOptions();
			currentMode = Mode.PHASE4;
			
		} else if(currentMode == Mode.PHASE4){
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
		
	}
}
