package lifegame;

public class AutoNext extends Thread {
	
	BoardModel model;
	AutoButtonAction button;
	
	public AutoNext(BoardModel m, AutoButtonAction b) {
		model = m;
		button = b;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(button.isSelected()) {
			model.next();
			try{
			    AutoNext.sleep((long)(button.getSpeed()*1000));
			}catch(InterruptedException e){
			    e.printStackTrace();
			}
		}
		

	}
	
}
