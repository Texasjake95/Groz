package groz.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

/**
 * @author Texasjake95
 */
public class GrozLogger {
	
	private static final String LINE_SEPARATOR = System.getProperty("line.separator");
	public static Level GAME = new Game("inGame", Integer.MAX_VALUE / 2);
	private static Thread gameConsoleLogThread;
	private static PrintStream errCache;
	private static PrintStream gameErrCache;
	private static Logger grozLogger = Logger.getLogger("Groz");
	private static Logger gameLogger = Logger.getLogger("GrozinGame");
	private static boolean Init = false;
	private static boolean gameInit = false;
	private static boolean masterInit = false;
	
	public static void logGame(Level level, String msg)
	{
		if (!masterInit)
		{
			LogManager.getLogManager().reset();
			Logger globalLogger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
			globalLogger.setLevel(Level.OFF);
			masterInit = true;
		}
		getGameLogger().log(GAME, msg);
		getLogger().log(level, msg);
	}
	
	public static Logger getLogger()
	{
		if (!Init)
		{
			grozLogger.setLevel(Level.ALL);
			grozLogger.setUseParentHandlers(false);
			File file = new File("../Groz", "GrozLog-%g.log");
			FileHandler fileHandler = null;
			try
			{
				fileHandler = new FileHandler(file.getPath(), 0, 3) {
					
					public synchronized void close() throws SecurityException
					{
						// We don't want this handler to reset
					}
				};
			}
			catch (Exception e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			setHandlers(grozLogger, fileHandler);
			errCache = System.err;
			System.setErr(new PrintStream(new LoggingOutStream(grozLogger), true));
			Init = true;
		}
		return grozLogger;
	}
	
	public static Logger getGameLogger()
	{
		if (!gameInit)
		{
			gameLogger.setUseParentHandlers(false);
			gameLogger.setLevel(Level.ALL);
			gameConsoleLogThread = new Thread(new GameConsoleLogThread());
			gameConsoleLogThread.setDaemon(true);
			gameConsoleLogThread.start();
			File file = new File("../Groz", "GameLog-%g.log");
			FileHandler fileHandler = null;
			try
			{
				fileHandler = new FileHandler(file.getPath(), 0, 3) {
					
					public synchronized void close() throws SecurityException
					{
						// We don't want this handler to reset
					}
				};
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			setGameHandlers(gameLogger, fileHandler);
			gameErrCache = System.err;
			System.setOut(new PrintStream(new LoggingOutStream(gameLogger), true));
			gameInit = true;
		}
		return gameLogger;
	}
	
	private static void setHandlers(Logger logger, FileHandler fileHandler)
	{
		ConsoleLogThread.wrappedHandler.setLevel(Level.parse(System.getProperty("groz.log.level", "ALL")));
		logger.addHandler(new ConsoleLogWrapper());
		ConsoleLogThread.wrappedHandler.setFormatter(new GrozLoggerFormatter());
		fileHandler.setLevel(Level.ALL);
		fileHandler.setFormatter(new GrozLoggerFormatter());
		logger.addHandler(fileHandler);
	}
	
	private static void setGameHandlers(Logger logger, FileHandler fileHandler)
	{
		GameConsoleLogThread.wrappedHandler.setLevel(Level.parse(System.getProperty("grozingame.log.level", "ALL")));
		logger.addHandler(new GameConsoleLogWrapper());
		GameConsoleLogThread.wrappedHandler.setFormatter(new GrozLoggerFormatter());
		fileHandler.setLevel(Level.ALL);
		fileHandler.setFormatter(new GrozLoggerFormatter());
		logger.addHandler(fileHandler);
	}
	
	private static class GameConsoleLogWrapper extends Handler {
		
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
				e.printStackTrace(gameErrCache);
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
	
	private static class ConsoleLogWrapper extends Handler {
		
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
				e.printStackTrace(errCache);
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
	
	private static class ConsoleLogThread implements Runnable {
		
		static ConsoleHandler wrappedHandler = new ConsoleHandler();
		static LinkedBlockingQueue<LogRecord> recordQueue = new LinkedBlockingQueue<LogRecord>();
		
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
					e.printStackTrace(errCache);
					Thread.interrupted();
				}
			}
			while (true);
		}
	}
	
	private static class GameConsoleLogThread implements Runnable {
		
		static ConsoleHandler wrappedHandler = new ConsoleHandler();
		static LinkedBlockingQueue<LogRecord> recordQueue = new LinkedBlockingQueue<LogRecord>();
		
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
					e.printStackTrace(gameErrCache);
					Thread.interrupted();
					// Stupid
				}
			}
			while (true);
		}
	}
	
	final static class GrozLoggerFormatter extends Formatter {
		
		private SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
		
		public String format(LogRecord record)
		{
			StringBuilder msg = new StringBuilder();
			if (record.getLoggerName().equals(gameLogger.getName()))
			{
				msg.append(record.getMessage());
				msg.append(LINE_SEPARATOR);
				Throwable thr = record.getThrown();
				if (thr != null)
				{
					StringWriter thrDump = new StringWriter();
					thr.printStackTrace(new PrintWriter(thrDump));
					msg.append(thrDump.toString());
				}
				return msg.toString();
			}
			Level lvl = record.getLevel();
			if (!(lvl.getName().equals(GAME.getName())))
			{
				msg.append(this.dateFormat.format(Long.valueOf(record.getMillis())));
				String name = lvl.getLocalizedName();
				if (name == null)
				{
					name = lvl.getName();
				}
				if ((name != null) && (name.length() > 0))
				{
					msg.append(" [" + name + "] ");
				}
				else
				{
					msg.append(" ");
				}
				if (record.getLoggerName() != null)
				{
					msg.append("[" + record.getLoggerName() + "] ");
				}
				else
				{
					msg.append("[] ");
				}
			}
			msg.append(record.getMessage());
			msg.append(LINE_SEPARATOR);
			Throwable thr = record.getThrown();
			if (thr != null)
			{
				StringWriter thrDump = new StringWriter();
				thr.printStackTrace(new PrintWriter(thrDump));
				msg.append(thrDump.toString());
			}
			return msg.toString();
		}
	}
	
	private static class LoggingOutStream extends ByteArrayOutputStream {
		
		private Logger log;
		private StringBuilder currentMessage;
		
		public LoggingOutStream(Logger log)
		{
			this.log = log;
			this.currentMessage = new StringBuilder();
		}
		
		@Override
		public void flush() throws IOException
		{
			String record;
			synchronized (GrozLogger.class)
			{
				super.flush();
				record = this.toString();
				super.reset();
				currentMessage.append(record.replace(GrozLogger.LINE_SEPARATOR, "\n"));
				// Are we longer than just the line separator?
				int lastIdx = -1;
				int idx = currentMessage.indexOf("\n", lastIdx + 1);
				while (idx >= 0)
				{
					log.log(Level.INFO, currentMessage.substring(lastIdx + 1, idx));
					lastIdx = idx;
					idx = currentMessage.indexOf("\n", lastIdx + 1);
				}
				if (lastIdx >= 0)
				{
					String rem = currentMessage.substring(lastIdx + 1);
					currentMessage.setLength(0);
					currentMessage.append(rem);
				}
			}
		}
	}
	
	public static class Game extends Level {
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
		protected Game(String name, int value)
		{
			super(name, value);
		}
	}
}
