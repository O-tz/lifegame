package lifegame;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class OptionView extends TransitionablePanel{
	//private JPanel optionPanel = new JPanel();
	//private JPanel base;
	private OptionalInformation info;
	private JFrame frame;
	private JSlider speed;
	
	public OptionView(OptionalInformation i, JFrame f) {
		info = i;
		frame =f;
		
		/*メインパネルの追加*/
		JPanel mainPanel = new JPanel(new BorderLayout());
		this.add(mainPanel, BorderLayout.CENTER);
		
		/*speed変更スライダーパネルの追加*/
		JPanel speedPanel = new JPanel();
		mainPanel.add(speedPanel, BorderLayout.NORTH);
		
		JLabel labelSpeed = new JLabel("Time Interval");
		speedPanel.add(labelSpeed);
		
		/*speedスライダーの追加*/
		speed = new JSlider(1, 10, (int)info.getSpeed()*10);
		speed.setMajorTickSpacing(1);
		speed.setPaintTicks(true);
		speed.addChangeListener(new OptionAction("speed", info, this));
		speedPanel.add(speed, BorderLayout.NORTH);
		
		
		
		
		/*背景カラー変更ボタンパネルの追加*/
		JButton buttonRed = new JButton("Red");
		JButton buttonGreen = new JButton("Green");
		JButton buttonBlue = new JButton("Blue");
		JButton buttonGray = new JButton("Gray");
		JButton buttonDefault = new JButton("Default");
		
		JPanel colorPanel = new JPanel();
		mainPanel.add(colorPanel, BorderLayout.CENTER);
		colorPanel.setLayout(new FlowLayout());
		
		JLabel labelColor = new JLabel("Color");
		colorPanel.add(labelColor);
		
		colorPanel.add(buttonGray);
		colorPanel.add(buttonBlue);
		colorPanel.add(buttonGreen);
		colorPanel.add(buttonRed);
		colorPanel.add(buttonDefault);
		
		buttonRed.addActionListener(new OptionAction("red", info, this));
		buttonGreen.addActionListener(new OptionAction("green", info, this));
		buttonBlue.addActionListener(new OptionAction("blue", info, this));
		buttonGray.addActionListener(new OptionAction("gray", info, this));
		buttonDefault.addActionListener(new OptionAction("default", info, this));
		
		/*セルカラー変更ボタンパネルの追加*/
		JButton buttonCellRed = new JButton("Red");
		JButton buttonCellGreen = new JButton("Green");
		JButton buttonCellBlue = new JButton("Blue");
		JButton buttonCellGray = new JButton("Gray");
		JButton buttonCellDefault = new JButton("Default");
		
		JPanel cellColorPanel = new JPanel();
		mainPanel.add(cellColorPanel, BorderLayout.SOUTH);
		cellColorPanel.setLayout(new FlowLayout());
		
		JLabel labelCellColor = new JLabel("Cell Color");
		cellColorPanel.add(labelCellColor);
		
		cellColorPanel.add(buttonCellGray);
		cellColorPanel.add(buttonCellBlue);
		cellColorPanel.add(buttonCellGreen);
		cellColorPanel.add(buttonCellRed);
		cellColorPanel.add(buttonCellDefault);
		
		buttonCellRed.addActionListener(new OptionAction("cellRed", info));
		buttonCellGreen.addActionListener(new OptionAction("cellGreen", info));
		buttonCellBlue.addActionListener(new OptionAction("cellBlue", info));
		buttonCellGray.addActionListener(new OptionAction("cellGray", info));
		buttonCellDefault.addActionListener(new OptionAction("cellDefault", info));
		
		/*ボタンパネルの追加*/
		JPanel buttonPanel = new JPanel();
		this.add(buttonPanel, BorderLayout.SOUTH);
		buttonPanel.setLayout(new FlowLayout());
		
		/*closeボタンの追加*/
		JButton close = new JButton("Close");
		buttonPanel.add(close);
		close.addActionListener(new OptionAction("close", info, frame, this));
		
		
	}

	public int getSpeedValue(){
		return(speed.getValue());
	}
/*	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		frame.setContentPane(this.getTransitionables("base"));
		frame.validate();
	}
*/
/*
	@Override
	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub
		info.setSpeed((double)speed.getValue()/10);
	}
*/
	
	
}
