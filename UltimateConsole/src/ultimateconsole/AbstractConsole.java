package ultimateconsole;

import javax.swing.text.BadLocationException;

class AbstractConsole
{
    private void setInputEditable(Window activeWindow, boolean arg, WindowElements elements)
    {
    	var inputPanel = elements.getInputPanel();
    	
        if (activeWindow != null)
		{
			try
			{
				inputPanel.setEditable(arg);
				inputPanel.setEnabled(arg);
				if (arg)
				{
					inputPanel.requestFocus();
					inputPanel.setCaretPosition(inputPanel.getText().length());
				} else
				{
					inputPanel.setText(null);
				} 
			} catch (Exception e)
			{
				throw new RuntimeException(e);
			} 
		}
    }
	
	public Window getActiveWindow(Window activeWindow)
	{
    	if (activeWindow != null)
		{
    		return activeWindow;
		}
    	return null;
	}
    
    public void terminateActiveWindow(Window activeWindow) 
    {
    	if (activeWindow != null)
		{
			activeWindow.dispose();
		}
    }
    
    public void switchWindow(Window activeWindow, Console console, Window newWindow) 
    {
    	if (activeWindow != null)
		{
//			terminateActiveWindow();
//			setWindow(window);
//			activateConsole();
    		activeWindow.deactivateWindow();
    		console.setWindow(newWindow);
		}
    }

    public void setTrueFalseForm(Window activeWindow ,String trueForm, String falseForm)
    {
        this.trueForm = trueForm;
        this.falseForm = falseForm;
    }

    public void setTrueForm(Window activeWindow, String trueForm)
    {
        setTrueFalseForm(trueForm, falseForm);
    }

    public void setFalseForm(Window activeWindow, String falseForm)
    {
        setTrueFalseForm(trueForm, falseForm);
    }

    public void clear(Window activeWindow)
    {
        if (activeWindow != null)
		{
			outputPanel.setText(null);
		}
    }

    public void clearll(Window activeWindow)
    {
        if (activeWindow != null)
		{
			try
			{
				int endOffset = outputPanel.getLineEndOffset(outputPanel.getLineCount() - 1);
				int startOffset = outputPanel.getLineStartOffset(outputPanel.getLineCount() - 2);
				if (startOffset > endOffset)
				{
					return;
				}
				outputPanel.replaceRange("", startOffset, endOffset);
			} catch (BadLocationException e)
			{
				throw new RuntimeException(e);
			} 
		}
    }
    
    public void clearll(Window activeWindow, int arg)
    {
    	if (activeWindow != null)
		{
			for (int i = arg; arg > 0; arg--)
			{
				clearll();
			} 
		}
    }

    public void clearLastLine(Window activeWindow)
    {
        clearll();
    }

    public <T> void print(Window activeWindow, T arg)
    {
        if (activeWindow != null)
		{
			outputPanel.setText(outputPanel.getText() + arg);
		}
    }

    public void println(Window activeWindow)
    {
        if (activeWindow != null)
		{
			outputPanel.append("\n");
		}
    }

    public <T> void println(Window activeWindow, T arg)
    {
        if (activeWindow != null)
		{
			outputPanel.append(arg + "\n");
		}
    }

    public <T> void lnprint(Window activeWindow, T arg)
    {
        if (activeWindow != null)
		{
			outputPanel.setText(outputPanel.getText() + "\n" + arg);
		}
    }

    public void sleep(Window activeWindow, long millis)
    {
        try
        {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void sleepSec(Window activeWindow, int seconds)
    {
        try
        {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e)
        {
            throw new RuntimeException(e);
        }
    }

    public void sleepNano(Window activeWindow, long nanos)
    {
        long start = System.nanoTime();
        long end=0;
        do
        {
            end = System.nanoTime();
        }while(start + nanos >= end);
    }

    public String getInputPlain(Window activeWindow)
    {
        if (activeWindow != null)
		{
        	String input = "";
			setInputEditable(true);
			
			synchronized (this)
			{
				try
				{
					KEYBOARD_INPUT_HANDLER.startInputListening();
					this.wait();
				}catch (InterruptedException e)
				{
					System.out.println("Caught:" + e);
				}
			}
			
			input = inputPanel.getText();
			setInputEditable(false);
			return input;
		}
		return null;
    }

    public Integer getInputInt(Window activeWindow) throws NumberFormatException
    {
        if (activeWindow != null)
		{
			return Integer.parseInt(getInputPlain());
		}
		return null;
    }

    public Double getInputDouble(Window activeWindow) throws NumberFormatException
    {
        if (activeWindow != null)
		{
			return Double.parseDouble(getInputPlain());
		}
        return null;
    }

    public Float getInputFloat(Window activeWindow) throws NumberFormatException
    {
        if (activeWindow != null)
		{
			return Float.parseFloat(getInputPlain());
		}
        return null;
    }

    public Boolean getInputBoolean(Window activeWindow)
    {
        if (activeWindow != null)
		{
			boolean endState = false;
			while (true)
			{
				String input = getInputPlain();

				if (input.equalsIgnoreCase(trueForm) || input.charAt(0) == trueForm.charAt(0))
				{
					endState = true;
					break;
				} else if (input.equalsIgnoreCase(falseForm) || input.charAt(0) == falseForm.charAt(0))
				{
					break;
				}
			}
			return endState;
		}
        return null;
    }
    
    public void waitForAnyKey(Window activeWindow) 
    {
    	if (activeWindow != null)
		{
			synchronized (this)
			{
				try
				{
					KEYBOARD_INPUT_HANDLER.startInputListeningForAnyKey();
					this.wait();
				} catch (InterruptedException e)
				{
					System.out.println("Caught:" + e);
				}
			} 
		}
    }

    public void askInput(Window activeWindow, UserInput userInput)
    {
        if (activeWindow != null)
		{
			if (userInput.getQuestionCount() == 0)
			{
				throw new RuntimeException("UserInput has no question to ask !");
			}
			userInput.resetSelectedQuestion();
			synchronized (this)
			{
				try
				{
					KEYBOARD_INPUT_HANDLER.startInputListeningWithUserInput(userInput);
					this.wait();
				} catch (InterruptedException e)
				{
					System.out.println("Caught:" + e);
				}
			} 
		}
    }

    public void addInputStream(Window activeWindow, InputStream inputStream) 
    {
    	if (activeWindow != null)
		{
			INPUT_STREAM_HANDLER.addInputStream(inputStream);
		}
    }
    
    public void removeInputStream(Window activeWindow, InputStream inputStream) 
    {
    	if (activeWindow != null)
		{
			INPUT_STREAM_HANDLER.removeInputStream(inputStream);
		}
    }
}
