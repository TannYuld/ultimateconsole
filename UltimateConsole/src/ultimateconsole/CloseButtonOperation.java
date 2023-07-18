package ultimateconsole;

public enum CloseButtonOperation
{
    DO_NOTHING(0),HIDE(1),DISPOSE(2),EXIT(3);

    public final int value;

    CloseButtonOperation(int value)
    {
        this.value = value;
    }
}
