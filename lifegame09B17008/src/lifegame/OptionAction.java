package lifegame;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class OptionAction implements ActionListener, ChangeListener {
	
	private String typeOfComponent;
	private OptionalInformation info;
	private JFrame frame;
	private OptionView option;
	private TransitionablePanel panel;
	
	public OptionAction(String s) {
		typeOfComponent = s;
	}
	
	public OptionAction(String s, OptionalInformation i, OptionView ov) {
		typeOfComponent = s;
		info = i;
		option = ov;
	}
	
	public OptionAction(String s, OptionalInformation i) {
		typeOfComponent = s;
		info = i;
	}
	
	public OptionAction(String s, OptionalInformation i, JFrame f, TransitionablePanel p) {
		typeOfComponent = s;
		info = i;
		frame = f;
		panel = p;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		switch(typeOfComponent) {
		
		// 背景の色を変える場合
		case "red":
			info.setColor(Color.RED);
			option.getTransitionables("base").getTransitionables("board").setOpaque(true);
			option.getTransitionables("base").getTransitionables("board").setBackground(Color.red);
			break;
		case "green":
			info.setColor(Color.GREEN);
			option.getTransitionables("base").getTransitionables("board").setOpaque(true);
			option.getTransitionables("base").getTransitionables("board").setBackground(Color.green);
			break;
		case "blue":
			info.setColor(Color.BLUE);
			option.getTransitionables("base").getTransitionables("board").setOpaque(true);
			option.getTransitionables("base").getTransitionables("board").setBackground(Color.blue);
			break;
		case "gray":
			info.setColor(Color.GRAY);
			option.getTransitionables("base").getTransitionables("board").setOpaque(true);
			option.getTransitionables("base").getTransitionables("board").setBackground(Color.gray);
			break;
		case "default":
			option.getTransitionables("base").getTransitionables("board").setOpaque(false);
			break;
			
		// セルの色を変える場合
		case "cellRed":
			info.setCellcolor(Color.RED);
			break;
		case "cellGreen":
			info.setCellcolor(Color.green);
			break;
		case "cellBlue":
			info.setCellcolor(Color.blue);
			break;
		case "cellGray":
			info.setCellcolor(Color.GRAY);
			break;
		case "cellDefault":
			info.setCellcolor(Color.BLACK);
			break;
		
		//オプション画面を閉じる場合
		case "close":
			frame.setContentPane(panel.getTransitionables("base"));
			frame.validate();
			break;
		default :
			break;
		}
			
	}
	
	@Override
	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub
		info.setSpeed((double)option.getSpeedValue()/10);
	}
	

}
