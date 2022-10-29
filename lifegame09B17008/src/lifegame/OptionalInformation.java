package lifegame;

import java.awt.Color;

public class OptionalInformation {
	
	private double autoSpeed = 1; //自動世代交代時の時間間隔 1 = 1000ms
	private Color color = Color.GRAY; //背景の色
	private Color cellcolor = Color.BLACK; //セルの色
	private BoardView board; //盤面の描画にかかわるインスタンス
	
	public OptionalInformation(BoardView v) {
		board = v;
	}
	
	public double getSpeed() {
		return(autoSpeed);
	}
	
	public void setSpeed(double s) {
		this.autoSpeed = s;
	}

	public Color getCellcolor() {
		return cellcolor;
	}

	public void setCellcolor(Color cellcolor) {
		this.cellcolor = cellcolor;
		board.setCellColor(this.cellcolor);
		board.updated(); //盤面の再描画を行う
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	
	
}
