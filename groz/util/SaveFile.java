package groz.util;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Map;
import java.util.TreeMap;

/**
 * This class will let Groz to create/use a save file
 * 
 * @author Texasjake95
 */
public class SaveFile {

	private File file;
	private Map<String, String> values = new TreeMap<String, String>();

	public SaveFile(String filename) {
		this.file = new File(filename);
		this.load();
	}

	private void load() {
		try {
			if (!this.file.exists()) {
				this.file.createNewFile();
			}
			DataInputStream dis = new DataInputStream(new FileInputStream(
					this.file));
			String insert = dis.readUTF();
			while (insert != null) {
				String sti = this.breakString(insert);
				this.values.put(insert, sti);
				insert = dis.readUTF();
			}
			dis.close();
		} catch (Exception e) {
		}
	}

	public void save() {
		try {
			DataOutputStream dos = new DataOutputStream(new FileOutputStream(
					this.file));
			for (String string : this.values.keySet()) {
				dos.writeUTF(string
						+ this.values.put(string, this.values.get(string)));
			}
			dos.close();
		} catch (Exception e) {
		}
	}

	public void setInt(String name, int value) {
		this.values.put("Integer." + name + ":", value + "");
	}

	public void setDouble(String name, double value) {
		this.values.put("Double." + name + ":", value + "");
	}

	public void setBoolean(String name, boolean value) {
		this.values.put("Boolean." + name + ":", value + "");
	}

	public void setShort(String name, short value) {
		this.values.put("Short." + name + ":", value + "");
	}

	public void setLong(String name, long value) {
		this.values.put("Long." + name + ":", value + "");
	}

	public void setFloat(String name, float value) {
		this.values.put("Float." + name + ":", value + "");
	}

	public void setString(String name, String value) {
		this.values.put("String." + name + ":", value + "");
	}

	public void setByte(String name, byte value) {
		this.values.put("Byte." + name + ":", value + "");
	}

	public int getInt(String name) {
		if (!this.values.containsKey("Integer." + name + ":")) {
			this.setInt(name, 0);
		}
		return Integer.parseInt(this.values.get("Integer." + name + ":"));
	}

	public void getDouble(String name, double value) {
		this.values.get("Double." + name + ":" + value);
	}

	public void getBoolean(String name, boolean value) {
		this.values.get("Boolean." + name + ":" + value);
	}

	public void getShort(String name, short value) {
		this.values.get("Short." + name + ":" + value);
	}

	public void getLong(String name, long value) {
		this.values.get("Long." + name + ":" + value);
	}

	public void getFloat(String name, float value) {
		this.values.get("Float." + name + ":" + value);
	}

	public void getString(String name, String value) {
		this.values.get("String." + name + ":" + value);
	}

	public void getByte(String name, byte value) {
		this.values.get("Byte." + name + ":" + value);
	}

	private String breakString(String Break) {
		String[] results = Break.split(":");
		return results[results.length - 1];
	}
}
