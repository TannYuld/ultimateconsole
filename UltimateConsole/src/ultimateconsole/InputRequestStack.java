package ultimateconsole;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public abstract class InputRequestStack implements KeyListener {
    private final char REQUEST_CHAR;
    private int REQUEST_CHAR_KEY_CODE;
    private final FinalState FINAL_STATE;
    private Console c;

    public void actionPerformed(){}

    public InputRequestStack(Character requestChar, FinalState finalState)
    {
        REQUEST_CHAR = requestChar;
        FINAL_STATE = finalState;

        try 
        {
        	REQUEST_CHAR_KEY_CODE = KeyEvent.getExtendedKeyCodeForChar(REQUEST_CHAR);
        }catch(Exception e)
        {
        	
        }
        
    }

    public char getChar()
    {
        return REQUEST_CHAR;
    }
    protected void setConsole(Console c)
    {
        this.c = c;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        if(e.getKeyCode() == REQUEST_CHAR_KEY_CODE)
        {
            this.actionPerformed();
            if(FINAL_STATE == FinalState.REMOVE)
            {
                c.removeKeyListener(this);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    	
    }
}
