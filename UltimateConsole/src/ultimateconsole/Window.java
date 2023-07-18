package ultimateconsole;

import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.*;

public class Window extends JFrame {
    private WindowProperties property;

    private final JLayeredPane LAYERED_PANE = new JLayeredPane();

    private final JTextArea OUTPUT_TEXTAREA = new JTextArea();
    private final JTextField INPUT_TEXTAREA = new JTextField();

    private final JScrollPane OUTPUT_SCROLL_PANE = new JScrollPane(OUTPUT_TEXTAREA);
    private final DrawPanel DRAW_PANEL = new DrawPanel(this);

    public Window(String title,WindowProperties prop)
    {
        super(title);
        prop.setAssignedWindow(this);
        add(LAYERED_PANE);
        LAYERED_PANE.setLayout(new GridBagLayout());
        getContentPane().setBackground(Color.BLACK);
        LAYERED_PANE.setBackground(Color.BLACK);

        DefaultCaret caret = (DefaultCaret) OUTPUT_TEXTAREA.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

        setProperty(prop);
    }

    public void PutElementsOnScreen()
    {
        GridBagConstraints GridConst = new GridBagConstraints();

        INPUT_TEXTAREA.setBorder(null);
        OUTPUT_TEXTAREA.setEditable(false);
        OUTPUT_TEXTAREA.setLineWrap(false);
        INPUT_TEXTAREA.setEditable(false);
        INPUT_TEXTAREA.setEnabled(false);

        GridConst.weighty = 1;
        GridConst.weightx = 1;
        GridConst.fill = GridBagConstraints.BOTH;

        GridConst.gridx = 0;
        GridConst.gridy = 0;
        GridConst.gridheight = 1;
        OUTPUT_SCROLL_PANE.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        OUTPUT_SCROLL_PANE.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        OUTPUT_SCROLL_PANE.setBorder(null);
        LAYERED_PANE.add(OUTPUT_SCROLL_PANE, GridConst);
        LAYERED_PANE.setLayer(OUTPUT_SCROLL_PANE, 1);

        GridConst.weighty = 0;
        GridConst.gridy = 1;
        GridConst.fill = GridBagConstraints.HORIZONTAL;
        GridConst.anchor = GridBagConstraints.PAGE_END;
        LAYERED_PANE.add(INPUT_TEXTAREA, GridConst);
        LAYERED_PANE.setLayer(INPUT_TEXTAREA, 1);


        DRAW_PANEL.setOpaque(false);
        GridConst.gridy = 0;
        GridConst.gridheight = 0;
        GridConst.fill = GridBagConstraints.BOTH;
        GridConst.anchor = GridBagConstraints.BELOW_BASELINE;
        LAYERED_PANE.add(DRAW_PANEL, GridConst);
        LAYERED_PANE.setLayer(DRAW_PANEL, 2);
    }

    public void setProperty(WindowProperties prop)
    {
        property = prop;

        setSize(new Dimension(property.getWindowWidth(), prop.getWindowHeight()));
        LAYERED_PANE.setSize(new Dimension(property.getWindowWidth(), prop.getWindowHeight()));
        setLocation(property.getWindowPosX(), property.getWindowPosY());
        setResizable(property.getCanResize());

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

    public JTextArea getOutputTextArea() { return OUTPUT_TEXTAREA;}
    public JTextField getInputTextArea () { return INPUT_TEXTAREA;}
    public DrawPanel getDrawPanel() { return DRAW_PANEL;}
    public WindowProperties getProperties() {return property;}
    public DrawDimension getRealSize()
    {
        return new DrawDimension(0,0,getSize().width, getSize().height);
    }
}
