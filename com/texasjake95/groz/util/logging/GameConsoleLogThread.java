package com.texasjake95.groz.util.logging;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.LogRecord;

public class GameConsoleLogThread implements Runnable {
	
	public static ConsoleHandler wrappedHandler = new ConsoleHandler();
	public static LinkedBlockingQueue<LogRecord> recordQueue = new LinkedBlockingQueue<LogRecord>();
	
	@Override
	public void run()
	{
		do
		{
			LogRecord lr;
			try
			{
				lr = recordQueue.take();
				wrappedHandler.publish(lr);
			}
			catch (InterruptedException e)
			{
				GrozLogger.logGame(Level.SEVERE, "An error has occured please look at the Groz.log for more details, Please show that log to the developer", true, 0);
				Thread.interrupted();
			}
		}
		while (true);
	}
}
