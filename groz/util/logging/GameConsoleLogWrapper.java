package groz.util.logging;

import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class GameConsoleLogWrapper extends Handler {
	
	@Override
	public void publish(LogRecord record)
	{
		boolean currInt = Thread.interrupted();
		try
		{
			GameConsoleLogThread.recordQueue.put(record);
		}
		catch (InterruptedException e)
		{
			e.printStackTrace(GrozLogger.gameErrCache);
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
