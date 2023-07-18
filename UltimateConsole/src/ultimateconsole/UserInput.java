package ultimateconsole;

import java.util.ArrayList;

public class UserInput
{
    private final UserInputTypes inputType;

    private final ArrayList<String> questions = new ArrayList<>();
    private int selectedQuestion = 0;

    public UserInput(UserInputTypes inputType)
    {
        this.inputType = inputType;
    }

    public UserInput addQuestion(String questionText)  {
        if(inputType == UserInputTypes.YesNo && questions.size() > 1)
        {
            throw new RuntimeException("YesNo question type can't have more than 2 question");
        }

        if(inputType == UserInputTypes.Numeric && questions.size() > 9)
        {
            throw new RuntimeException("Numeration question type can't have more than 10 question");
        }

        questions.add(questionText);
        return this;
    }

    protected void goRight()
    {
        if(selectedQuestion < questions.size() - 1)
        {
            selectedQuestion++;
        }
        else
        {
            selectedQuestion = 0;
        }
    }

    protected void goLeft()
    {
        if(selectedQuestion > 0)
        {
            selectedQuestion--;
        }else
        {
            selectedQuestion = questions.size() - 1;
        }
    }

    protected int getSelectedQuestion()
    {
        return selectedQuestion;
    }

    public int getQuestionCount()
    {
        return questions.size();
    }

    protected void resetSelectedQuestion()
    {
        selectedQuestion = 0;
    }

    public UserInputTypes getInputType(){return inputType;}

    public String getQuestion(int idx)
    {
        return questions.get(idx);
    }

    protected void setSelectedQuestion(int idx)
    {
        if(idx < 0 || idx > getQuestionCount())
        {
            throw new RuntimeException("Undefined question: "+idx+"  ||total question "+getQuestionCount());
        }
        if(idx > questions.size()){throw new RuntimeException("There are "+questions.size()+" questions but tried to set selected question " + idx);}
        selectedQuestion = idx;
    }

    public int getResult()
    {
        if(inputType == UserInputTypes.YesNo){throw new RuntimeException("This type of result does not support YesNo questions !");}
        else if(inputType == UserInputTypes.Selection){return selectedQuestion;}
        else{return selectedQuestion;}

    }

    public boolean getBooleanResult()
    {
        if(inputType != UserInputTypes.YesNo){throw new RuntimeException("This type of result only supports YesNo questions !");}
        return selectedQuestion == 0;
    }
}
