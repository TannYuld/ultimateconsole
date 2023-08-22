package ultimateconsole;

public abstract class InputStream
{
	private final char actionKey;
	private final FinalAction finalAction;
	private final TriggerType triggerType;
	private final Boolean forceNewAction;
	
	private boolean canPerformAction = true;
	
	public InputStream(char actionKey, FinalAction finalAction, TriggerType triggerType, Boolean forceNewAction) 
	{
		this.actionKey = actionKey;
		this.finalAction = finalAction;
		this.triggerType = triggerType;
		this.forceNewAction = forceNewAction;
	}
	
	public InputStream(char actionKey, FinalAction finalAction, TriggerType triggerType) 
	{
		this.actionKey = actionKey;
		this.finalAction = finalAction;
		this.triggerType = triggerType;
		this.forceNewAction = false;
	}
	
	public InputStream(char actionKey, TriggerType triggerType) 
	{
		this.actionKey = actionKey;
		this.finalAction = FinalAction.DO_NOTHING;
		this.triggerType = triggerType;
		this.forceNewAction = false;
	}
	
	public InputStream(char actionKey, TriggerType triggerType, Boolean forceNewAction) 
	{
		this.actionKey = actionKey;
		this.finalAction = FinalAction.DO_NOTHING;
		this.triggerType = triggerType;
		this.forceNewAction = forceNewAction;
	}
	
	public char getActionKey()
	{
		return actionKey;
	}
	
	protected FinalAction getFinalAction() 
	{
		return finalAction;
	}
	
	protected TriggerType getTriggerType()
	{
		return triggerType;
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
	
	public abstract void performAction();
}
