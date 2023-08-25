package ultimateconsole;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Iterator;

class InputStreamHandler implements KeyListener
{
	private	ArrayList<InputStream> inputStreamList = new ArrayList<>();
	private ArrayList<InputStream> inputStreamWaitlistForAwake = new ArrayList<>();
	private ArrayList<InputStream> inputStreamBeingPressedList = new ArrayList<>();
	
	public InputStreamHandler() 
	{
		Thread timerThread = new Thread() {
			@Override
			public void run() 
			{
				while(true) 
				{
					try
					{
						Thread.sleep(1000);
					} catch (InterruptedException e)
					{
						e.printStackTrace();
					}
					
					Iterator itr = inputStreamBeingPressedList.iterator();
					
					while(itr.hasNext())
					{
						InputStream stream = (InputStream) itr.next();
						
						stream.setTimer(stream.getTimer()+1);
						if(stream.getTimer() >= stream.getHoldWaitTime()) 
						{
							stream.keyHold();
							stream.setTimer(0);
						}
					}
				}
			}
		};
		timerThread.start();
	}
	
	public void addInputStream(InputStream inputStream) 
	{
		inputStreamList.add(inputStream);
	}
	
	public void removeInputStream(InputStream inputStream) 
	{
		searchAndRemoveInputStream(inputStreamList, inputStream);
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
		handleKeyInput(e, inputStreamList, TriggerType.PRESS);
	}

	@Override
	public void keyReleased(KeyEvent e)
	{
		handleKeyInput(e, inputStreamList, TriggerType.RELEASE);
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
				if(streamToWake.getLastTriggerType() != typeOfTrigger) 
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
						stream.setLastTriggerType(typeOfTrigger);
						inputStreamWaitlistForAwake.add(stream);
					}
					
					switch(typeOfTrigger) 
					{
						case PRESS:
							stream.keyPressed();
							if(!inputStreamBeingPressedList.contains(stream)) 
							{
								stream.setTimer(0);
								inputStreamBeingPressedList.add(stream);
							}
							break;
							
						case RELEASE:
							stream.keyReleased();
							if(inputStreamBeingPressedList.contains(stream)) 
							{
								inputStreamBeingPressedList.remove(stream);
							}
							break;
					}
				}
			}
		}
	}

}
