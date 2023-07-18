package ultimateconsole;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

class InputKeyControl extends Thread implements KeyListener
{

    private boolean listensInput = false;
    private boolean customUserInput = false;

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
        if(userInput != null){userInput = null;}
        clearScreen();
        synchronized(CONSOLE)
        {
            CONSOLE.notify();
        }
        synchronized(this)
        {
            this.interrupt();
        }
    }

    protected InputKeyControl(DrawPanel panel, Console Console)
    {
        this.DRAW_PANEL = panel;
        this.CONSOLE = Console;
    }

    @Override
    public void keyTyped(KeyEvent e)
    {

    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        if(listensInput)
        {

            if(userInput != null)
            {
                if(userInput.getInputType() != UserInputTypes.Numeric && e.getKeyCode() == KeyEvent.VK_ENTER)
                {
                    pressedEnter();
                }
            }else if(e.getKeyCode() == KeyEvent.VK_ENTER)
            {
                pressedEnter();
            }

            if(customUserInput && userInput != null && userInput.getInputType() != UserInputTypes.Numeric)
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
            }else if(customUserInput  && userInput != null)
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
            customUserInput = false;
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

    public void run()
    {
        super.run();
        listensInput = true;
    }

    protected void runWithCustomUserInput(UserInput userInput)
    {
        run();
        customUserInput = true;
        this.userInput = userInput;
        paintWindow();
        DRAW_PANEL.addComponentListener(resizeEvent);
    }
}
