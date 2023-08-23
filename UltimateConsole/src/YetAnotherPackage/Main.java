package YetAnotherPackage;

import java.awt.Color;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import ultimateconsole.*;

public class Main {

	public static void main(String[] args)
	{
		WindowProperties p = new WindowProperties();
		
		Window window1 = new Window("Window 1", p);
		Window window2 = new Window("Window 2", p);
		Window window3 = new Window("Window 3", p);
		
		Console c = new Console(window1);
		//Console c2 = new Console(window2);
		
		c.sleep(1000);
		
		c.switchWindow(window1);
		
		c.sleep(1000);
//		
//		
//		c.switchWindow(window1);
		
//		InputStream stream = new InputStream('d', FinalAction.DO_NOTHING, TriggerType.PRESS, true) {
//			
//			@Override
//			public void performAction()
//			{
//				System.out.println("YOO");
//			}
//		};
//		
//		c.addInputStream(stream);
//		c.addInputStream(stream);
//		c.sleep(2500);
//		c.removeInputStream(stream);
//		System.out.println("It happend");
		
	}

}
