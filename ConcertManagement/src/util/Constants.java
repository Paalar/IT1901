package util;

import java.util.Stack;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class Constants {
    private static final AnchorPane AnchorPane = null;

	@FXML 
    static private String home;
    
    @FXML 
    static private String current;
    
    @FXML 
    static Stack<String> prev = new Stack<String>();
    
    @FXML
    static private Stack<String> forw = new Stack<String>();

    public String getForw() { //Returnerer det siste som ble lagt til på forward stacken
		return forw.pop();
	}

	public void emptyStack(Stack<String> stack){

	}

	public void setForw(String forw) { //Legger til side til forward stacken når vi går tilbake
		Constants.forw.push(forw);
	}

	public static String getHome() { //Returnerer home siden når vi trykker på start
		return home;
	}

	public static void setHome(String string) { //Setter home siden ved start
		Constants.home = string;
	}

	public String getCurrent() { //Returnerer den viewen som blir vist
		return current;
	}

	public static void setCurrent(String current) { //Oppdater hva som er viewen du er på
		Constants.current = current;
	}

	public String getPrev() { //Returnerer det siste som ble lagt på tilbake stacken
		return prev.pop();
	}

	public void setPrev(String prev) { //Legger til siste view i tilbake stacken
		Constants.prev.push(prev);
	}
    
    

}
