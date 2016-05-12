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
	
	//tree saved with all the attributes
	public void setup(){
		size(LENGTH, WIDTH);
		currentMode = Mode.INTRO;
	}
	
	public void draw(){
		
	}
	
	public void displayIntro(){
		
	}
	
	public void mouseReleased(){
		
	}
}
