package lifegame;

import java.util.ArrayList;

public class BoardModel {
	private int cols;
	private int rows;
	private boolean[][] cells;
	private ArrayList<BoardListener> listeners;
	public static final int MAX_LENGTH_OF_HISTORY = 32;//盤面の履歴の最大数
	
	public BoardModel(int c, int r) {
		cols = c;
		rows = r;
		cells = new boolean[rows][cols];
		listeners = new ArrayList<BoardListener>();
	}
	
	public void addListener(BoardListener listener) {
		listeners.add(listener);
	}
	
	/*盤面変化後種々の更新を引き起こす。*/
	private void fireUpdate() {
		for (BoardListener listener: listeners) {
			listener.updated(this);
		}
	}
	
	/*(x,y)のセルが生きているか伝える。*/
	public boolean isAlive(int x, int y) {
		return(cells[y][x]);
	}
	
	/*インスタンス外から列の数を得る為の関数。*/
	public int getCols() {
		return cols;
	}
	
	/*インスタンス外から行数を得るための関数*/
	public int getRows() {
		return rows;
	}
	
	/*(x,y)のセルの状態を反転させる。*/
	public synchronized void changeCellState(int x, int y) {
		if(x>=0 && x<cols) {
			if(y>=0 && y<rows) {
				addHistory();
				cells[y][x] = !cells[y][x];
				fireUpdate();
			}
		}
	}
	
	/*各セルに隣接する生存セルの数を、受け取った二次元配列に書き込む。*/
	private void writeNofAdjacentlives(int[][] adj){
		
		
		for(int i=0; i<rows; i++) {
			for(int j=0; j<cols; j++) {
				if(cells[i][j]==true) {
					if(i!=0) {
						adj[i-1][j]++;
						if(j!=0) {
							adj[i-1][j-1]++;
						}
						if(j!=cols-1) {
							adj[i-1][j+1]++;
						}
					}
					if(i!=rows-1) {
						adj[i+1][j]++;
						if(j!=0) {
							adj[i+1][j-1]++;
						}
						if(j!=cols-1) {
							adj[i+1][j+1]++;
						}
					}
					if(j!=0) {
						adj[i][j-1]++;
					}
					if(j!=cols-1) {
						adj[i][j+1]++;
					}
				}
			}
		}
		
	}
	
	/*盤面を一世代進める。*/
	public synchronized void next() {
		addHistory();
		int[][] adjacent = new int[rows][cols]; //隣接する生存セルの数を格納する変数。
		//↑メソッドのローカル変数なのに初期化されていない?
		this.writeNofAdjacentlives(adjacent);
		for (int i=0; i < rows; i++) {
			for (int j=0; j < cols; j++) {
				int k = adjacent[i][j];
				if(cells[i][j]==true) {
					if(k != 2 && k != 3) {
						this.cells[i][j] = false;
					}
				}else {
					if(k == 3) {
						this.cells[i][j] = true;
					}
				}
			}
		}
		fireUpdate();
	}
	
	private ArrayList<boolean[][]> historyOfBoard = new ArrayList<boolean[][]>();//逶､髱｢縺ｮ螻･豁ｴ縲�
	
	private boolean[][] cloneCells; //	現在のセルの複製を入れる変数。
	
	/*cloneCellsに現在のセルの内容をコピーする*/
	private void makeClone() {      
		cloneCells = new boolean[rows][cols];
		for(int i=0; i<rows; i++) {
			for(int j=0; j<cols; j++) {
				cloneCells[i][j] = cells[i][j];
			}
		}
	}
	
	/*historyOfBoardの最後に現在の盤面を追加する。溢れ履歴の消去も行う。next・changeCellState用*/
	private void addHistory() {
		if(historyOfBoard.size()>=MAX_LENGTH_OF_HISTORY) {
			historyOfBoard.remove(0);
		}
		makeClone();
		historyOfBoard.add(cloneCells);
	}
	
	/*historyOfBoardの最後の要素を削除する。undo用*/
	private void removeHistory() {
		historyOfBoard.remove(historyOfBoard.size()-1);
	}
	
	/*undo可能かどうか判定する。*/
	public boolean isUndoable() {
		if(historyOfBoard.isEmpty()) {
			return(false);
		}else {
			return(true);
		}
	}
	
	/*historyOfBoardの最後の要素を用いて盤面を更新する。undo用*/
	private void updateCells(boolean[][] tmp) {
		for(int i=0; i<rows; i++) {
			for(int j=0; j<cols; j++) {
				cells[i][j] = tmp[i][j];
			}
		}
	}
	
	/*盤面を一世代戻す。*/
	public synchronized void undo() {
		updateCells(historyOfBoard.get(historyOfBoard.size()-1));
		removeHistory();
		fireUpdate();
	}
	
	public void printForDebug() {
		for (int i=0; i < rows; i++) {
			for (int j=0; j < cols; j++) {
				if(cells[i][j]==false) {
					System.out.print(".");
				} else {
					System.out.print("*");
				}
			}
			System.out.println();
		}
	}
	
}
