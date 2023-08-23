package ultimateconsole;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultEditorKit;

import java.util.ArrayList;

public class Console {
    private final JTextArea CONSOLE_OUTPUT;
    private final JTextField CONSOLE_INPUT;
    private final Window WINDOW;
    private final KeyboardInput KEYBOARD_INPUT_HANDLER;
    private final InputStreamHandler INPUT_STREAM_HANDLER;

    private Window activeWindow = null;
    
    private String trueForm = "yes";
    private String falseForm = "no";

    public Console(Window window)
    {
    	WINDOW = window;
        CONSOLE_OUTPUT = window.getOutputTextArea();
        CONSOLE_INPUT = window.getInputTextArea();
        KEYBOARD_INPUT_HANDLER = new KeyboardInput(this);
        INPUT_STREAM_HANDLER = new InputStreamHandler();
        
        activateConsole();
    }
    
    public Console(Window window, boolean selfActivation) 
    {
    	WINDOW = window;
        CONSOLE_OUTPUT = window.getOutputTextArea();
        CONSOLE_INPUT = window.getInputTextArea();
        KEYBOARD_INPUT_HANDLER = new KeyboardInput(this);
        INPUT_STREAM_HANDLER = new InputStreamHandler();
        
    	if(selfActivation) 
    	{
    		activateConsole();
    	}
    }
    
    public Console activateConsole() 
    {
    	WINDOW.PutElementsOnScreen();
    	WINDOW.setVisible(true);
    	WINDOW.setFocusable(true);
    	WINDOW.setFocusableWindowState(true);
    	WINDOW.requestFocusInWindow();

    	WINDOW.addKeyListener(KEYBOARD_INPUT_HANDLER);
        CONSOLE_OUTPUT.addKeyListener(KEYBOARD_INPUT_HANDLER);
        CONSOLE_INPUT.addKeyListener(KEYBOARD_INPUT_HANDLER);
        CONSOLE_OUTPUT.addKeyListener(INPUT_STREAM_HANDLER);
        CONSOLE_INPUT.addKeyListener(INPUT_STREAM_HANDLER);
        //CONSOLE_OUTPUT.setFocusable(false);
        
        Action beepOutput = CONSOLE_OUTPUT.getActionMap().get(DefaultEditorKit.deletePrevCharAction);
        Action beepInput = CONSOLE_INPUT.getActionMap().get(DefaultEditorKit.deletePrevCharAction);
        beepOutput.setEnabled(false);
        beepInput.setEnabled(false);
        
        return this;
    }
    
    public Window getCurrentWindow()
	{
		return WINDOW;
	}

    public static void terminateAllConsoles()
    {
        System.exit(0);
    }
    
    public void terminateConsole() 
    {
    	WINDOW.dispose();
    }
    
    public void showWindow() 
    {
    	WINDOW.setVisible(true);
    }
    
    public void hideWindow() 
    {
    	WINDOW.setVisible(false);
    }

    private void setInputEditable(boolean arg)
    {
        CONSOLE_INPUT.setEditable(arg);
        CONSOLE_INPUT.setEnabled(arg);

        if (arg)
        {
            CONSOLE_INPUT.requestFocus();
            CONSOLE_INPUT.setCaretPosition(CONSOLE_INPUT.getText().length());
        } else
        {
            CONSOLE_INPUT.setText(null);
        }
    }

    public void setTrueFalseForm(String trueForm, String falseForm)
    {
        this.trueForm = trueForm;
        this.falseForm = falseForm;
    }

    public void setTrueForm(String trueForm)
    {
        setTrueFalseForm(trueForm, falseForm);
    }

    public void setFalseForm(String falseForm)
    {
        setTrueFalseForm(trueForm, falseForm);
    }

    public void clear()
    {
        CONSOLE_OUTPUT.setText(null);
    }

    public void clearll()
    {
        try
        {
            int endOffset = CONSOLE_OUTPUT.getLineEndOffset(CONSOLE_OUTPUT.getLineCount()-1);
            int startOffset = CONSOLE_OUTPUT.getLineStartOffset(CONSOLE_OUTPUT.getLineCount()-2);
            if(startOffset > endOffset){return;}
            CONSOLE_OUTPUT.replaceRange("", startOffset, endOffset);
        } catch (BadLocationException e) {
            throw new RuntimeException(e);
        }
    }
    
    public void clearll(int arg)
    {
    	for(int i = arg; arg > 0; arg--) 
    	{
    		clearll();
    	}
    }

    public void clearLastLine()
    {
        clearll();
    }

    public <T> void print(T arg)
    {
        CONSOLE_OUTPUT.setText(CONSOLE_OUTPUT.getText() + arg);
    }

    public void println()
    {
        CONSOLE_OUTPUT.append("\n");
    }

    public <T> void println(T arg)
    {
        CONSOLE_OUTPUT.append(arg + "\n");
    }

    public <T> void lnprint(T arg)
    {
        CONSOLE_OUTPUT.setText(CONSOLE_OUTPUT.getText() + "\n" + arg);
    }

    public void sleep(long millis)
    {
        try
        {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void sleepSec(int seconds)
    {
        try
        {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e)
        {
            throw new RuntimeException(e);
        }
    }

    public void sleepNano(long nanos)
    {
        long start = System.nanoTime();
        long end=0;
        do
        {
            end = System.nanoTime();
        }while(start + nanos >= end);
    }

    public String getInputPlain()
    {
        String input = "";

        try
        {
            setInputEditable(true);
            
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        synchronized(this) {
            try
            {
                KEYBOARD_INPUT_HANDLER.startInputListening();
                this.wait();
            } catch (InterruptedException e) {
                System.out.println("Caught:" + e);
            }
        }

        input = CONSOLE_INPUT.getText();

        try
        {
            setInputEditable(false);
        } catch (Exception e) {
            System.out.println("Caught: "+e);
            throw new RuntimeException(e);
        }

        return input;
    }

    public int getInputInt() throws NumberFormatException
    {
        return Integer.parseInt(getInputPlain());
    }

    public double getInputDouble() throws NumberFormatException
    {
        return Double.parseDouble(getInputPlain());
    }

    public float getInputFloat() throws NumberFormatException
    {
        return Float.parseFloat(getInputPlain());
    }

    public boolean getInputBoolean()
    {
        boolean endState = false;

        while(true)
        {
            String input = getInputPlain();

            if(input.equalsIgnoreCase(trueForm) || input.charAt(0) == trueForm.charAt(0))
            {
                endState = true;
                break;
            }else if(input.equalsIgnoreCase(falseForm) || input.charAt(0) == falseForm.charAt(0))
            {
                break;
            }
        }

        return endState;
    }
    
    public void waitForAnyKey() 
    {
    	synchronized(this) {
            try
            {
                KEYBOARD_INPUT_HANDLER.startInputListeningForAnyKey();
                this.wait();
            } catch (InterruptedException e) {
                System.out.println("Caught:" + e);
            }
        }
    }

    public void askInput(UserInput userInput)
    {
        if(userInput.getQuestionCount() == 0){throw new RuntimeException("UserInput has no question to ask !");}
        userInput.resetSelectedQuestion();

        synchronized(this)
        {
            try
            {
                KEYBOARD_INPUT_HANDLER.startInputListeningWithUserInput(userInput);
                this.wait();
            } catch (InterruptedException e) {
                System.out.println("Caught:" + e);
            }
        }
    }

    public void addInputStream(InputStream inputStream) 
    {
    	INPUT_STREAM_HANDLER.addInputStream(inputStream);
    }
    
    public void removeInputStream(InputStream inputStream) 
    {
    	INPUT_STREAM_HANDLER.removeInputStream(inputStream);
    }

}
