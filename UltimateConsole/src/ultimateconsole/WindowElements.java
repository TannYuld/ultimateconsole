package ultimateconsole;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class WindowElements extends JFrame
{
	private final JLayeredPane LAYERED_PANE;

    private final JTextArea OUTPUT_TEXTAREA;
    private final JTextField INPUT_TEXTAREA;
    private final JScrollPane OUTPUT_SCROLL_PANE;
    private final DrawPanel DRAW_PANEL;
    
    public WindowElements(Window self, String title, WindowProperties properties) 
    {
    	super(title);
    	
    	LAYERED_PANE = new JLayeredPane();
    	OUTPUT_TEXTAREA = new JTextArea();
    	INPUT_TEXTAREA = new JTextField();
    	OUTPUT_SCROLL_PANE = new JScrollPane(OUTPUT_TEXTAREA);
    	DRAW_PANEL = new DrawPanel(self);
    	
    	preActivate();
    }
    
    private void preActivate() 
    {
    	LAYERED_PANE.setLayout(new GridBagLayout());
    	
    	GridBagConstraints outputScrollConstraits = new GridBagConstraints();
    	GridBagConstraints inputPanelConstraits = new GridBagConstraints();
    	GridBagConstraints drawPanelConstraints = new GridBagConstraints();
    	
    	INPUT_TEXTAREA.setBorder(null);
        OUTPUT_TEXTAREA.setEditable(false);
        OUTPUT_TEXTAREA.setLineWrap(false);
        INPUT_TEXTAREA.setEditable(false);
        INPUT_TEXTAREA.setEnabled(false);
    	
        outputScrollConstraits.weighty = 1;
        outputScrollConstraits.weightx = 1;
        outputScrollConstraits.fill = GridBagConstraints.BOTH;

        outputScrollConstraits.gridx = 0;
        outputScrollConstraits.gridy = 0;
        outputScrollConstraits.gridheight = 1;
        OUTPUT_SCROLL_PANE.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        OUTPUT_SCROLL_PANE.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        OUTPUT_SCROLL_PANE.setBorder(null);
        LAYERED_PANE.add(OUTPUT_SCROLL_PANE, outputScrollConstraits);
        LAYERED_PANE.setLayer(OUTPUT_SCROLL_PANE, 1);
        
        inputPanelConstraits.weighty = 0;
        inputPanelConstraits.gridy = 1;
        inputPanelConstraits.fill = GridBagConstraints.HORIZONTAL;
        inputPanelConstraits.anchor = GridBagConstraints.PAGE_END;
        LAYERED_PANE.add(INPUT_TEXTAREA, inputPanelConstraits);
        LAYERED_PANE.setLayer(INPUT_TEXTAREA, 1);

        DRAW_PANEL.setOpaque(false);
        drawPanelConstraints.gridy = 0;
        drawPanelConstraints.gridheight = 0;
        drawPanelConstraints.fill = GridBagConstraints.BOTH;
        drawPanelConstraints.anchor = GridBagConstraints.BELOW_BASELINE;
        LAYERED_PANE.add(DRAW_PANEL, drawPanelConstraints);
        LAYERED_PANE.setLayer(DRAW_PANEL, 2);
    }
    
    protected void activate() 
    {
    	setSize(540, 320);
    	LAYERED_PANE.setSize(540, 320);
    	getContentPane().add(LAYERED_PANE);
    	setVisible(true);
    }
    
    protected void changeProperties(WindowProperties prop) 
    {
    	OUTPUT_TEXTAREA.setLineWrap(prop.getLineWrapping());
        
        setSize(new Dimension(prop.getWindowWidth(), prop.getWindowHeight()));
        LAYERED_PANE.setSize(new Dimension(prop.getWindowWidth(), prop.getWindowHeight()));
        setLocation(prop.getWindowPosX(), prop.getWindowPosY());
        setResizable(prop.getCanResize());

        setDefaultCloseOperation(prop.getDefaultCloseButtonOperation());

        INPUT_TEXTAREA.setForeground(prop.getInputTextForeground());
        INPUT_TEXTAREA.setBackground(prop.getInputTextBackground());
        OUTPUT_TEXTAREA.setForeground(prop.getOutputTextForeground());
        OUTPUT_TEXTAREA.setBackground(prop.getOutputTextBackground());

        OUTPUT_TEXTAREA.setMargin(prop.getOutputTextMargin());

        INPUT_TEXTAREA.setFont(prop.getInputTextFont());
        OUTPUT_TEXTAREA.setFont(prop.getOutputTextFont());

        INPUT_TEXTAREA.setCaretColor(prop.getInputTextCaretColor());
        INPUT_TEXTAREA.setSelectedTextColor(prop.getInputSelectedTextColor());
        OUTPUT_TEXTAREA.setSelectedTextColor(prop.getOutputSelectedTextColor());
        
        setAlwaysOnTop(prop.getAlwaysOnTop());

        if(prop.getFullscreen())
        {
            this.dispose();
            setUndecorated(true);
            this.pack();
            this.setVisible(true);
            setExtendedState(JFrame.MAXIMIZED_BOTH);
        }
    }

	protected JScrollPane getScrollPanel(){return OUTPUT_SCROLL_PANE;}
	protected JTextArea getOutputPanel(){return OUTPUT_TEXTAREA;}
	protected JTextField getInputPanel(){return INPUT_TEXTAREA;}
	protected JLayeredPane getPaneLayered(){return LAYERED_PANE;}
	protected DrawPanel getDrawPanel() {return DRAW_PANEL;}
	
}
