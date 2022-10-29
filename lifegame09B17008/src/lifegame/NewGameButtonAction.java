package lifegame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingUtilities;

public class NewGameButtonAction implements ActionListener {

	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Thread thread = new Thread(new Main());
		thread.start();
	}

}
