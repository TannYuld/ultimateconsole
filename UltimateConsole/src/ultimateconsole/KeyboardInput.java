package ultimateconsole;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

class KeyboardInput extends Thread implements KeyListener
{

    private boolean listensInput = false;
    private boolean listenForAnyKey = false;
    private UserInput userInput = null;

    private final Console CONSOLE;
    private final DrawPanel DRAW_PANEL;
    

    ComponentAdapter resizeEvent = new ComponentAdapter() {
        public void componentResized(ComponentEvent e) {
            paintWindow();
        }
    };

    private void pressedEnter()
    {
        listensInput = false;
        listenForAnyKey = false;
        if(userInput != null){userInput = null;}
        synchronized(CONSOLE)
        {
            CONSOLE.notify();
        }
        synchronized(this)
        {
            this.interrupt();
        }
    }

    protected KeyboardInput(Console Console)
    {
        this.CONSOLE = Console;
        DRAW_PANEL = CONSOLE.getCurrentWindow().getDrawPanel();
    }

    @Override
    public void keyTyped(KeyEvent e){}

    @Override
    public void keyPressed(KeyEvent e)
    {
        if(listensInput)
        {       
            if(e.getKeyCode() == KeyEvent.VK_ENTER || listenForAnyKey) 
            {
            	if((userInput != null &&
                		userInput.getInputType() != UserInputTypes.Numeric) || userInput == null) 
            	{
            		if(userInput != null) {clearScreen();}
            		pressedEnter();
            	}
            }

            if(userInput != null && userInput.getInputType() != UserInputTypes.Numeric)
            {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT -> {
                        userInput.goLeft();
                        paintWindow();
                    }
                    case KeyEvent.VK_RIGHT -> {
                        userInput.goRight();
                        paintWindow();
                    }
                }
            }else if(userInput != null)
            {
                switch (e.getKeyCode())
                {
                    case KeyEvent.VK_0 -> pressedNumber(0);
                    case KeyEvent.VK_1 -> pressedNumber(1);
                    case KeyEvent.VK_2 -> pressedNumber(2);
                    case KeyEvent.VK_3 -> pressedNumber(3);
                    case KeyEvent.VK_4 -> pressedNumber(4);
                    case KeyEvent.VK_5 -> pressedNumber(5);
                    case KeyEvent.VK_6 -> pressedNumber(6);
                    case KeyEvent.VK_7 -> pressedNumber(7);
                    case KeyEvent.VK_8 -> pressedNumber(8);
                    case KeyEvent.VK_9 -> pressedNumber(9);
                }
            }
        }
    }

    private void pressedNumber(int idx)
    {
        if(userInput.getQuestionCount() >= idx + 1)
        {
            clearScreen();
            userInput.setSelectedQuestion(idx);
            listensInput = false;
            if(userInput != null){userInput = null;}
            synchronized (CONSOLE)
            {
                CONSOLE.notify();
            }
            synchronized (this) {
                this.interrupt();
            }
        }
    }

    private void clearScreen()
    {
    	DRAW_PANEL.clearRect();
        DRAW_PANEL.clearText();
        DRAW_PANEL.removeComponentListener(resizeEvent);
        DRAW_PANEL.repaint();
    }

    private void paintWindow()
    {
        if(userInput.getInputType() != UserInputTypes.Numeric)
        {
        	DRAW_PANEL.paintUserInputSelection(userInput);
        	DRAW_PANEL.setSelectedFillRect(
        			DRAW_PANEL.getRectDrawList().get(userInput.getSelectedQuestion())
            );
        }
        else{DRAW_PANEL.paintUserInputNumeric(userInput);}

        DRAW_PANEL.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {}
    
    protected void startInputListening() 
    {
    	super.run();
        listensInput = true;
    }
    
    protected void startInputListeningForAnyKey() 
    {
    	startInputListening();
        listenForAnyKey = true;
    }
    
    protected void startInputListeningWithUserInput(UserInput userInput)
    {
    	startInputListening();
        this.userInput = userInput;
        paintWindow();
        DRAW_PANEL.addComponentListener(resizeEvent);
    }
}
