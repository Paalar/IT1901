package application;

public class UpOrDownCounter {
	private int startVerdi;
	private int sluttVerdi;
	private int valueAtPoint;
	public UpOrDownCounter(int start, int end){
		startVerdi = start;
		sluttVerdi = end;
		valueAtPoint = start;
		if(startVerdi == sluttVerdi){
			throw new IllegalArgumentException ("Counter has stopped");
		}
	}
	
	public UpOrDownCounter() {
		valueAtPoint = 0;
	}
	
	int getCounter(){
		return valueAtPoint;
	}
	
	boolean count1() {
		valueAtPoint++;
		return true;
	}
	
	boolean count(){
		if (startVerdi > sluttVerdi & !(valueAtPoint == sluttVerdi)){
			valueAtPoint = valueAtPoint - 1;
			if(valueAtPoint == sluttVerdi){
				return false;
			}
			else{
				return true;
			}
		}
		if((startVerdi < sluttVerdi) & !(valueAtPoint == sluttVerdi)){
			valueAtPoint = valueAtPoint + 1;
			if(valueAtPoint == sluttVerdi){
				return false;
			}
			else{
				return true;
			}
		}
		if(valueAtPoint == sluttVerdi){
			return false;
		}
		return false;
	}
	
	public static void main(String[] args){
		UpOrDownCounter A = new UpOrDownCounter(10,5);
		System.out.println(A.getCounter());
	}

}