package lifegame;

import javax.swing.JButton;

public class ToggleUndoButton implements BoardListener {
	
	private JButton button;
	
	public ToggleUndoButton(JButton b) {
		button = b;
	}
	
	@Override
	public void updated(BoardModel m) {
		// TODO Auto-generated method stub
		if(m.isUndoable()) {
			button.setEnabled(true);
		}else {
			button.setEnabled(false);
		}
	}

}
