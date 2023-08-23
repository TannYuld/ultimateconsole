package ultimateconsole;

import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.*;

public class Window implements Cloneable {
	
    private WindowProperties properties;
    private WindowElements elements;

    public Window(String title, WindowProperties properties) 
    {
    	setProperties(properties);
    	elements = new WindowElements(this, title, properties);
    	properties.addToAssignedWindowList(this);
    }
    
    protected void setProperties(WindowProperties properties) 
    {
    	this.properties = properties;
    	if(elements != null) 
    	{
    		elements.changeProperties(properties);
    	}
    }
    
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
    
    protected WindowElements getElements() {return elements;}
    
    /*
     * USER METHODES
     */
    public WindowProperties getProperties() {return properties;}
    public DrawDimension getRealSize()	{ return new DrawDimension(0,0,elements.getSize().width, elements.getSize().height);}
    
    public void setVisibile(Boolean arg) 
    {
    	elements.setVisible(arg);
    }
    
    public void activateWindow() 
    {
    	elements.activate();
    }
    
    public void deactivateWindow() 
    {
    	elements.dispose();
    }
    
    public void setWindowProperties(WindowProperties arg) 
    {
    	properties.removeFromAssignedWindowList(this);
    	setProperties(arg);
    }
}
