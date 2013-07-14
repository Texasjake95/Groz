package com.texasjake95.groz.util.logging;

import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class ConsoleLogWrapper extends Handler {
	
	@Override
	public void publish(LogRecord record)
	{
		boolean currInt = Thread.interrupted();
		try
		{
			ConsoleLogThread.recordQueue.put(record);
		}
		catch (InterruptedException e)
		{
			e.printStackTrace(GrozLogger.errCache);
		}
		if (currInt)
		{
			Thread.currentThread().interrupt();
		}
	}
	
	@Override
	public void flush()
	{
	}
	
	@Override
	public void close() throws SecurityException
	{
	}
}
