package ultimateconsole;

import javax.swing.*;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class DrawPanel extends JPanel
{
    private final Window WINDOW;

    private ArrayList<DrawDimension> rectDrawList = new ArrayList<>();
    private ArrayList<DrawText> textDrawList = new ArrayList<>();
    private ArrayList<DrawDimension> textDimensionList = new ArrayList<>();

    private DrawDimension selectedSquare = new DrawDimension(0,0,0,0);

    protected DrawPanel(Window arg)
    {
        WINDOW = arg;
    }

    public void paint(Graphics g)
    {
        super.paint(g);

        Graphics2D g2D = (Graphics2D) g;

        g2D.setColor(Color.cyan);

        for (DrawDimension dimension : rectDrawList)
        {
            g2D.drawRect(dimension.x, dimension.y, dimension.width, dimension.height);
        }

        g2D.setFont(WINDOW.getProperties().getInputTextFont());
        for (int i = 0; i < textDrawList.size(); i++)
        {
            g2D.drawString(textDrawList.get(i).text(), textDrawList.get(i).dimension().x, textDrawList.get(i).dimension().y);
        }

        g2D.fillRect(selectedSquare.x, selectedSquare.y, selectedSquare.width, selectedSquare.height);
    }

    private void appendRectDraw(DrawDimension dimension)
    {
        rectDrawList.add(dimension);
    }

    protected ArrayList<DrawDimension> getRectDrawList()
    {
        return rectDrawList;
    }

    private int appendTextDraw(DrawDimension dimension, String str)
    {
        textDrawList.add(new DrawText(str, dimension));
        AffineTransform affinetransform = new AffineTransform();
        FontRenderContext frc = new FontRenderContext(affinetransform,true,false);
        Rectangle2D rect = WINDOW.getProperties().getInputTextFont().getStringBounds(str, frc);
        textDimensionList.add(new DrawDimension(0,0,(int) rect.getBounds().getWidth(), (int) rect.getBounds().getHeight()));
        return textDrawList.size();
    }

    protected void paintUserInputSelection(UserInput userInput)
    {
        this.clearRect();
        this.clearText();

        int x = 15,y = WINDOW.getRealSize().height - 60;
        for (int i = 0; i < userInput.getQuestionCount(); i++)
        {
            this.appendRectDraw(new DrawDimension(x, y,15,15));
            x += 20;
            this.appendTextDraw(new DrawDimension(x,y+15), userInput.getQuestion(i));
            x = x + this.getTextDimension(i).width + 20;
            if (x + this.getTextDimension(i).width + 20 + 20 > WINDOW.getRealSize().width)
            {
                y -= 30;
                x = 15;
            }
        }

        this.repaint();
    }

    protected void paintUserInputNumeric(UserInput userInput)
    {
        this.clearText();

        int totalWidth = 15;
        int x = 15, y = WINDOW.getRealSize().height - 45;
        int columnSize = 0;
        for(int i = 0; i < userInput.getQuestionCount(); i++)
        {
            columnSize++;
            this.appendTextDraw(new DrawDimension(x, y), "(" + i + ")" + userInput.getQuestion(i));
            x += this.getTextDimension(i).width + 25;
            totalWidth += this.getTextDimension(i).width + 25;
            if(x >= this.WINDOW.getRealSize().width)
            {
                break;
            }
        }

        if(columnSize - 1 != 0)
        {
            columnSize -= 1;
        }
        if(this.WINDOW.getRealSize().width >= totalWidth)
        {
            columnSize += 1;
        }
        this.clearText();

        x = 15; y = WINDOW.getRealSize().height - 45;

        int columnElementCount = userInput.getQuestionCount() / columnSize;
        int columnExtraElementCount = userInput.getQuestionCount() % columnSize;
        int questionCounter = 0;

        for (int i = 0; i < columnSize; i++)
        {
            int extraElement = 0;
            y = WINDOW.getRealSize().height - 45;
            if (columnExtraElementCount > 0) {
                extraElement = 1;
            }

            String longestText = "";
            int selectedLongestText = 0;
            for (int e = 0; e < columnElementCount + extraElement; e++) {
                int drawedText = this.appendTextDraw(new DrawDimension(x, y), "(" + questionCounter + ")" + userInput.getQuestion(questionCounter));
                y -= 30;

                if (userInput.getQuestion(questionCounter).length() > longestText.length()) {
                    longestText = userInput.getQuestion(questionCounter);
                    selectedLongestText = drawedText;
                }
                questionCounter++;
            }
            columnExtraElementCount--;
            x += this.getTextDimension(selectedLongestText - 1).width + 25;

        }
    }

    private DrawDimension getTextDimension(int idx)
    {
        return textDimensionList.get(idx);
    }

    protected void setSelectedFillRect(DrawDimension dimension)
    {
        selectedSquare = dimension;
    }

    protected void clearRect()
    {
        this.rectDrawList = new ArrayList<>();
        this.selectedSquare = new DrawDimension(0,0,0,0);
    }

    protected void clearText()
    {
        this.textDrawList = new ArrayList<>();
        this.textDimensionList = new ArrayList<>();
    }
}
