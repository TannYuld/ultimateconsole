package ultimateconsole;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Iterator;

class InputStreamHandler implements KeyListener
{
	private	ArrayList<InputStream> keyReleaseActionStreamList = new ArrayList<>();
	private ArrayList<InputStream> keyPressActionStreamList = new ArrayList<>();
	private ArrayList<InputStream> inputStreamWaitlistForAwake = new ArrayList<>();
	
	public void addInputStream(InputStream inputStream) 
	{
		switch(inputStream.getTriggerType()) 
		{
			case PRESS -> keyPressActionStreamList.add(inputStream);
			case RELEASE -> keyReleaseActionStreamList.add(inputStream);
		}
	}
	
	public void removeInputStream(InputStream inputStream) 
	{
		switch(inputStream.getTriggerType()) 
		{
			case PRESS -> searchAndRemoveInputStream(keyPressActionStreamList, inputStream);
			case RELEASE -> searchAndRemoveInputStream(keyReleaseActionStreamList, inputStream);
		}
	}
	
	private void searchAndRemoveInputStream(ArrayList<InputStream> searchedList, 
			InputStream objectSearchedForFor) 
	{
		if(searchedList.contains(objectSearchedForFor)) 
		{
			Iterator<InputStream> searchedListIterator = searchedList.iterator();
			while(searchedListIterator.hasNext()) 
			{
				InputStream stream = searchedListIterator.next();
				if(stream == objectSearchedForFor) 
				{
					searchedListIterator.remove();
					break;
				}
			}
		}
	}
	
	@Override
	public void keyTyped(KeyEvent e){}

	@Override
	public void keyPressed(KeyEvent e)
	{
		handleKeyInput(e, keyPressActionStreamList, TriggerType.PRESS);
	}

	@Override
	public void keyReleased(KeyEvent e)
	{
		handleKeyInput(e, keyReleaseActionStreamList, TriggerType.RELEASE);
	}
	
	private void handleKeyInput(KeyEvent e, ArrayList<InputStream> arrayList, TriggerType typeOfTrigger) 
	{
		Iterator<InputStream> keyListIterator = arrayList.iterator();
		Iterator<InputStream> keyWaitlistIterator = inputStreamWaitlistForAwake.iterator();
		
		while(keyWaitlistIterator.hasNext()) 
		{
			InputStream streamToWake = keyWaitlistIterator.next();
			if(streamToWake.getActionKey() == e.getKeyChar()) 
			{
				if(streamToWake.getTriggerType() != typeOfTrigger) 
				{
					streamToWake.setCanPerformAction(true);
					keyWaitlistIterator.remove();
				}
			}
		}
		
		while(keyListIterator.hasNext()) 
		{
			InputStream stream = keyListIterator.next();
			if(stream.getActionKey() == e.getKeyChar()) 
			{
				if(stream.canPerformAction()) 
				{
					if(stream.getFinalAction() == FinalAction.REMOVE) 
					{
						keyListIterator.remove();
					}else if(stream.getForceNewAction()) 
					{
						stream.setCanPerformAction(false);
						inputStreamWaitlistForAwake.add(stream);
					}
					
					stream.performAction();
				}
			}
		}
	}

}
