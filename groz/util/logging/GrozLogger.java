package groz.util.logging;

import groz.util.InteractiveHelper;

import java.io.File;
import java.io.PrintStream;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 * @author Texasjake95
 */
public class GrozLogger {
	
	public static final String LINE_SEPARATOR = System.getProperty("line.separator");
	public static Level GAME = new Game("inGame", Integer.MAX_VALUE / 2);
	private static Thread gameConsoleLogThread;
	public static PrintStream errCache;
	private static Logger grozLogger = Logger.getLogger("Groz");
	private static Logger gameLogger = Logger.getLogger("GrozinGame");
	private static boolean Init = false;
	private static boolean gameInit = false;
	private static boolean masterInit = false;
	
	private static void init()
	{
		if (!masterInit)
		{
			LogManager.getLogManager().reset();
			Logger globalLogger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
			globalLogger.setLevel(Level.OFF);
			masterInit = true;
		}
	}
	
	public static void logInput(String msg)
	{
		logGame(Level.INFO, msg, false, 0);
	}
	
	public static void logGame(Level level, String msg, boolean showInGame, double timeDelay)
	{
		init();
		if (showInGame)
		{
			InteractiveHelper.gameWait_Sec(timeDelay);
			getGameLogger().log(GAME, msg);
		}
		getLogger().log(level, msg);
	}
	
	public static void logGame(String msg, double delay)
	{
		logGame(GAME, msg, true, delay);
	}
	
	public static void logGame(double doub, long delay)
	{
		logGame(doub + "", delay);
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
}
