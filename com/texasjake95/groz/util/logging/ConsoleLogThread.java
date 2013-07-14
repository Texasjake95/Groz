package com.texasjake95.groz.util.logging;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.ConsoleHandler;
import java.util.logging.LogRecord;

public class ConsoleLogThread implements Runnable {
	
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
				e.printStackTrace(GrozLogger.errCache);
				Thread.interrupted();
			}
		}
		while (true);
	}
}
