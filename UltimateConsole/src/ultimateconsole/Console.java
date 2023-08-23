package ultimateconsole;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultEditorKit;

import java.util.ArrayList;

public class Console extends AbstractConsole 
{
	
    private KeyboardInput KEYBOARD_INPUT_HANDLER;
    private InputStreamHandler INPUT_STREAM_HANDLER;

    private Window activeWindow = null;
    
    private String trueForm = "yes";
    private String falseForm = "no";

    public Console() 
    {}
    
    public Console(Window window)
    {
    	setWindow(window);
        activeWindow.activateWindow();
    }
    
    protected void setWindow(Window window)
	{
		try
		{
			activeWindow = (Window) window.clone();
			activeWindow.activateWindow();
		} catch (CloneNotSupportedException e)
		{
			e.printStackTrace();
		}
		
		KEYBOARD_INPUT_HANDLER = new KeyboardInput(this);
		INPUT_STREAM_HANDLER = new InputStreamHandler();
	}
    
    /*
     * USER METHODES
     */
    public static void exitConsole() 
    {
    	System.exit(0);
    }
    
    public Window getActiveWindow() { return super.getActiveWindow(activeWindow);}
    public String getInputPlain() { return super.getInputPlain(activeWindow);}
    public Integer getInputInteger() {return super.getInputInt(activeWindow);}
    public Double getInpuDouble() {return super.getInputDouble(activeWindow);}
    public Float getInputFloat() {return super.getInputFloat(activeWindow);}
    public Boolean getInputBoolean() {return super.getInputBoolean(activeWindow);}
    
    public void terminateActiveWindow() {super.terminateActiveWindow(activeWindow);}
    public void switchWindow(Window newWindow) {super.switchWindow(activeWindow, this, newWindow);}
    
    public void setTrueFalseForm(String trueForm, String falseForm) {super.setTrueFalseForm(activeWindow, trueForm, falseForm);}
    public void setTrueForm(String trueForm) {super.setTrueForm(activeWindow, trueForm);}
    public void setFalseForm(String falseForm) {super.setFalseForm(activeWindow, falseForm);}
    
    public void clear() {super.clear(activeWindow);}
    public void clearll() {super.clearll(activeWindow);}
    public void clearll(int arg) {super.clearll(activeWindow, arg);}
    public void clearLastLine() {super.clearLastLine(activeWindow);}
    
    public <T> void print(T arg) {super.print(activeWindow, arg);}
    public void println() {super.println(activeWindow);}
    public <T> void println(T arg) {super.println(activeWindow, arg);}
    public <T> void lnprint(T arg) {super.lnprint(activeWindow, arg);}
    
    public void sleep(int arg) {super.sleep(activeWindow, arg);}
    public void sleepSec(int arg) {super.sleepSec(activeWindow, arg);}
    public void sleepNano(int arg) {super.sleepNano(activeWindow, arg);}
    
    public void waitForAnyKey() {super.waitForAnyKey(activeWindow);}
    public void askInput(UserInput arg) {super.askInput(activeWindow, arg);}
    public void addInputStream(InputStream arg) {super.addInputStream(activeWindow, arg);}
    public void removeInputStream(InputStream arg) {super.removeInputStream(activeWindow, arg);}
}
