package lifegame;


/*
 *現在最低基準及びAutoボタンの実装が終了済。
 *Optionボタンの実装が待たれる。 
 * 
 * 
 */

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.SwingUtilities;

public class Main implements Runnable {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Main());
	}
	
	public void run() {
		/*盤面の情報のインスタンス作成*/
		BoardModel model = new BoardModel(50, 50);
		
		/*フレーム作成*/
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//ウィンドウが閉じられたときに終了させる。
		frame.setTitle("lifegame");
		
		/*盤面とボタンを入れる基本パネル作成。これをcontentPaneとする*/
		TransitionablePanel base = new TransitionablePanel();
		frame.setContentPane(base);
		
		/*contentPaneとフレームの推奨サイズ最低サイズを定める*/
		base.setPreferredSize(new Dimension(400, 300));
		frame.setMinimumSize(new Dimension(300, 200));
		
		/*盤面のパネルを基本パネルに追加*/
		base.setLayout(new BorderLayout());
		BoardView boardPanel = new BoardView(model);
		base.add(boardPanel, BorderLayout.CENTER);
		
		/*盤面のパネルを基本パネルからの遷移先として指定可能に*/
		base.addTransitionables("board", boardPanel);
		
		/*BoardModelの変更に応じて描画するためにaddListener*/
		model.addListener(boardPanel);
		
		/*オプション情報を格納するインスタンス作成*/
		OptionalInformation optionalinfo = new OptionalInformation(boardPanel);
		
		/*オプションパネル作成*/
		OptionView option = new OptionView(optionalinfo, frame);
		
		/*基本パネル→オプションパネル、オプションパネル→基本パネルの遷移を可能に*/
		option.addTransitionables("base", base);
		base.addTransitionables("option", option);
		
		
		/*ボタン類のパネルを基本パネルに追加*/
		TransitionablePanel buttonPanel = new TransitionablePanel();
		base.add(buttonPanel, BorderLayout.SOUTH);
		buttonPanel.setLayout(new FlowLayout());
		
		/*ボタン類のパネルを基本パネルからの遷移先として指定可能に*/
		base.addTransitionables("button", buttonPanel);
		
		/*ボタンを作る*/
		JButton buttonNewGame = new JButton("NewGame");
		JButton buttonNext = new JButton("Next");
		JButton buttonUndo = new JButton("Undo");
		buttonUndo.setEnabled(false);
		JToggleButton buttonAuto = new JToggleButton("Auto");
		JButton buttonOption = new JButton("Option");
		
		/*ボタン使用時の挙動を決めるActionListenerを各ボタンに追加*/
		buttonNewGame.addActionListener(new NewGameButtonAction());
		buttonNext.addActionListener(new NextButtonAction(model));
		buttonUndo.addActionListener(new UndoButtonAction(model));
		buttonOption.addActionListener(new OptionButtonAction(option, frame));
		AutoButtonAction autoButtonAction = new AutoButtonAction(model, buttonAuto, optionalinfo);
		buttonAuto.addActionListener(autoButtonAction);
		frame.addWindowListener(autoButtonAction); //Windowが閉じられたときにAuto実行を止めるため
		
		/*ボタンパネルにボタンを追加*/
		buttonPanel.add(buttonNewGame);
		buttonPanel.add(buttonNext);
		buttonPanel.add(buttonUndo);
		buttonPanel.add(buttonAuto);
		buttonPanel.add(buttonOption);
		
		/*Undoボタンの使用可否の自動切換のため、BoardModel側からの情報を得られるようにaddListener*/
		model.addListener(new ToggleUndoButton(buttonUndo));
		
		
		frame.pack();
		frame.setVisible(true);
	}
	
}
