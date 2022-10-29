package lifegame;

import java.util.*;

import javax.swing.JPanel;

public class TransitionablePanel extends JPanel {
	public TransitionablePanel() {
		
	}
	
	private HashMap<String, TransitionablePanel> transitionables = new HashMap<>();
	
	public void addTransitionables(String s, TransitionablePanel p) {
		this.transitionables.put(s, p);
	}
	
	public TransitionablePanel getTransitionables(String s){
		return this.transitionables.get(s);
		
	}
}
