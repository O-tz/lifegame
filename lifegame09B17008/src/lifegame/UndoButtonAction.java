package lifegame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UndoButtonAction implements ActionListener {
	BoardModel model;
	public UndoButtonAction(BoardModel m) {
		model = m;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		model.undo();
	}

}
