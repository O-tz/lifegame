package lifegame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Base64;
import java.awt.FlowLayout;
import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class OptionButtonAction implements ActionListener {
	
	private OptionView view;
	private JFrame frame;
	
	public OptionButtonAction(OptionView v, JFrame f) {
		view = v;
		frame = f;
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		/*
		frame.remove(view.getTransitionables("board"));
		frame.remove(view.getTransitionables("button"));
		frame.add(view);
		*/
		frame.setContentPane(view);
		frame.validate();
	}

}
