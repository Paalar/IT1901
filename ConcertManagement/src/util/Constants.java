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

    public String getForw() {
		return forw.pop();
	}

	public void emptyStack(Stack<String> stack){

	}

	public void setForw(String forw) {
		Constants.forw.push(forw);
	}

	public static String getHome() {
		return home;
	}

	public static void setHome(String string) {
		Constants.home = string;
	}

	public String getCurrent() {
		return current;
	}

	public static void setCurrent(String current) {
		Constants.current = current;
	}

	public String getPrev() {
		return prev.pop();
	}

	public void setPrev(String prev) {
		Constants.prev.push(prev);
	}
    
    

}
