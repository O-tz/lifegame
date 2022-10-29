package lifegame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Date;
import java.util.Timer;

import javax.swing.JToggleButton;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class AutoButtonAction implements ActionListener, WindowListener {
	
	private OptionalInformation info;
	private BoardModel model;
	private JToggleButton button;
	
	public boolean isEnabled() {
		return(button.isEnabled());
	}
	
	public double getSpeed() {
		return(info.getSpeed());
	}
	
	public AutoButtonAction(BoardModel m, JToggleButton b, OptionalInformation i) {
		model = m;
		button = b;
		info = i;
	}
	
	public boolean isSelected() {
		return(button.isSelected());
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(button.isSelected()) {
			AutoNext autoNext = new AutoNext(model, this);
			autoNext.start();
		}
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		button.setSelected(false);
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	

}
