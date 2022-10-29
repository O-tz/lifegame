package lifegame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

public class BoardView extends TransitionablePanel implements BoardListener, MouseListener, MouseMotionListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private BoardModel model;
	private Color cellColor = Color.BLACK;
	
	public BoardView(BoardModel m) {
		this.model = m;
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
	}
	public void updated(BoardModel m) {
		model = m;
		repaint();
	}
	
	public void updated() {
		repaint();
	}
	
	/*paintメソッドの中を見よ*/
	private int r, c, cellWidth;
	private int[] s = new int[2];
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		/*ナシ------→パネルの縦か横の小さいほうの99%を占めるようにする。*/
		//Dimension rv = this.getSize(); //パネルの大きさを取得する。
		//int h = rv.height; //パネルの高さ
		//int w = rv.width;  //パネルの幅
		int h = getHeight();
		int w = getWidth();
		r = model.getRows();//縦に連なるセルの数
		c = model.getCols();//横に連なるセルの数
		cellWidth = Math.min((int) (h/r), (int) (w/c));//セルの一辺の長さ
		s[0] = (w-cellWidth*c)/2;//格子の描写の開始位置
		s[1] = (h-cellWidth*r)/2;
		
		g.setColor(Color.BLACK);
		
		for(int i=0; i<=r; i++) {
			g.drawLine(s[0], s[1]+i*cellWidth, s[0]+c*cellWidth, s[1]+i*cellWidth);
		}
		for(int i=0; i<=c; i++) {
			g.drawLine(s[0]+i*cellWidth, s[1], s[0]+i*cellWidth, s[1]+r*cellWidth);
		}
		
		g.setColor(this.cellColor);
		
		for(int i=0; i<r; i++) {
			for(int j=0; j<c; j++) {
				if(model.isAlive(j, i)) {
					g.fillRect(s[0]+j*cellWidth, s[1]+i*cellWidth, cellWidth, cellWidth);
				}
			}
		}
	}
	
	public void setCellColor(Color c) {
		this.cellColor = c;
	}
	
	private int[] previousLocation = new int[2];
//	private boolean previousEvent;
	
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		int x = (int) ((e.getX()-s[0])/cellWidth);
		int y = (int) ((e.getY()-s[1])/cellWidth);
		if(x!=previousLocation[0] || y!=previousLocation[1]) {
			model.changeCellState(x, y);
		}
		
		previousLocation[0] = (int) ((e.getX()-s[0])/cellWidth);
		previousLocation[1] = (int) ((e.getY()-s[1])/cellWidth);
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		previousLocation[0] = (int) ((e.getX()-s[0])/cellWidth);
		previousLocation[1] = (int) ((e.getY()-s[1])/cellWidth);
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		int x = e.getX();
		int y = e.getY();
		/*外枠上は反応せず、内部では左と上の辺に反応する。*/
		if(x>s[0] && x<s[0]+c*cellWidth) {
			if(y>s[1] && y<s[1]+r*cellWidth) {
				model.changeCellState((int) ((x-s[0])/cellWidth), (int) ((y-s[1])/cellWidth));
			}
		}
//		previousEvent = true;
		previousLocation[0] = (int) ((e.getX()-s[0])/cellWidth);
		previousLocation[1] = (int) ((e.getY()-s[1])/cellWidth);
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
