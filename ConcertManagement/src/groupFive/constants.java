package groupFive;

import java.io.IOException;
import java.util.Stack;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

public class constants {
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

	public void setForw(String forw) {
		constants.forw.push(forw);
	}

	public static String getHome() {
		return home;
	}

	public static void setHome(String string) {
		constants.home = string;
	}

	public String getCurrent() {
		return current;
	}

	public static void setCurrent(String current) {
		constants.current = current;
	}

	public String getPrev() {
		return prev.pop();
	}

	public void setPrev(String prev) {
		constants.prev.push(prev);
	}
    
    

}
