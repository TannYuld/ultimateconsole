package ultimateconsole;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public class WindowProperties
{
    private ArrayList<Window> assignedWindowList = new ArrayList<Window>();

    // Default variables
    private int windowHeight = 325;
    private int windowWidth = 540;
    private int windowPosX = 195;
    private int windowPosY = 225;

    private boolean canResize = false;
    private boolean alwaysOnTop = false;
    private boolean isFullscreen = false;
    private boolean lineWrapping = false;

    private Insets outputTextMargin = new Insets(-5,3,0,0);
    private Font outputTextFont = new Font("Monospaced", Font.PLAIN, 19);
    private Font inputTextFont = new Font("Monospaced", Font.PLAIN, 19);

    private Color inputTextBackground = new Color(38, 38, 38);
    private Color inputTextForeground = Color.WHITE;
    private Color inputSelectedColorText = Color.BLACK;
    private Color inputTextCaretColor = Color.cyan;
    private Color outputTextBackground = Color.BLACK;
    private Color outputTextForeground = Color.WHITE;
    private Color outputSelectedColorText = Color.DARK_GRAY;

    CloseButtonOperation defaultCloseButtonOperation = CloseButtonOperation.EXIT;

    private WindowProperties changeProperties()
    {
        if(!assignedWindowList.isEmpty())
        {
            Iterator<Window> itr = assignedWindowList.iterator();
            
            while(itr.hasNext()) 
            {
            	var window = itr.next();
            	window.setProperties(this);
            }
        }
        return this;
    }
    
    public WindowProperties windowHeight(int arg)
    {
        windowHeight = arg;
        return changeProperties();
    }

    public WindowProperties windowWidth(int arg)
    {
        windowWidth = arg;
        return changeProperties();
    }

    public WindowProperties windowPosX(int arg)
    {
        windowPosX = arg;
        return changeProperties();
    }

    public WindowProperties windowPosY(int arg)
    {
        windowPosY = arg;
        return changeProperties();
    }

    public WindowProperties canResize(boolean arg)
    {
        canResize = arg;
        return changeProperties();
    }

    public WindowProperties alwaysOnTop(boolean arg)
    {
        alwaysOnTop = arg;
        return changeProperties();
    }

    public WindowProperties outputTextMargin(Insets arg)
    {
        outputTextMargin = arg;
        return changeProperties();
    }

    public WindowProperties outputTextFont(Font arg)
    {
        outputTextFont = arg;
        return changeProperties();
    }

    public WindowProperties inputTextFont(Font arg)
    {
        inputTextFont = arg;
        return changeProperties();
    }

    public WindowProperties inputTextForeground(Color arg)
    {
        inputTextForeground = arg;
        return changeProperties();
    }

    public WindowProperties outputTextForeground(Color arg)
    {
        outputTextForeground = arg;
        return changeProperties();
    }

    public WindowProperties inputTextBackground(Color arg)
    {
        inputTextBackground = arg;
        return changeProperties();
    }

    public WindowProperties outputTextBackground(Color arg)
    {
        outputTextBackground = arg;
        return changeProperties();
    }

    public WindowProperties inputSelectedTextColor(Color arg)
    {
        inputSelectedColorText = arg;
        return changeProperties();
    }

    public WindowProperties outputSelectedColor(Color arg)
    {
        outputSelectedColorText = arg;
        return changeProperties();
    }

    public WindowProperties inputTextCaretColor(Color arg)
    {
        inputTextCaretColor = arg;
        return changeProperties();
    }

    public WindowProperties isFullscreen(Boolean arg)
    {
        isFullscreen = arg;
        return changeProperties();
    }

    public WindowProperties closeOperation(CloseButtonOperation arg)
    {
        defaultCloseButtonOperation = arg;
        return changeProperties();
    }
    
    public WindowProperties lineWrapping(Boolean arg)
    {
    	lineWrapping = arg;
        return changeProperties();
    }

    protected void addToAssignedWindowList(Window arg)
    {
    	assignedWindowList.add(arg);
    }
    
    protected void removeFromAssignedWindowList(Window arg) 
    {
    	if(assignedWindowList.contains(arg)) 
    	{
    		Iterator<Window> itr = assignedWindowList.iterator();
    		
    		while(itr.hasNext()) 
    		{
    			var window = itr.next();
    			
    			if(window == arg) 
    			{
    				itr.remove();
    			}
    		}
    	}
    }

    protected int getWindowHeight() { return windowHeight;}
    protected int getWindowWidth() { return windowWidth;}
    protected int getWindowPosX() { return windowPosX;}
    protected int getWindowPosY() { return windowPosY;}
    protected boolean getCanResize() { return canResize;}
    protected boolean getAlwaysOnTop() { return alwaysOnTop;}
    protected Insets getOutputTextMargin() { return outputTextMargin;}
    protected Font getOutputTextFont() { return outputTextFont;}
    protected Font getInputTextFont() { return inputTextFont;}
    protected Color getInputTextBackground() { return inputTextBackground;}
    protected Color getInputTextForeground() { return inputTextForeground;}
    protected Color getOutputTextForeground() { return outputTextForeground;}
    protected Color getOutputTextBackground() { return outputTextBackground;}
    protected Color getInputSelectedTextColor() { return inputSelectedColorText;}
    protected Color getOutputSelectedTextColor() { return outputSelectedColorText;}
    protected Color getInputTextCaretColor() { return inputTextCaretColor;}
    protected int getDefaultCloseButtonOperation() { return defaultCloseButtonOperation.value;}
    protected boolean getFullscreen(){ return isFullscreen;}
    protected boolean getLineWrapping(){return lineWrapping;}
	
}