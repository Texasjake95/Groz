package com.texasjake95.groz.util;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;

import com.texasjake95.groz.entity.InGamePlayer;
import com.texasjake95.groz.entity.Player;
import com.texasjake95.groz.entity.attack.Attack;
import com.texasjake95.groz.util.logging.GrozLogger;
import com.texasjake95.lib.file.FileHelper;

/**
 * This class will let Groz to create/use a save file
 * 
 * @author Texasjake95
 */
public class SaveFile {
	
	private File file;
	private Map<String, String> values = new TreeMap<String, String>();
	
	public SaveFile()
	{
		this.file = FileHelper.getFile("saves/Groz.dat");
		this.load();
	}
	
	private void load()
	{
		try
		{
			/*
			 * if (!this.file.exists()) { GrozLogger.logGame(Level.INFO,
			 * "No Save File detected Creating now", true, 1.5); File temp = new
			 * File("saves"); temp.mkdirs(); this.file.createNewFile(); }
			 */
			GrozLogger.logGame("Loading Save File found at: " + this.file.getAbsolutePath(), 1.5);
			GrozLogger.logGame(Level.INFO, "", false, 0);
			DataInputStream dis = new DataInputStream(new FileInputStream(this.file));
			String insert;
			insert = dis.readUTF();
			while (insert != null)
			{
				String str = this.breakFrontString(insert);
				String ing = this.breakEndString(insert);
				this.values.put(str, ing);
				GrozLogger.logGame(Level.INFO, "Loading: " + insert, false, 0);
				insert = dis.readUTF();
			}
			dis.close();
		}
		catch (EOFException e)
		{
			GrozLogger.logGame("Successfully loaded Save File found at: " + this.file.getAbsolutePath(), 1.5);
		}
		catch (Exception e)
		{
		}
		GrozLogger.logGame(Level.INFO, "", true, 0);
	}
	
	public void save()
	{
		try
		{
			GrozLogger.logGame("Saving Save File at: " + this.file.getAbsolutePath(), 1.5);
			GrozLogger.logGame(Level.INFO, "", false, 0);
			if (!this.file.exists())
			{
				this.file.createNewFile();
			}
			DataOutputStream dos = new DataOutputStream(new FileOutputStream(this.file));
			boolean skip = false;
			for (String string : this.values.keySet())
			{
				if (InGamePlayer.godMode)
					if (string.contains(".player"))
						skip = true;
				if (!skip)
				{
					dos.writeUTF(string + ":" + this.values.get(string));
					GrozLogger.logGame(Level.INFO, "Saving: " + string + ":" + this.values.get(string), true, 0);
				}
				skip = false;
			}
			dos.close();
		}
		catch (EOFException e)
		{
		}
		catch (Exception e)
		{
		}
		GrozLogger.logGame("Successfully saved Save File at: " + this.file.getAbsolutePath(), 1.5);
		GrozLogger.logGame(Level.INFO, "", true, 0);
	}
	
	public boolean doesSaveFileHave(String name)
	{
		return this.values.containsKey(name);
	}
	
	public void setInt(String name, int value)
	{
		this.values.put("Integer." + name, value + "");
	}
	
	public void setDouble(String name, double value)
	{
		this.values.put("Double." + name, value + "");
	}
	
	public void setBoolean(String name, boolean value)
	{
		this.values.put("Boolean." + name, value + "");
	}
	
	public void setShort(String name, short value)
	{
		this.values.put("Short." + name, value + "");
	}
	
	public void setLong(String name, long value)
	{
		this.values.put("Long." + name, value + "");
	}
	
	public void setFloat(String name, float value)
	{
		this.values.put("Float." + name, value + "");
	}
	
	public void setString(String name, String value)
	{
		this.values.put("String." + name, value + "");
	}
	
	public void setByte(String name, byte value)
	{
		this.values.put("Byte." + name, value + "");
	}
	
	public int getInt(String name)
	{
		if (!this.values.containsKey("Integer." + name))
		{
			this.setInt(name, 0);
		}
		return Integer.parseInt(this.values.get("Integer." + name));
	}
	
	public double getDouble(String name, double value)
	{
		if (!this.values.containsKey("Double." + name))
		{
			this.setDouble(name, 0);
		}
		return Double.parseDouble(this.values.get("Double." + name));
	}
	
	public void getBoolean(String name, boolean value)
	{
		this.values.get("Boolean." + name + value);
	}
	
	public void getShort(String name, short value)
	{
		this.values.get("Short." + name + value);
	}
	
	public void getLong(String name, long value)
	{
		this.values.get("Long." + name + value);
	}
	
	public void getFloat(String name, float value)
	{
		this.values.get("Float." + name + value);
	}
	
	public String getString(String name)
	{
		if (!this.values.containsKey("String." + name))
		{
			this.setString(name, "");
		}
		return this.values.get("String." + name);
	}
	
	public void getByte(String name, byte value)
	{
		this.values.get("Byte." + name + value);
	}
	
	private String breakFrontString(String Break)
	{
		String[] results = Break.split(":");
		return results[0];
	}
	
	private String breakEndString(String Break)
	{
		String[] results = Break.split(":");
		return results[results.length - 1];
	}
	
	public Player createPlayer()
	{
		Player player;
		player = new Player(this.getString("playerName"), this.getInt("playerLevel"));
		if (this.getInt("playerAttack") != 0)
		{
			player.getStats().setAttack(this.getInt("playerAttack"));
		}
		else
		{
		}
		if (this.getInt("playerDefense") != 0)
		{
			player.getStats().setDefense(this.getInt("playerDefense"));
		}
		else
		{
		}
		if (this.getInt("playerAgility") != 0)
		{
			player.getStats().setAgility(this.getInt("playerAgility"));
		}
		else
		{
		}
		if (this.getInt("playerHP") != 0)
		{
			player.getStats().setHP(this.getInt("playerHP"));
		}
		else
		{
		}
		if (this.getInt("playerSpeed") != 0)
		{
			player.getStats().setAgility(this.getInt("playerSpeed"));
		}
		else
		{
		}
		player.addAttack(new Attack(1d, .75d, "Normal"), 0);
		player.addAttack(new Attack(1d, .75d, "Strong"), 1);
		player.addAttack(new Attack(.5d, 1d, "Light"), 2);
		player.addAttack(new Attack(1d, .75d, "Heal").setGivesDamage(false), 3);
		return player;
	}
}
