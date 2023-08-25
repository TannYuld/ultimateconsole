package ultimateconsole;

public abstract class InputStream
{
	private final char actionKey;
	private final FinalAction finalAction;
	private final Boolean forceNewAction;
	private final int holdWaitTime;
	
	private boolean canPerformAction = true;
	private TriggerType lastTriggerType = null;
	private int waitTimer;
	
	public InputStream(char actionKey, FinalAction finalAction, Boolean forceNewAction, int holdWaitTime) 
	{
		this.actionKey = actionKey;
		this.finalAction = finalAction;
		this.forceNewAction = forceNewAction;
		this.holdWaitTime = holdWaitTime;
	}
	
	public InputStream(char actionKey, FinalAction finalAction) 
	{
		this.actionKey = actionKey;
		this.finalAction = finalAction;
		this.forceNewAction = false;
		holdWaitTime = 0;
	}
	
	public InputStream(char actionKey, FinalAction finalAction, int holdWaitTime) 
	{
		this.actionKey = actionKey;
		this.finalAction = finalAction;
		this.forceNewAction = false;
		this.holdWaitTime = holdWaitTime;
	}
	
	public InputStream(char actionKey) 
	{
		this.actionKey = actionKey;
		this.finalAction = FinalAction.DO_NOTHING;
		this.forceNewAction = false;
		holdWaitTime = 0;
	}
	
	public InputStream(char actionKey, FinalAction finalAction, Boolean forceNewAction) 
	{
		this.actionKey = actionKey;
		this.finalAction = FinalAction.DO_NOTHING;
		this.forceNewAction = forceNewAction;
		holdWaitTime = 0;
	}
	
	public char getActionKey()
	{
		return actionKey;
	}
	
	protected FinalAction getFinalAction() 
	{
		return finalAction;
	}
	
	protected Boolean getForceNewAction() 
	{
		return forceNewAction;
	}
	
	protected Boolean canPerformAction() 
	{
		return canPerformAction;
	}
	
	protected void setCanPerformAction(Boolean arg) 
	{
		canPerformAction = arg;
	}
	
	protected TriggerType getLastTriggerType()
	{
		return lastTriggerType;
	}
	
	protected void setLastTriggerType(TriggerType arg) 
	{
		lastTriggerType = arg;
	}
	
	protected void setTimer(int arg) 
	{
		waitTimer = arg;
	}
	
	protected Integer getTimer() 
	{
		return waitTimer;
	}
	
	protected Integer getHoldWaitTime() 
	{
		return holdWaitTime;
	}
	
	public abstract void keyPressed();
	public abstract void keyReleased();
	public abstract void keyHold();
}
