package groz.util.logging;

import java.util.logging.Handler;
import java.util.logging.Level;
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
			GrozLogger.logGame(Level.SEVERE, "An error has occured please look at the Groz.log for more details, Please show that log to the developer", true, 0);
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
